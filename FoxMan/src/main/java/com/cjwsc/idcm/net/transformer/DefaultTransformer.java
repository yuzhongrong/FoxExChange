package com.cjwsc.idcm.net.transformer;


import com.cjwsc.idcm.net.response.HttpResponse;

import org.reactivestreams.Publisher;


import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 *  作者：yzr
 *  电话：18826585609
 *  邮箱：956942189@qq.com
 *  版本号：1.0
 *  类描述：  预处理异常信息
 *  备注消息：
 *  修改时间：2016/11/25 下午7:22
 **/
public class DefaultTransformer<T>  implements FlowableTransformer<HttpResponse<T>,T> {


    @Override
    public Publisher<T> apply(Flowable<HttpResponse<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ErrorTransformer.<T>getInstance())
                ;
    }
}
