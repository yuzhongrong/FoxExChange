package com.foxexchange.exchange.api;

import com.cjwsc.idcm.net.response.HttpResponse;
import com.foxexchange.c2c.params.KlineParam;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ExChangeHttpApi {

    String C2C_KLINE_HISTORY = "/kdata_http/kline_history"; // k线历史数据

    /**
     *带参数
     * @return
     */
    @GET(C2C_KLINE_HISTORY)
    Flowable<HttpResponse<Object>> getKlineHistory(@Query("symbol") String symbol,
                                                   @Query("subject") String subject,
                                                   @Query("from") String from,
                                                   @Query("to") String to);


}
