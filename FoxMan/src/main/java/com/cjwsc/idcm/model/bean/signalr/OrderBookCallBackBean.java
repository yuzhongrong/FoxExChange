package com.cjwsc.idcm.model.bean.signalr;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/31.
 * 功能描述：委托订单(当前委托价格和成交历史价格)
 */

public class OrderBookCallBackBean {

    /**
     * Symbol : BTC/USD
     * Bids : [{"Price":500,"Amount":10,"Quantity":2,"Timestamp":"2018-01-03T21:22:34.7886571+08:00","OrderType":0}]
     * Asks : [{"Price":12000,"Amount":1,"Quantity":1,"Timestamp":"2018-01-03T21:10:38.167909+08:00","OrderType":0},{"Price":11000,"Amount":1,"Quantity":1,"Timestamp":"2018-01-03T21:10:29.8447131+08:00","OrderType":0}]
     */

    private String Symbol;
    private List<HandicapStatisticsBean.BuyHandicapListBean> Bids;
    private List<HandicapStatisticsBean.SellHandicapListBean> Asks;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public List<HandicapStatisticsBean.BuyHandicapListBean> getBids() {
        return Bids;
    }

    public void setBids(List<HandicapStatisticsBean.BuyHandicapListBean> bids) {
        Bids = bids;
    }

    public List<HandicapStatisticsBean.SellHandicapListBean> getAsks() {
        return Asks;
    }

    public void setAsks(List<HandicapStatisticsBean.SellHandicapListBean> asks) {
        Asks = asks;
    }
}
