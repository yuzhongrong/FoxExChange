package com.cjwsc.idcm.model.bean.providerbean;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：
 */

public class CoinTransctionOutConfigBean {

    /**
     * MinQuantity : 0
     * FeeRate : 0
     * MinFee : 0
     */

    private double MinQuantity;
    private double FeeRate;
    private double MinFee;
    private double FixFee;
    private int FeeType;

    public double getFixFee() {
        return FixFee;
    }

    public void setFixFee(double fixFee) {
        FixFee = fixFee;
    }

    public int getFeeType() {
        return FeeType;
    }

    public void setFeeType(int feeType) {
        FeeType = feeType;
    }

    public double getMinQuantity() {
        return MinQuantity;
    }

    public void setMinQuantity(int MinQuantity) {
        this.MinQuantity = MinQuantity;
    }

    public double getFeeRate() {
        return FeeRate;
    }

    public void setFeeRate(int FeeRate) {
        this.FeeRate = FeeRate;
    }

    public double getMinFee() {
        return MinFee;
    }

    public void setMinFee(int MinFee) {
        this.MinFee = MinFee;
    }

}
