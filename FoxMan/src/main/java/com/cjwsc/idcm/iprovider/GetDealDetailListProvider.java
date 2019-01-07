package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.HistoryOrderDetailBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/30.
 * 功能描述：获取成交订单详情
 */

public interface GetDealDetailListProvider extends IProvider {
    Flowable<List<HistoryOrderDetailBean>> getDealDetailList(Context context,String orderID,String tradeSide);
}
