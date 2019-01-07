package com.cjwsc.idcm.kotlin.base


import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.widget.TextView

import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.R
import com.cjwsc.idcm.Utils.LogUtil
import com.cjwsc.idcm.base.AppManager
import com.cjwsc.idcm.base.BaseModel
import com.cjwsc.idcm.base.BasePresenter
import com.cjwsc.idcm.base.BaseProgressView
import com.cjwsc.idcm.base.BaseView
import com.cjwsc.idcm.language.LanguageUtil
import com.cjwsc.idcm.model.ContractProxy
import com.cjwsc.idcm.widget.LoadDialog.LoadingPopWindow
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

import org.simple.eventbus.EventBus

import butterknife.ButterKnife
import butterknife.Unbinder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/5 下午3:03
 */
abstract class BaseKTActivity : RxAppCompatActivity(), BaseProgressView<Any> {
    //    定义Presenter
    //    protected P mPresenter;

    protected var dialog: LoadingPopWindow? = null


    //    获取布局资源文件
    protected abstract val layoutId: Int

    //   获取抽取View对象
//    protected abstract val view: BaseView<*>

   // private var unbinder: Unbinder? = null

    @Volatile
    private var mLoadingCount = 0

    protected val isDialogShowing: Boolean
        get() = dialog != null && dialog!!.isShowing

    //提供给接口暴露服务使用
    private var mCompositeDisposable: CompositeDisposable? = null

    //    初始化数据

    protected abstract fun onInitView(bundle: Bundle?)

    //    初始化事件Event

    protected abstract fun onEvent()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (layoutId != 0) {
            //            设置布局资源文件
            setContentView(layoutId)
            EventBus.getDefault().register(this)
          //  unbinder = ButterKnife.bind(this)
            //            bindMVP();

            AppManager.getInstance().addActivity(this);
            dialog = LoadingPopWindow(this)
            onInitView(savedInstanceState)
            initTitle()
            onEvent()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initTitle() {
        val tvBack = findViewById<View>(R.id.tv_baseweplus_title_left)
        tvBack?.setOnClickListener { view -> onBackPressed() }
    }

    /**
     * 获取presenter 实例
     */
    //    private void bindMVP() {
    //        if (getPresenterClazz() != null) {
    //            mPresenter = getPresenterImpl();
    //            mPresenter.mContext = this;
    //            bindVM();
    //        }
    //
    //        //用于管理activity

    //    }
    //
    //    private <T> T getPresenterImpl() {
    //        return ContractProxy.getInstance().presenter(getPresenterClazz());
    //    }

    //    @Override
    //    protected void onStart() {
    //        if (mPresenter == null) {
    //            bindMVP();
    //        }
    //        super.onStart();
    //    }


    override fun attachBaseContext(newBase: Context) {//8.0+支持多国语言
        val newContext = LanguageUtil.initAppLanguage(newBase)
        super.attachBaseContext(newContext)
    }


    //    private void bindVM() {
    //        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getView() != null) {
    //            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
    //            ContractProxy.getInstance().bindView(getView(), mPresenter);
    //            mPresenter.mContext = this;
    //        }
    //    }

    /**
     * activity摧毁
     */
    override fun onDestroy() {
        super.onDestroy()
        //        if (mPresenter != null) {
        //            ContractProxy.getInstance().unbindView(getView(), mPresenter);
        //            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
        //            mPresenter = null;
        //        }
        //销毁activity时候必须先销毁dialog
        if (dialog != null) dialog!!.dismiss()
        this.dialog = null

        EventBus.getDefault().unregister(this)

        unSubscribe()
   //     if (unbinder != null) unbinder!!.unbind()
        AppManager.getInstance().finishActivity(this)

    }

    override fun showDialog() {


        window.decorView.post(object : Runnable {
            override fun run() {

                //                if (!dialog.isShowing()) {
                //
                //                    dialog.showPopupWindow();
                //
                //                }
                if (dialog != null && !dialog!!.isShowing) {
                    dialog!!.showPopupWindow()
                }
                mLoadingCount++
                LogUtil.e(this.javaClass.simpleName + " dialog  showDialog ---> " + mLoadingCount)

            }
        })


    }


    override fun dismissDialog() {
        cancelPopupLoading(false)
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

    protected fun cancelPopupLoading(force: Boolean) {
        window.decorView.post(object : Runnable {
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

    fun dismissDialogDelay(timer: Int) {


        window.decorView.postDelayed({
            if (dialog!!.isShowing) {

                dialog!!.dismiss()
            }
        }, timer.toLong())


    }


    fun dismissDialogDelay(timer: Int, text: String) {


        window.decorView.postDelayed({
            if (dialog!!.isShowing) {

                dialog!!.dismiss()
            }
        }, timer.toLong())


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

    fun <T : View> `$`(@IdRes resId: Int): T {
        return super.findViewById<View>(resId) as T
    }

    companion object {

        //    //    获得抽取接口Model对象
        //    protected Class getModelClazz() {
        //        return (Class<M>) ContractProxy.getModelClazz(getClass(), 0);
        //    }
        //
        //    //    获得抽取接口Presenter对象
        //    protected Class getPresenterClazz() {
        //        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
        //    }

        private val LL = intArrayOf(//
                android.R.attr.src)//
    }
}
