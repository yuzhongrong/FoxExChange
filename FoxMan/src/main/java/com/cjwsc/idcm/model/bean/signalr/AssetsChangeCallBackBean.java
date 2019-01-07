package com.cjwsc.idcm.model.bean.signalr;

/**
 * Created by ${zipp} on 2018/1/3.
 * 功能描述：数组
 */

public class AssetsChangeCallBackBean {

    /**
     * ID : M716wsg8Q0eMmw-ksWkyjA
     * Amount : 0.0
     * FreeAmount : 5490.0
     * NetAmount : 0.0
     * AvailableAmount : 9.9999925E9
     * AssetsType : 1.0
     */

    private String ID;
    private double Amount;
    private double FreeAmount;
    private double NetAmount;
    private double AvailableAmount;
    private double AssetsType;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public double getFreeAmount() {
        return FreeAmount;
    }

    public void setFreeAmount(double FreeAmount) {
        this.FreeAmount = FreeAmount;
    }

    public double getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(double NetAmount) {
        this.NetAmount = NetAmount;
    }

    public double getAvailableAmount() {
        return AvailableAmount;
    }

    public void setAvailableAmount(double AvailableAmount) {
        this.AvailableAmount = AvailableAmount;
    }

    public double getAssetsType() {
        return AssetsType;
    }

    public void setAssetsType(double AssetsType) {
        this.AssetsType = AssetsType;
    }
}
