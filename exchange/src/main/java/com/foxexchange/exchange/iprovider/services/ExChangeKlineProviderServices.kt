package com.foxexchange.exchange.iprovider.services

import com.alibaba.android.arouter.facade.template.IProvider
import com.cjwsc.idcm.net.response.HttpResponse
import com.foxexchange.c2c.params.KlineParam

import io.reactivex.Flowable

interface ExChangeKlineProviderServices : IProvider {

    fun getKlineHistory(params: KlineParam): Flowable<HttpResponse<Any>>
}
