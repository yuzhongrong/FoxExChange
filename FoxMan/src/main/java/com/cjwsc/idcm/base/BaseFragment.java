package com.cjwsc.idcm.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import com.cjwsc.idcm.Utils.LogUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.model.ContractProxy;
import com.cjwsc.idcm.widget.LoadDialog.LoadingPopWindow;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/11/14 上午11:28
 **/
public abstract class BaseFragment<M extends BaseModel, P extends BasePresenter> extends RxFragment implements BaseProgressView {
    protected View rootView;
    protected Context mContext = null;//context
    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据
    protected LoadingPopWindow dialog;
    //    定义Presenter
    protected P mPresenter;

    private Unbinder unbinder;

    //    获取布局资源文件
    protected abstract int getLayoutId();

    //    初始化数据

    protected abstract void onInitView(Bundle bundle);

    //    初始化事件Event

    protected abstract void onEvent();

    //   获取抽取View对象
    protected abstract BaseView getViewImp();

    //    获得抽取接口Model对象
    protected Class getModelClazz() {
        return (Class<M>) ContractProxy.getModelClazz(getClass(), 0);
    }

    //    获得抽取接口Presenter对象
    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }

    private volatile int mLoadingCount = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        if (getLayoutId() != 0) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, rootView);
        dialog = new LoadingPopWindow(getActivity());

        bindMVP();
        onInitView(savedInstanceState);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            lazyFetchDataIfPrepared();

        }
//        DebugLog.e("setUserVisibleHint------"+this.getClass().getSimpleName()+isHasFetchData());
    }

    /**
     * 获取presenter 实例
     */
    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = getActivity();
            bindVM();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onEvent();
    }

    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }

    @Override
    public void onStart() {
        if (mPresenter == null) {
            bindMVP();
        }
        super.onStart();
    }

    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getViewImp() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            ContractProxy.getInstance().bindView(getViewImp(), mPresenter);
            mPresenter.mContext = getActivity();
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
        if (mPresenter == null) {
            bindMVP();
        }

    }

    /**
     * 进行懒加载
     */
    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void lazyFetchData();

    @Override
    public void onDestroy() {

        EventBus.getDefault().unregister(this);
        unSubscribe();
        if (unbinder != null) unbinder.unbind();
        if (dialog != null) {
            dialog.dismiss();
            this.dialog = null;
        }
        if (mPresenter != null) {
            ContractProxy.getInstance().unbindView(getViewImp(), mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
        }


        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    protected boolean isHasFetchData() {
        return hasFetchData;
    }

    @Override
    public void showDialog() {
        if (getActivity() == null) return;
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {

//                if (!dialog.isShowing()) {
//
//                    dialog.showPopupWindow();
//
//                }
                if (dialog!=null){
                    if (!dialog.isShowing()) {
                        dialog.showPopupWindow();
                    }
                    mLoadingCount++;
                    LogUtil.e(this.getClass().getSimpleName() + " dialog  showDialog ---> " + mLoadingCount);
                }


            }
        });
    }

    @Override
    public void dismissDialog() {
        cancelPopupLoading(false);
    }

    protected void cancelPopupLoading(boolean force) {
        if (getActivity() == null) return;
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                if (force) {
                    mLoadingCount = 0;
                }
                mLoadingCount--;
                LogUtil.e(this.getClass().getSimpleName() + "  dialog dismissDialog  ---> " + mLoadingCount);
                if (mLoadingCount <= 0) {
                    mLoadingCount = 0;
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            }
        });

    }


    //提供给接口暴露服务使用
    private CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void addSubscribe(Disposable Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(Disposable);

    }

    protected void navigation(String routerPath) {
        ARouter.getInstance().build(routerPath).navigation();
    }

//    @Override
//    public void onLoading(boolean isShow, String... loadingMessage) {
//        if (!isShow) {
//            cancelLoadingDialog(false);
//        } else {
//            if (!dialog.isShowing()) {
//                dialog.showPopupWindow();
//            }
//            mLoadingCount++;
//        }
//    }

    public void cancelLoadingDialog(boolean force) {
        if (force) {
            mLoadingCount = 0;
        }
        mLoadingCount--;
        if (mLoadingCount <= 0) {
            dialog.dismiss();
        }
    }

    public <T extends View> T $( @IdRes int resId) {
        return (T) rootView.findViewById(resId);
    }
}
