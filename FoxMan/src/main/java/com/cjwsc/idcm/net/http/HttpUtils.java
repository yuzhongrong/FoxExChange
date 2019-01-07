package com.cjwsc.idcm.net.http;

import android.content.Context;

import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.Utils.LoginUtils;
import com.cjwsc.idcm.Utils.NetworkUtil;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.net.config.NetWorkConfiguration;
import com.cjwsc.idcm.net.cookie.SimpleCookieJar;
import com.cjwsc.idcm.net.interceptor.LogInterceptor;
import com.cjwsc.idcm.net.request.RetrofitClient;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：   对OkHttpClient进行配置
 * 备注消息：
 * 修改时间：2016/11/18 下午2:12
 **/
public class HttpUtils {

    public static final String TAG = "HttpUtils";

    //    获得HttpUtils实例
    private static HttpUtils mInstance;
    //    OkHttpClient对象
    private OkHttpClient mOkHttpClient;
    private static NetWorkConfiguration configuration;
    private Context context;

    private static List<Interceptor> interceptors;
    /**
     * 是否加载本地缓存数据
     * 默认为TRUE
     */
    private boolean isLoadDiskCache = true;

    /**
     * ---> 针对无网络情况
     * 是否加载本地缓存数据
     *
     * @param isCache true为加载 false不进行加载
     * @return
     */
    public HttpUtils setLoadDiskCache(boolean isCache) {
        this.isLoadDiskCache = isCache;
        return this;
    }

    public void updateBaseUrl(String baseUrl) {
        if (configuration != null) {
            configuration.baseUrl(baseUrl);
        }
    }

    /**更新超时时间为指定时间*/
    public HttpUtils updateTimeOut(int second){
        mOkHttpClient = getOkHttpClient().newBuilder().readTimeout(second, TimeUnit.SECONDS).build();
        return this;
    }

    /**重置超时时间为configuration配置的时间*/
    public void resetTimeOut(){
        int timeout = 20;
        if(configuration!=null){
            timeout = configuration.getConnectTimeOut();
        }
        mOkHttpClient = getOkHttpClient().newBuilder().readTimeout(timeout, TimeUnit.SECONDS).build();
    }

    /**
     * ---> 针对有网络情况
     * 是否加载内存缓存数据
     * 默认为False
     */
    private boolean isLoadMemoryCache = false;

    /**
     * 是否加载内存缓存数据
     *
     * @param isCache true为加载 false不进行加载
     * @return
     */
    public HttpUtils setLoadMemoryCache(boolean isCache) {
        this.isLoadMemoryCache = isCache;
        return this;
    }

    public HttpUtils(Context context) {
//创建默认 okHttpClient对象
        this.context = context;
        /**进行默认配置
         *    未配置configuration ,
         *
         */
        if (configuration == null) {
            configuration = new NetWorkConfiguration(context);
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> LogUtil.e("OkHttp", message)).setLevel
                (HttpLoggingInterceptor.Level.BODY);
        if (configuration.getIsCache()) {
            mOkHttpClient = new OkHttpClient.Builder()
//                   网络缓存拦截器
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(interceptor)
//                    自定义网络Log显示
                    .addInterceptor(httpLoggingInterceptor)
                    .cache(configuration.getDiskCache())
                    .connectTimeout(configuration.getConnectTimeOut(), TimeUnit.SECONDS)
                    .readTimeout(configuration.getReadTimeOut(), TimeUnit.SECONDS)
                    .connectionPool(configuration.getConnectionPool())
                    .retryOnConnectionFailure(true)
                    .build();
        } else {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(configuration.getConnectTimeOut(), TimeUnit.SECONDS)
                    .connectionPool(configuration.getConnectionPool())
                    .retryOnConnectionFailure(true)
                    .build();

        }
        /**
         *
         *  判断是否在AppLication中配置Https证书
         *
         */
        if (configuration.getCertificates() != null) {
            mOkHttpClient = getOkHttpClient().newBuilder()
                    .sslSocketFactory(HttpsUtils.getSslSocketFactory(configuration.getCertificates(), null, null))
                    .build();
        }

        /**
         * 添加客户定制拦截器
         */

        if(mOkHttpClient!=null&&interceptors!=null&&interceptors.size()>0){
            for(Interceptor item:interceptors){
                mOkHttpClient = getOkHttpClient().newBuilder().addInterceptor(item).build();
            }
        }

    }


