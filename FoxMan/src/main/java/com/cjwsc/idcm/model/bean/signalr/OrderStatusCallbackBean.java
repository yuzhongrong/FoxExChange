package com.cjwsc.idcm.model.bean.signalr;

/**
 * Created by ${zipp} on 2017/12/8.
 * 功能描述： 这个状态后台是返回要给集合
 */

public class OrderStatusCallbackBean {

    /**
     * Symbol : BTC/USD
     * TradeSide : 0.0
     * Price : 500.0
     * TotalNum : 5.0
     * Num : 5.0
     * Status : 0.0
     * OrderNO : rZ4g1fBH
     * ID : rZ4g1fBHTkyhEuF9k1gWxw
     * CreateTime : 2018-01-03T21:22:34.7886571+08:00
     * DealNum : 0.0
     * OrderType : 1.0
     * TradingConfigID : _DSQ3BmslE-cS-HP3POlnA
     */

    private String Symbol;
    private double TradeSide;
    private double Price;
    private double TotalNum;
    private double Num;
    private double Status;
    private String OrderNO;
    private String ID;
    private String CreateTime;
    private double DealNum;
    private double OrderType;
    private String TradingConfigID;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public double getTradeSide() {
        return TradeSide;
    }

    public void setTradeSide(double TradeSide) {
        this.TradeSide = TradeSide;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(double TotalNum) {
        this.TotalNum = TotalNum;
    }

    public double getNum() {
        return Num;
    }

    public void setNum(double Num) {
        this.Num = Num;
    }

    public double getStatus() {
        return Status;
    }

    public void setStatus(double Status) {
        this.Status = Status;
    }

    public String getOrderNO() {
        return OrderNO;
    }

    public void setOrderNO(String OrderNO) {
        this.OrderNO = OrderNO;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public double getDealNum() {
        return DealNum;
    }

    public void setDealNum(double DealNum) {
        this.DealNum = DealNum;
    }

    public double getOrderType() {
        return OrderType;
    }

    public void setOrderType(double OrderType) {
        this.OrderType = OrderType;
    }

    public String getTradingConfigID() {
        return TradingConfigID;
    }

    public void setTradingConfigID(String TradingConfigID) {
        this.TradingConfigID = TradingConfigID;
    }
}
