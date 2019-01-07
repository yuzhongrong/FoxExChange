package com.cjwsc.idcm.base;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *  作者：yzr
 *  电话：18826585609
 *  邮箱：956942189@qq.com
 *  版本号：1.0
 *  类描述：
 *         1.获取绑定View实例传递到子类中进行调用!
 *
 *         2.注销View实例
 *
 *         3.创建 Model 实例
 *
 *         4.注销Model实例
 *
 *         5.通过RxJava进行绑定activity和fragment生命周期绑定
 *
 *  备注消息：
 *  修改时间：2016/11/8 下午5:07
 **/
public class BasePresenter<V extends BaseView,M extends BaseModel> implements Presenter<V,M> {

    protected Context mContext;

    protected V mView;

    protected M mModel;

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

    //    获取绑定View实例
    @Override
    public void attachView(V view) {
        this.mView=view;
    }
    //    获取绑定Model层实例
    @Override
    public void attachModel(M m) {
        this.mModel=m;
    }


    public M getModel() {
        return mModel;
    }
    //    注销mModel实例
    @Override
    public void detachModel() {
        this.mModel=null;
    }

    //    注销View实例
    @Override
    public void detachView() {
        this.mView=null;
        unSubscribe();
    }

    public V getView() {
        return mView;
    }

    public boolean isViewBind()
    {
        return mView!=null;
    }
}
