package com.cjwsc.idcm.event;

import com.cjwsc.idcm.model.bean.trade.CurrentEntruseBean;

import java.util.List;

/**
 * Created by ${zipp} on 2018/1/3.
 * 功能描述：
 */

public class OrderStatusCallBackEvent {

    public List<CurrentEntruseBean> getOrderStatusCallbackBeans() {
        return mOrderStatusCallbackBeans;
    }

    public void setOrderStatusCallbackBeans(List<CurrentEntruseBean> orderStatusCallbackBeans) {
        mOrderStatusCallbackBeans = orderStatusCallbackBeans;
    }

    List<CurrentEntruseBean> mOrderStatusCallbackBeans;
}
