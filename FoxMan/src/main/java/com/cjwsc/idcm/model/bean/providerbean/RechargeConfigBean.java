package com.cjwsc.idcm.model.bean.providerbean;

/**
 * Created by ${zipp} on 2017/12/29.
 * 功能描述：充值配置信息
 */

public class RechargeConfigBean {
    private double MinRecharge;
    private double FeeRate ;
    private double MinFee ;

    public double getMinRecharge() {
        return MinRecharge;
    }

    public void setMinRecharge(double minRecharge) {
        MinRecharge = minRecharge;
    }

    public double getFeeRate() {
        return FeeRate;
    }

    public void setFeeRate(double feeRate) {
        FeeRate = feeRate;
    }

    public double getMinFee() {
        return MinFee;
    }

    public void setMinFee(double minFee) {
        MinFee = minFee;
    }
}
