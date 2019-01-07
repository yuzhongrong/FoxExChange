package com.foxexchange.c2c.iprovider.impl;

import android.content.Context;

import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.net.http.HttpUtils;
import com.cjwsc.idcm.net.transformer.DefaultTransformer;
import com.foxexchange.c2c.api.C2CHttpApi;
import com.foxexchange.c2c.iprovider.services.C2CKlineProviderServices;

import io.reactivex.Flowable;

public class C2CKlineProviderServicesImpl implements C2CKlineProviderServices {

//
//    @Override
//    public Flowable<Object> getKlineHistory(KlineParam params) {
//        return HttpUtils.getInstance(BaseApplication.getContext())
//                .getRetrofitClient()
//                .builder(C2CHttpApi.class)
//                .getKlineHistory(params)
//                .compose(new DefaultTransformer<Object>());
//    }
//
    @Override
    public void init(Context context) {

    }
}
