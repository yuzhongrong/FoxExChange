package com.foxexchange.c2c.api;

import com.cjwsc.idcm.net.response.HttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface C2CHttpApi {

    String C2C_KLINE_HISTORY = "/kline_history"; // k线历史数据

    /**
     *带参数
     * @param params:用于传递参数 如果没有为null
     * @return
     */
   // @POST(C2C_KLINE_HISTORY)
    //Flowable<HttpResponse<Object>> getKlineHistory(@Body KlineParam params);


}
