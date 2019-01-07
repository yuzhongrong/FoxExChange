package com.cjwsc.idcm.iprovider.logic;

import com.cjwsc.idcm.model.bean.ServiceStateBean;
import com.cjwsc.idcm.net.response.HttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 *
 * @project name : IDCM2.1
 * @class name :   BaseApi
 * @package name : com.cjwsc.idcm.iprovider.logic
 * @author :       MayerXu10000@gamil.com
 * @date :         2018/3/2 16:49
 * @describe :
 *
 */
public interface BaseApi {
    //校验服务器维护升级
    @GET("http://192.168.1.251:8302/api/maintain/CheckMaintain")
    Flowable<HttpResponse<ServiceStateBean>> checkServiceMaintenance();
}
