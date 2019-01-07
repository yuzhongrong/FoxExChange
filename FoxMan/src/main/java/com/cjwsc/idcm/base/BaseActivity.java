package com.cjwsc.idcm.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.language.LanguageUtil;
import com.cjwsc.idcm.model.ContractProxy;
import com.cjwsc.idcm.widget.LoadDialog.LoadingPopWindow;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.jvm.Volatile;


/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/5 下午3:03
 **/
public abstract class BaseActivity<M extends BaseModel, P extends BasePresenter> extends RxAppCompatActivity implements BaseProgressView {
    //    定义Presenter
    protected P mPresenter;
    protected InputMethodManager inputMethodManager;
    protected LoadingPopWindow dialog;

    protected Context mCtx;

    //    获取布局资源文件
    protected abstract int getLayoutId();

    //    初始化数据

    protected abstract void onInitView(Bundle bundle);

    //    初始化事件Event

    protected abstract void onEvent();

    //   获取抽取View对象
    protected abstract BaseView getView();

    //    获得抽取接口Model对象
    protected Class getModelClazz() {
        return (Class<M>) ContractProxy.getModelClazz(getClass(), 0);
    }

    //    获得抽取接口Presenter对象
    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }

    private static final int[] LL = new int[]{ //
            android.R.attr.src,
            //
    };

    private Unbinder unbinder;

    @Volatile
    private int mLoadingCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            //            设置布局资源文件
            setContentView(getLayoutId());
            inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            EventBus.getDefault().register(this);
            mCtx = this;
            unbinder = ButterKnife.bind(this);
            bindMVP();
            dialog = new LoadingPopWindow(this);
            onInitView(savedInstanceState);
            initTitle();
            onEvent();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initTitle() {
        TextView tvBack = (TextView) findViewById(R.id.tv_baseweplus_title_left);
        if (tvBack != null) {
            tvBack.setOnClickListener(view -> onBackPressed());
        }
    }

    /**
     * 获取presenter 实例
     */
    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = this;
            bindVM();
        }

        //用于管理activity
        if(isAddThis()) AppManager.getInstance().addActivity(this);

    }


    protected  boolean isAddThis(){
        return true;
    }



    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }

    @Override
    protected void onStart() {
        if (mPresenter == null) {
            bindMVP();
        }
        super.onStart();
    }


//    @Override
//    protected void attachBaseContext(Context newBase) {//8.0+支持多国语言
//        Context newContext = LanguageUtil.initAppLanguage(newBase);
//        super.attachBaseContext(newContext);
//    }


    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getView() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            ContractProxy.getInstance().bindView(getView(), mPresenter);
            mPresenter.mContext = this;
        }
    }

    /**
     * activity摧毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            ContractProxy.getInstance().unbindView(getView(), mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
            mPresenter = null;
        }
        //销毁activity时候必须先销毁dialog
        if (dialog != null) dialog.dismiss();
        this.dialog = null;

        EventBus.getDefault().unregister(this);

        unSubscribe();
        if (unbinder != null) unbinder.unbind();
        AppManager.getInstance().finishActivity(this);

    }

    @Override
    public void showDialog() {


        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {

//                if (!dialog.isShowing()) {
//
//                    dialog.showPopupWindow();
//
//                }
                if (dialog != null && !dialog.isShowing()) {
                    dialog.showPopupWindow();
                }
                mLoadingCount++;
                LogUtil.e(this.getClass().getSimpleName() + " dialog  showDialog ---> " + mLoadingCount);

            }
        });


    }


    @Override
    public void dismissDialog() {
        cancelPopupLoading(false);
//        getWindow().getDecorView().post(new Runnable() {
//            @Override
//            public void run() {
//
//                if (dialog != null && dialog.isShowing()) {
//                    dialog.dismiss();
//                }
//            }
//        });
    }

    protected void cancelPopupLoading(boolean force) {
        getWindow().getDecorView().post(new Runnable() {
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

    protected boolean isDialogShowing() {
        return dialog != null && dialog.isShowing();
    }

    public void dismissDialogDelay(int timer) {


        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (dialog.isShowing()) {

                    dialog.dismiss();
                }
            }
        }, timer);


    }


    public void dismissDialogDelay(int timer, String text) {


        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (dialog.isShowing()) {

                    dialog.dismiss();
                }
            }
        }, timer);


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

    public <T extends View> T $(@IdRes int resId) {
        return (T) super.findViewById(resId);
    }

    public void finish(View view) {
        finish();
    }
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public void hideSoftKeyboard(View view){
        hideSoftKeyboard();
    }
}
