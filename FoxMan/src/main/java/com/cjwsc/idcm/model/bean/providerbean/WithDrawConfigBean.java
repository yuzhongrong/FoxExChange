package com.cjwsc.idcm.model.bean.providerbean;

/**
 * Created by ${zipp} on 2018/1/1.
 * 功能描述：
 */

public class WithDrawConfigBean {
    private double MinWithdraw ;
    private double FeeRate ;
    private double MinFee ;
    private double FixFee;
    private double FeeType;

    public double getFixFee() {
        return FixFee;
    }

    public void setFixFee(double fixFee) {
        FixFee = fixFee;
    }

    public double getFeeType() {
        return FeeType;
    }

    public void setFeeType(double feeType) {
        FeeType = feeType;
    }

    public double getMinWithdraw() {
        return MinWithdraw;
    }

    public void setMinWithdraw(double minWithdraw) {
        MinWithdraw = minWithdraw;
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
