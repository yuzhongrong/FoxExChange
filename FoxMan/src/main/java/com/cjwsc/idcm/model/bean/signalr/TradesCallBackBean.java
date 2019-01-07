package com.cjwsc.idcm.model.bean.signalr;

/**
 * Created by ${zipp} on 2017/12/31.
 * 功能描述：交易回调类
 */

public class TradesCallBackBean {

    /**
     * CreateTime : 2017-12-31T17:26:12.3048695+08:00
     * Exchange : idcm
     * Id : LXBXQRa-GU2MM1A_Kg3tog
     * InOutside : 1.0
     * Num : 1.0
     * Price : 13050.0
     * Symbol : BTC/USD
     * TtradingConfigID : _DSQ3BmslE-cS-HP3POlnA
     */

    private String CreateTime;
    private String Exchange;
    private String Id;
    private double InOutside;
    private double Num;
    private double Price;
    private String Symbol;
    private String TtradingConfigID;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getExchange() {
        return Exchange;
    }

    public void setExchange(String Exchange) {
        this.Exchange = Exchange;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public double getInOutside() {
        return InOutside;
    }

    public void setInOutside(double InOutside) {
        this.InOutside = InOutside;
    }

    public double getNum() {
        return Num;
    }

    public void setNum(double Num) {
        this.Num = Num;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public String getTtradingConfigID() {
        return TtradingConfigID;
    }

    public void setTtradingConfigID(String TtradingConfigID) {
        this.TtradingConfigID = TtradingConfigID;
    }
}
