package com.cjwsc.idcm.model.bean.providerbean;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：安全策略
 */

public class SecurityStrategyBean {
    private String UserId;
    private int LoginVerifyType ;
    private String LastLoginVerifyTime ;
    private int TranVerifyType ;
    private String LastTranVerifyTypeTime  ;
    private int CashOutVerifyType  ;
    private String LastCashOutVerifyTime   ;
    private String ID    ;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getLoginVerifyType() {
        return LoginVerifyType;
    }

    public void setLoginVerifyType(int loginVerifyType) {
        LoginVerifyType = loginVerifyType;
    }

    public String getLastLoginVerifyTime() {
        return LastLoginVerifyTime;
    }

    public void setLastLoginVerifyTime(String lastLoginVerifyTime) {
        LastLoginVerifyTime = lastLoginVerifyTime;
    }

    public int getTranVerifyType() {
        return TranVerifyType;
    }

    public void setTranVerifyType(int tranVerifyType) {
        TranVerifyType = tranVerifyType;
    }

    public String getLastTranVerifyTypeTime() {
        return LastTranVerifyTypeTime;
    }

    public void setLastTranVerifyTypeTime(String lastTranVerifyTypeTime) {
        LastTranVerifyTypeTime = lastTranVerifyTypeTime;
    }

    public int getCashOutVerifyType() {
        return CashOutVerifyType;
    }

    public void setCashOutVerifyType(int cashOutVerifyType) {
        CashOutVerifyType = cashOutVerifyType;
    }

    public String getLastCashOutVerifyTime() {
        return LastCashOutVerifyTime;
    }

    public void setLastCashOutVerifyTime(String lastCashOutVerifyTime) {
        LastCashOutVerifyTime = lastCashOutVerifyTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
