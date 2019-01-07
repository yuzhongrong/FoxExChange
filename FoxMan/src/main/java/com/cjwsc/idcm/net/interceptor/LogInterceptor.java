package com.cjwsc.idcm.net.interceptor;

import com.cjwsc.idcm.Utils.LogUtil;


import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import static microsoft.aspnet.signalr.client.Constants.UTF8;


/**
 *  作者：yzr
 *  电话：18826585609
 *  邮箱：956942189@qq.com
 *  版本号：1.0
 *  类描述：  网络日志过滤器
 *  备注消息：
 *  修改时间：16/9/18 下午2:25
 **/

public class LogInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request();
        Response response=chain.proceed(chain.request());
        MediaType mediaType=response.body().contentType();
        String content=response.body().string();
        long t1 = System.nanoTime();

        String requestParam=getRequestParams(request);

        LogUtil.i(String.format("Sending request %s on %s%n%s%n%s%n%s", request.url(), chain.connection(), request.headers(),requestParam,"请求结果："+content));
      //  LogUtil.e(String.format("Sending params,%s",requestParam));

        LogUtil.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        long t2 = System.nanoTime();
        LogUtil.i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d,
                response.headers()));
        LogUtil.i("response body:" +content );
        if(response.body()!=null)
        {
            ResponseBody body=ResponseBody.create(mediaType, content);
            return response.newBuilder().body(body).build();
        }
        return response;

    }

    private String getRequestParams(Request request) {

        String result="";
        RequestBody requestBody= request.body();
        if(requestBody==null) return "无请求参数";
        Buffer buffer = new Buffer();
        try {
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            result = buffer.readString(charset);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "请求参数："+result;

    }



}