   public static void setInterceptoers( List<Interceptor> list){
         interceptors=list;

   }


   private  List<Interceptor> getInterceptors(){
       return  interceptors;
   }

    /**
     * 设置网络配置参数
     *
     * @param configuration
     */
    public static void setConFiguration(NetWorkConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else {
            if (HttpUtils.configuration == null) {
                LogUtil.d("Initialize NetWorkConfiguration with configuration");
                HttpUtils.configuration = configuration;
            } else {
                LogUtil.e("Try to initialize NetWorkConfiguration which had already been initialized before. To re-init NetWorkConfiguration with new configuration ");
            }
        }
        if (configuration != null) {
            LogUtil.i("ConFiguration" + configuration.toString());
        }
    }

    public RetrofitClient getRetrofitClient() {

        LogUtil.v("configuration:" + configuration.toString());
        return new RetrofitClient(configuration.getBaseUrl(), mOkHttpClient);
    }

    /**
     * 设置HTTPS客户端带证书访问
     *
     * @param certificates 本地证书
     */
    public HttpUtils setCertificates(InputStream... certificates) {
        mOkHttpClient = getOkHttpClient().newBuilder()
                .sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null))
                .build();
        return this;
    }

    /**
     * 设置是否打印网络日志
     *
     * @param falg
     */
    public HttpUtils setDBugLog(boolean falg) {
        if (falg) {
            mOkHttpClient = getOkHttpClient().newBuilder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return this;
    }

    /**
     * 设置Coolie
     *
     * @return
     */
    public HttpUtils addCookie() {
        mOkHttpClient = getOkHttpClient().newBuilder()
                .cookieJar(new SimpleCookieJar())
                .build();
        return this;
    }

    /**
     * 获得OkHttpClient实例
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 网络拦截器
     * 进行网络操作的时候进行拦截
     */
    final Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            // 断网后是否加载本地缓存数据
            CacheControl control;

            //如果强制不需要缓存可以通过注解 @Headers("Cache-Control:FORCE_NETWORK")
            if (!request.cacheControl().toString().contains("FORCE_NETWORK")&&(!NetworkUtil.isNetworkAvailable(configuration.context) && isLoadDiskCache) || isLoadMemoryCache) {
                control = CacheControl.FORCE_CACHE;
            } else {
                control = CacheControl.FORCE_NETWORK;
            }
//            if(!LoginUtils.getAppToken(context).equals("")){
//                request = request.newBuilder()
//                        .cacheControl(control)
//                        .header("Authorization", "BasicAuth" + " " + LoginUtils.getAppToken(context))
//                        .build();
//            }else{
                request = request.newBuilder()
                        .cacheControl(control)
                        .build();
//            }

            Response response = chain.proceed(request);
            if (response.isSuccessful()) {
                //有网进行内存缓存数据
                if (NetworkUtil.isNetworkAvailable(configuration.context) && configuration.getIsMemoryCache()) {
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + configuration.getmemoryCacheTime())
                            .build();
                } else {
//              进行本地缓存数据
                    if (configuration.getIsDiskCache()) {
                        response.newBuilder()
                                .removeHeader("Pragma")
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + configuration.getDiskCacheTime())
                                .build();
                    }
                }
            }
            return response;
        }
    };

    /**
     * 获取请求网络实例
     *
     * @return
     */
    public static HttpUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils(BaseApplication.getContext());
                }
            }
        }
        return mInstance;
    }


}
