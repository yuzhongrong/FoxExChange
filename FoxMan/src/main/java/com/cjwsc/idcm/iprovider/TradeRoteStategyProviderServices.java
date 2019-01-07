package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.AccountLoginBean;
import com.cjwsc.idcm.model.bean.providerbean.TradeStrategyBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/19.
 * 功能描述：
 */

public interface TradeRoteStategyProviderServices extends IProvider {
    /**
     * 获取用户汇率大小
     * @return
     */
    Flowable<List<TradeStrategyBean>> getTradeGroupStrategy(Context context);
}
