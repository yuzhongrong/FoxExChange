package com.cjwsc.idcm.net.callback;
import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.net.exception.ResponseThrowable;


import io.reactivex.subscribers.ResourceSubscriber;


/**
 * 作者：${User}
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}0056
 */

public abstract class ErrorSubscriber<T> extends ResourceSubscriber<T> {
    @Override
    public void onError(Throwable e) {
        LogUtil.e("错误信息:"+e.getMessage());
        if(e instanceof ResponseThrowable){
            onError((ResponseThrowable)e);
        }else{
            onError(new ResponseThrowable(e,"1000"));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ResponseThrowable ex);
}

