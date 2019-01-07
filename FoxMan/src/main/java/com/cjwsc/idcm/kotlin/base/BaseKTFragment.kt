package com.cjwsc.idcm.kotlin.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.Utils.LogUtil
import com.cjwsc.idcm.base.BaseModel
import com.cjwsc.idcm.base.BasePresenter
import com.cjwsc.idcm.base.BaseProgressView
import com.cjwsc.idcm.base.BaseView
import com.cjwsc.idcm.model.ContractProxy
import com.cjwsc.idcm.widget.LoadDialog.LoadingPopWindow
import com.trello.rxlifecycle2.components.support.RxFragment
import org.simple.eventbus.EventBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/11/14 上午11:28
 */
abstract class BaseKTFragment : RxFragment(), BaseProgressView<Any> {
    protected var rootView: View? = null
    protected var mContext: Context? = null//context
    private var isViewPrepared: Boolean = false // 标识fragment视图已经初始化完毕
    protected var isHasFetchData: Boolean = false
        private set // 标识已经触发过懒加载数据
    protected var dialog: LoadingPopWindow? = null
    //    定义Presenter
    //    protected P mPresenter;


    //    获取布局资源文件
    protected abstract val layoutId: Int

    //   获取抽取View对象
//    protected abstract val viewImp: BaseView<*>

    //    //    获得抽取接口Model对象
    //    protected Class getModelClazz() {
    //        return (Class<M>) ContractProxy.getModelClazz(getClass(), 0);
    //    }
    //
    //    //    获得抽取接口Presenter对象
    //    protected Class getPresenterClazz() {
    //        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    //    }

    @Volatile
    private var mLoadingCount = 0


    //提供给接口暴露服务使用
    private var mCompositeDisposable: CompositeDisposable? = null

    //    初始化数据

    protected abstract fun onInitView(bundle: Bundle?)

    //    初始化事件Event

    protected abstract fun onEvent()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView != null) {
            val parent = rootView!!.parent as ViewGroup
            parent?.removeView(rootView)
            return rootView
        }
        if (layoutId != 0) {
            rootView = inflater!!.inflate(layoutId, container, false)
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState)
        }
        EventBus.getDefault().register(this)
        dialog = LoadingPopWindow(activity)

        //     bindMVP();
        onInitView(savedInstanceState)
        return rootView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {

            lazyFetchDataIfPrepared()

        }
        //        DebugLog.e("setUserVisibleHint------"+this.getClass().getSimpleName()+isHasFetchData());
    }

    /**
     * 获取presenter 实例
     */
    //    private void bindMVP() {
    //        if (getPresenterClazz() != null) {
    //            mPresenter = getPresenterImpl();
    //            mPresenter.mContext = getActivity();
    //            bindVM();
    //        }
    //    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onEvent()
    }

    //    private <T> T getPresenterImpl() {
    //        return ContractProxy.getInstance().presenter(getPresenterClazz());
    //    }

    //    @Override
    //    public void onStart() {
    //        if (mPresenter == null) {
    //            bindMVP();
    //        }
    //        super.onStart();
    //    }

    //    private void bindVM() {
    //        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getViewImp() != null) {
    //            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
    //            ContractProxy.getInstance().bindView(getViewImp(), mPresenter);
    //            mPresenter.mContext = getActivity();
    //        }
    //    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepared = true
        lazyFetchDataIfPrepared()
        //        if (mPresenter == null) {
        //            bindMVP();
        //        }

    }

    /**
     * 进行懒加载
     */
    private fun lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (userVisibleHint && !isHasFetchData && isViewPrepared) {
            isHasFetchData = true
            lazyFetchData()
        }

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract fun lazyFetchData()

    override fun onDestroy() {

        EventBus.getDefault().unregister(this)
        unSubscribe()
        if (dialog != null) {
            dialog!!.dismiss()
            this.dialog = null
        }
        //        if (mPresenter != null) {
        //            ContractProxy.getInstance().unbindView(getViewImp(), mPresenter);
        //            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
        //        }


        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun showDialog() {
        if (activity == null) return
        activity.window.decorView.post(object : Runnable {
            override fun run() {

                //                if (!dialog.isShowing()) {
                //
                //                    dialog.showPopupWindow();
                //
                //                }
                if (dialog != null) {
                    if (!dialog!!.isShowing) {
                        dialog!!.showPopupWindow()
                    }
                    mLoadingCount++
                    LogUtil.e(this.javaClass.simpleName + " dialog  showDialog ---> " + mLoadingCount)
                }


            }
        })
    }

    override fun dismissDialog() {
        cancelPopupLoading(false)
    }

    protected fun cancelPopupLoading(force: Boolean) {
        if (activity == null) return
        activity.window.decorView.post(object : Runnable {
            override fun run() {
                if (force) {
                    mLoadingCount = 0
                }
                mLoadingCount--
                LogUtil.e(this.javaClass.simpleName + "  dialog dismissDialog  ---> " + mLoadingCount)
                if (mLoadingCount <= 0) {
                    mLoadingCount = 0
                    if (dialog != null) {
                        dialog!!.dismiss()
                    }
                }
            }
        })

    }

    protected fun unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    fun addSubscribe(Disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(Disposable)

    }

    protected fun navigation(routerPath: String) {
        ARouter.getInstance().build(routerPath).navigation()
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

    fun cancelLoadingDialog(force: Boolean) {
        if (force) {
            mLoadingCount = 0
        }
        mLoadingCount--
        if (mLoadingCount <= 0) {
            dialog!!.dismiss()
        }
    }

    fun <T : View> `$`(@IdRes resId: Int): T {
        return rootView!!.findViewById<View>(resId) as T
    }

//    abstract fun getViewImp(): BaseView<*>?
}
