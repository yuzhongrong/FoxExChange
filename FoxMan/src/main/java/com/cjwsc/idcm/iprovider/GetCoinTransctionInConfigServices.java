package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.CoinTransctionOutConfigBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：提币项设置信息
 */

public interface GetCoinTransctionInConfigServices extends IProvider{
    Flowable<CoinTransctionOutConfigBean> getCoinTransactionInConfig(Context context, String assetsID);
}
