package com.cjwsc.idcm.event;

/**
 * Created by ${zipp} on 2017/12/31.
 * 功能描述：
 */

public class AddGropEvent {
    public String tradeConfigId;
    public int digits;



    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public String getTradeConfigId() {
        return tradeConfigId;
    }

    public void setTradeConfigId(String tradeConfigId) {
        this.tradeConfigId = tradeConfigId;
    }
}
