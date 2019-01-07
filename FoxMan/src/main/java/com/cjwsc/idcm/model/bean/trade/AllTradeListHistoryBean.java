package com.cjwsc.idcm.model.bean.trade;


/**
 * Created by ${zipp} on 2018/2/4.
 * 功能描述：
 */

public class AllTradeListHistoryBean {


    private String ID;
    private String TradingConfigID;
    private double Price;
    private double Num;
    private String CreateTime;


    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    private boolean isUp=true;




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTradingConfigID() {
        return TradingConfigID;
    }

    public void setTradingConfigID(String tradingConfigID) {
        TradingConfigID = tradingConfigID;
    }



    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getNum() {
        return Num;
    }

    public void setNum(double num) {
        Num = num;
    }


    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }


}
