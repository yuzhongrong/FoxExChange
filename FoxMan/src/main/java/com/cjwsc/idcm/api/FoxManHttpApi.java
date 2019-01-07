package com.cjwsc.idcm.api;

import com.cjwsc.idcm.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by yuzhongrong on 2018/5/9.
 */

public interface FoxManHttpApi {

    public static final String UPLOAD_IMG = "/api/OtcChat/UploadFile"; // 上传图片
    public static final String UPLOAD_CERT = "/api/Appeal/UploadPayCertificate"; // 上传凭证

    /**
     *带参数
     * @param params:用于传递参数 如果没有为null
     * @param body:传递表单体
     * @return
     */
    @POST(UPLOAD_CERT)
    Flowable<HttpResponse<List<String>>> uploadImg(@QueryMap Map<String, String> params,
                                                   @Body MultipartBody body);


    @POST(UPLOAD_IMG)
    Flowable<HttpResponse<Object>> uploadImg1(@Body MultipartBody body);


}
