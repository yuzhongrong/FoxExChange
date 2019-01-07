package com.cjwsc.idcm.event;

/**
 * Created by ${zipp} on 2017/12/28.
 * 功能描述：
 */

public class ZxingEvent {
    private String result;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    private String    businessType;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
