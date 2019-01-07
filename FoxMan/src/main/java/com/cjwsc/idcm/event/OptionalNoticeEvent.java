package com.cjwsc.idcm.event;

import com.cjwsc.idcm.model.bean.providerbean.TradeConfigBean;

/**
 * Created by ${zipp} on 2018/2/7.
 * 功能描述：
 */

public class OptionalNoticeEvent {
    public TradeConfigBean.TradeVarietyListBean getTradeVarietyListBean(){
        return mTradeVarietyListBean;
    }

    public void setTradeVarietyListBean(TradeConfigBean.TradeVarietyListBean tradeVarietyListBean) {
        mTradeVarietyListBean = tradeVarietyListBean;
    }

    private TradeConfigBean.TradeVarietyListBean mTradeVarietyListBean;
}
