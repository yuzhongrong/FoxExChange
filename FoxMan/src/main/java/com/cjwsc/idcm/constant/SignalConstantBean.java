package com.cjwsc.idcm.constant;

/**
 * Created by ${zipp} on 2017/12/31.
 * 功能描述：获取
 */

public class SignalConstantBean {
    public static String tradeConfigId;
    public static String signalUrl;
    public static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SignalConstantBean.token = token;
    }

    public static String getSignalUrl() {
        return signalUrl;
    }

    public static void setSignalUrl(String signalUrl) {
        SignalConstantBean.signalUrl = signalUrl;
    }

    public static String getTradeConfigId() {
        return tradeConfigId;
    }

    public static void setTradeConfigId(String tradeConfigId) {
        SignalConstantBean.tradeConfigId = tradeConfigId;
    }
}
