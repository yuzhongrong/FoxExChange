package com.cjwsc.idcm.model.bean.providerbean;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/21.
 * 功能描述： 用户总资产
 */

public class UserAssetPropertyBean {

    /**
     * UserID : 4RiYsbJudUat11plfQ99PA
     * TotalRMB : 0
     * TotalUSD : 0
     * Assets : []
     */

    private String UserID;
    private double TotalRMB;
    private double TotalUSD;
    private double TotalHKD;
    private double TotalBTC;

    public double getTotalUSD() {
        return TotalUSD;
    }

    public void setTotalUSD(double totalUSD) {
        TotalUSD = totalUSD;
    }

    private List<AssetsDTO> Assets;

    public double getTotalHKD() {
        return TotalHKD;
    }

    public void setTotalHKD(double totalHKD) {
        TotalHKD = totalHKD;
    }

    public double getTotalBTC() {
        return TotalBTC;
    }

    public void setTotalBTC(double totalBTC) {
        TotalBTC = totalBTC;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public double getTotalRMB() {
        return TotalRMB;
    }

    public void setTotalRMB(int TotalRMB) {
        this.TotalRMB = TotalRMB;
    }


    public List<AssetsDTO> getAssets() {
        return Assets;
    }

    public void setAssets(List<AssetsDTO> Assets) {
        this.Assets = Assets;
    }

        public static class AssetsDTO{
            private String ID;
            private String AssetsName;
            private String Symbol;
            private String Amount;
            private String FreeAmount;
            private String NetAmount;
            private double AvailableAmount;
            private String AssetsType;
            private String AssetsCode;
            private double FeeRate;
            private String AssetsTag;
            private String Logo;
            private int Precision;

            public int getPrecision() {
                return Precision;
            }

            public void setPrecision(int precision) {
                Precision = precision;
            }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String logo) {
            Logo = logo;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getAssetsName() {
            return AssetsName;
        }

        public void setAssetsName(String assetsName) {
            AssetsName = assetsName;
        }

        public String getSymbol() {
            return Symbol;
        }

        public void setSymbol(String symbol) {
            Symbol = symbol;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }

        public String getFreeAmount() {
            return FreeAmount;
        }

        public void setFreeAmount(String freeAmount) {
            FreeAmount = freeAmount;
        }

        public String getNetAmount() {
            return NetAmount;
        }

        public void setNetAmount(String netAmount) {
            NetAmount = netAmount;
        }

        public double getAvailableAmount() {
            return AvailableAmount;
        }

        public void setAvailableAmount(double availableAmount) {
            AvailableAmount = availableAmount;
        }

        public String getAssetsType() {
            return AssetsType;
        }

        public void setAssetsType(String assetsType) {
            AssetsType = assetsType;
        }

        public String getAssetsCode() {
            return AssetsCode;
        }

        public void setAssetsCode(String assetsCode) {
            AssetsCode = assetsCode;
        }

        public Double getFeeRate() {
            return FeeRate;
        }

        public void setFeeRate(double feeRate) {
            FeeRate = feeRate;
        }

        public String getAssetsTag() {
            return AssetsTag;
        }

        public void setAssetsTag(String assetsTag) {
            AssetsTag = assetsTag;
        }
    }

}
