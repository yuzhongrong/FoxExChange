package com.cjwsc.idcm.net.request;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/11/22 下午11:27
 **/
public class RetrofitClient {

    private static OkHttpClient mOkHttpClient;
    //    初始化BaseUrl
    private static String baseUrl;

    private static Retrofit retrofit;

    /**
     * RetrofitClient 构造 函数
     * 获取OKhttpClient 实例
     *
     * @param mOkHttpClient
     */
    public RetrofitClient(String baseUrl, OkHttpClient mOkHttpClient) {
        this.baseUrl = baseUrl;
        this.mOkHttpClient = mOkHttpClient;
    }


    /**
     * 修改BaseUrl地址
     *
     * @param baseUrl
     */
    public RetrofitClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * 获得对应的ApiServcie对象
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T builder(Class<T> service) {
        if (baseUrl == null) {
            throw new RuntimeException("baseUrl is null!");
        }
        if (service == null) {
            throw new RuntimeException("api Service is null!");
        }
        retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

}
