package com.cjwsc.idcm.net.transformer;


import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.net.exception.ExceptionHandle;
import com.cjwsc.idcm.net.exception.NullException;
import com.cjwsc.idcm.net.exception.ServerException;
import com.cjwsc.idcm.net.response.HttpResponse;


import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/11/25 下午8:11
 **/

public class ErrorTransformer<T> implements FlowableTransformer<HttpResponse<T>, T> {


    public static <T> ErrorTransformer<T> create() {
        return new ErrorTransformer<>();
    }

    private static ErrorTransformer instance = null;

    private ErrorTransformer() {
    }

    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T> ErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (ErrorTransformer.class) {
                if (instance == null) {
                    instance = new ErrorTransformer();
                }
            }
        }
        return instance;
    }


    @Override
    public Publisher<T> apply(Flowable<HttpResponse<T>> httpResponseObservable) {
        return httpResponseObservable.map(new Function<HttpResponse<T>, T>() {
            @Override
            public T apply(HttpResponse<T> tHttpResponse) throws Exception {
                String code = tHttpResponse.getStatus() + "";
                if (!code.equals("1")) {//代表业务逻辑！=0代表业务逻辑有错
                    LogUtil.e("异常", tHttpResponse.toString());
                    //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                    throw new ServerException(tHttpResponse.getMsg(), code);
                }
//                //服务器请求数据成功，返回里面的数据实体
               T data=tHttpResponse.getData();
                if(data==null) throw new NullException(tHttpResponse.getMsg(), code);

                return tHttpResponse.getData();
            }
        }).onErrorResumeNext(new Function<Throwable, Publisher<? extends T>>() {
            @Override
            public Publisher<? extends T> apply(Throwable throwable) throws Exception {
                return Flowable.error(ExceptionHandle.handleException(throwable));
            }
        });
    }
}
