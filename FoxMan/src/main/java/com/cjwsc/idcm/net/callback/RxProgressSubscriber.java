package com.cjwsc.idcm.net.callback;

import com.cjwsc.idcm.Utils.LogUtil;

import com.cjwsc.idcm.Utils.NetworkUtil;
import com.cjwsc.idcm.base.BaseProgressView;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.net.exception.ExceptionHandle;
import com.cjwsc.idcm.net.exception.ResponseThrowable;

import java.lang.ref.WeakReference;
import java.net.ConnectException;

/**
 * 作者：hxy
 * 邮箱：hua.xiangyang@shuweikeji.com
 * 版本号：1.0
 * 项目包名：FoxIDCW com.cjwsc.idcm.net.callback
 * 备注消息：
 * 修改时间：2018/3/26 10:14
 **/

public abstract class RxProgressSubscriber<T> extends ErrorSubscriber<T> {

    private WeakReference<BaseProgressView> mWeakReferenceProgress;
    private boolean mIsShowLoading;

    public RxProgressSubscriber(BaseProgressView progressView) {
        mWeakReferenceProgress = new WeakReference<>(progressView);
        mIsShowLoading = true;
    }

    public RxProgressSubscriber(BaseProgressView progressView, boolean isShowDialog) {
        mWeakReferenceProgress = new WeakReference<>(progressView);
        mIsShowLoading = isShowDialog;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!NetworkUtil.isNetworkAvailable(BaseApplication.getContext())) {
            onError(new ResponseThrowable(new ConnectException(), ExceptionHandle.ERROR.NETWORD_ERROR));
            //onComplete();
            LogUtil.e("--------------->", "无网络");
            return;
        }
        //LogUtil.e("mIsShowLoading   ---- > " + mIsShowLoading);
        if (mIsShowLoading) {
            if (mWeakReferenceProgress != null) {
                BaseProgressView progressView = mWeakReferenceProgress.get();
                if (null != progressView) {
                    progressView.showDialog();
                }
            }
        }
    }

    private void dismissDialog() {
        if (mWeakReferenceProgress != null && mWeakReferenceProgress.get() != null) {
            mWeakReferenceProgress.get().dismissDialog();
            mWeakReferenceProgress.clear();
        }
    }

    @Override
    public void onNext(T t) {
        dismissDialog();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        dismissDialog();
        super.onError(e);
    }

    @Override
    protected void onError(ResponseThrowable ex) {
        dismissDialog();
    }

    @Override
    public void onComplete() {
        dismissDialog();
    }

    public abstract void onSuccess(T data);
}
