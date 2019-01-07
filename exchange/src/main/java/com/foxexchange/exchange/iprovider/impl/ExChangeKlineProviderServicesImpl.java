package com.foxexchange.exchange.iprovider.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.net.http.HttpUtils;
import com.cjwsc.idcm.net.response.HttpResponse;
import com.cjwsc.idcm.net.transformer.DefaultTransformer;
import com.foxexchange.c2c.params.KlineParam;
import com.foxexchange.exchange.api.ExChangeHttpApi;
import com.foxexchange.exchange.iprovider.services.ExChangeKlineProviderServices;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Flowable;


@Route(path = "/exchange/services/ExChangeKlineProviderServicesImpl", name = "k线历史数据接口")
public class ExChangeKlineProviderServicesImpl implements ExChangeKlineProviderServices {
    @NotNull
    @Override
    public Flowable<HttpResponse<Object>> getKlineHistory(@NotNull KlineParam params) {
        return HttpUtils.getInstance(BaseApplication.getContext())
                .getRetrofitClient()
                .builder(ExChangeHttpApi.class)
                .getKlineHistory(params.getSymbol(),params.getSubject(),params.getFrom(),params.getTo());
//                .compose(new DefaultTransformer<Object>());

    }

    @Override
    public void init(Context context) {

    }
}
