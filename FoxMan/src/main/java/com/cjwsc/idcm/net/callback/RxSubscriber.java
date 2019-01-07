package com.cjwsc.idcm.net.callback;

/**
 *  作者：yzr
 *  电话：18826585609
 *  邮箱：956942189@qq.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2016/11/24 上午10:56
 **/
public  abstract class RxSubscriber<T> extends ErrorSubscriber<T> {


    @Override
    public void onComplete() {
        //TODO 可以在这里disDialog
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO 可以showDialog

    }

    /**
     *  获取网络数据
     * @param t
     */
    @Override
    public void onNext(T t) {

          onSuccess(t);
    }
    public abstract  void onSuccess(T t);

}