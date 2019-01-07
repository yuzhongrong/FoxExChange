package com.cjwsc.idcm.model.bean.trade;


/**
 * Created by ${zipp} on 2017/12/29.
 * 功能描述：当前委托订单
 */

public class CurrentEntruseBean {


    /**
     * CreateTime : 2018-01-04T09:35:21.6675988+08:00
     * DealNum : 0.0
     * ID : HT8Og9ZP40mW7lnrrVQifQ
     * Num : 5.0
     * OrderNO : HT8Og9ZP
     * OrderType : 1.0
     * Price : 500.0
     * Status : 0.0
     * Symbol : BTC/USD
     * TotalNum : 5.0
     * TradeSide : 0.0
     * TradingConfigID : _DSQ3BmslE-cS-HP3POlnA
     */

    private String CreateTime;
    private double DealNum;
    private String ID;
    private double Num;
    private String OrderNO;
    private int OrderType;
    private double Price;
    private int Status;
    private String Symbol;
    private double TotalNum;
    private int TradeSide;
    private String TradingConfigID;

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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getNum() {
        return Num;
    }

    public void setNum(double Num) {
        this.Num = Num;
    }

    public String getOrderNO() {
        return OrderNO;
    }

    public void setOrderNO(String OrderNO) {
        this.OrderNO = OrderNO;
    }

    public double getOrderType() {
        return OrderType;
    }

    public void setOrderType(int OrderType) {
        this.OrderType = OrderType;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public double getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(double TotalNum) {
        this.TotalNum = TotalNum;
    }

    public int getTradeSide() {
        return TradeSide;
    }

    public void setTradeSide(int TradeSide) {
        this.TradeSide = TradeSide;
    }

    public String getTradingConfigID() {
        return TradingConfigID;
    }

    public void setTradingConfigID(String TradingConfigID) {
        this.TradingConfigID = TradingConfigID;
    }
}
