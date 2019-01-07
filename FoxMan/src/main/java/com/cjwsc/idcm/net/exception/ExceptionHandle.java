package com.cjwsc.idcm.net.exception;


import com.alibaba.android.arouter.launcher.ARouter;
import com.cjwsc.idcm.R;
import com.cjwsc.idcm.api.NetWorkApi;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.model.bean.ServiceStateBean;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.simple.eventbus.EventBus;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.HttpException;

import static com.cjwsc.idcm.constant.IdcmConstant.ServiceMaintenance_PAGE;

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：  异常处理类
 * 展示友好UI界面给用户
 * 备注消息：
 * 修改时间：16/9/18 下午2:37
 **/
public class ExceptionHandle {
    /**
     * 定义网络异常码
     */
    public static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static String serviceState;

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case GATEWAY_TIMEOUT:
                    ex.ErrorMsg = "";
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.ErrorMsg = BaseApplication.getContext().getResources().getString(R.string.abnormal_server);
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {

            ServerException resultException = (ServerException) e;
            ex = new ResponseThrowable(resultException, resultException.code);
            ex.ErrorMsg = resultException.message;
            return ex;
        }else if(e instanceof NullException){
            ex = new ResponseThrowable(e, ERROR.NULL_ERROR);
            ex.ErrorMsg = BaseApplication.getContext().getResources().getString(R.string.parse_the_exception_null);
            return ex;
        }

        else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.ErrorMsg = BaseApplication.getContext().getResources().getString(R.string.parse_the_exception);
            return ex;
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            //TODO 跳转到服务器维护页面
//            ex.ErrorMsg= BaseApplication.getContext().getResources().getString(R.string.no_network);
            ex.ErrorMsg = "";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.ErrorMsg = BaseApplication.getContext().getResources().getString(R.string.certificate_verification_is_abnormal);
            return ex;
        } else if (e instanceof ConnectTimeoutException || e instanceof java.net.SocketTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.ErrorMsg = BaseApplication.getContext().getResources().getString(R.string.connection_timed_out);
            return ex;
        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);

            //TODO 跳转到服务器维护页面
//            ex.ErrorMsg= BaseApplication.getContext().getResources().getString(R.string.no_network);
            ex.ErrorMsg = "";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 未知错误
         */
        public static final String UNKNOWN = "1000";
        /**
         * 解析错误
         */
        public static final String PARSE_ERROR = "1001";
        /**
         * 网络错误
         */
        public static final String NETWORD_ERROR = "1002";
        /**
         * 协议出错
         */
        public static final String HTTP_ERROR = "1003";

        /**
         * 证书出错
         */
        public static final String SSL_ERROR = "1004";

        /**
         * 连接超时
         */
        public static final String TIMEOUT_ERROR = "1005";


        /**
         * 请求成功返回没有数据null
         */
        public static final String NULL_ERROR = "1006";
    }


}

