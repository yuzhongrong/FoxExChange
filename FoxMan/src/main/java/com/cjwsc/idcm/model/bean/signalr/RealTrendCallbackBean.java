package com.cjwsc.idcm.model.bean.signalr;

/**
 * Created by ${zipp} on 2018/1/2.
 * 功能描述：
 */

public class RealTrendCallbackBean {

    /**
     * Close : 0.8
     * Date : 2017-12-29T17:18:32.707
     * Highest : 282373.0
     * Low : 0.11201
     * Newest : 0.8
     * Open : 0.11816
     * Rise : 0.68184
     * Rose : 5.7704807041299935
     * TradingConfigID : _DSQ3BmslE-cS-HP0POlnA
     */

    private double Close;
    private String Date;
    private double Highest;
    private double Low;
    private double Newest;
    private double Open;
    private double Rise;
    private double Rose;
    private String TradingConfigID;
    private double Last24TradeQuantity ;

    public double getLast24TradeQuantity() {
        return Last24TradeQuantity;
    }

    public void setLast24TradeQuantity(double last24TradeQuantity) {
        Last24TradeQuantity = last24TradeQuantity;
    }

    public double getClose() {
        return Close;
    }

    public void setClose(double Close) {
        this.Close = Close;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public double getHighest() {
        return Highest;
    }

    public void setHighest(double Highest) {
        this.Highest = Highest;
    }

    public double getLow() {
        return Low;
    }

    public void setLow(double Low) {
        this.Low = Low;
    }

    public double getNewest() {
        return Newest;
    }

    public void setNewest(double Newest) {
        this.Newest = Newest;
    }

    public double getOpen() {
        return Open;
    }

    public void setOpen(double Open) {
        this.Open = Open;
    }

    public double getRise() {
        return Rise;
    }

    public void setRise(double Rise) {
        this.Rise = Rise;
    }

    public double getRose() {
        return Rose;
    }

    public void setRose(double Rose) {
        this.Rose = Rose;
    }

    public String getTradingConfigID() {
        return TradingConfigID;
    }

    public void setTradingConfigID(String TradingConfigID) {
        this.TradingConfigID = TradingConfigID;
    }
}
