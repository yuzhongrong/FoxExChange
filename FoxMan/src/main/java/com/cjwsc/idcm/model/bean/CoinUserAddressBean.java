package com.cjwsc.idcm.model.bean;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/28.
 * 功能描述：
 */

public class CoinUserAddressBean {

    private String CoinID;
    private String UserID;
    private String Address;
    private String CreatorID;
    private String CreateTime;
    private boolean IsEnable;
    private String Remark;
    private CoinBean Coin;
    private String ID;

    public String getCoinID() {
        return CoinID;
    }

    public void setCoinID(String CoinID) {
        this.CoinID = CoinID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCreatorID() {
        return CreatorID;
    }

    public void setCreatorID(String CreatorID) {
        this.CreatorID = CreatorID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public boolean isIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(boolean IsEnable) {
        this.IsEnable = IsEnable;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public CoinBean getCoin() {
        return Coin;
    }

    public void setCoin(CoinBean Coin) {
        this.Coin = Coin;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public static class CoinBean {
        /**
         * Code : string
         * Name : string
         * FeeRate : 0
         * Image : string
         * IP : string
         * Port : string
         * Account : string
         * UnitPrecision : 0
         * Pwd : string
         * SortNumber : 0
         * Circulation : 0
         * IsSupportWallet : true
         * Description : string
         * IsListing : true
         * CreatorID : string
         * CreateTime : 2017-12-28T02:06:15.033Z
         * LastUpdateUserID : string
         * LastUpdateTime : 2017-12-28T02:06:15.033Z
         * ID : string
         */

        private String Code;
        private String Name;
        private int FeeRate;
        private String Image;
        private String IP;
        private String Port;
        private String Account;
        private int UnitPrecision;
        private String Pwd;
        private int SortNumber;
        private int Circulation;
        private boolean IsSupportWallet;
        private String Description;
        private boolean IsListing;
        private String CreatorID;
        private String CreateTime;
        private String LastUpdateUserID;
        private String LastUpdateTime;
        private String ID;

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getFeeRate() {
            return FeeRate;
        }

        public void setFeeRate(int FeeRate) {
            this.FeeRate = FeeRate;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public String getPort() {
            return Port;
        }

        public void setPort(String Port) {
            this.Port = Port;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public int getUnitPrecision() {
            return UnitPrecision;
        }

        public void setUnitPrecision(int UnitPrecision) {
            this.UnitPrecision = UnitPrecision;
        }

        public String getPwd() {
            return Pwd;
        }

        public void setPwd(String Pwd) {
            this.Pwd = Pwd;
        }

        public int getSortNumber() {
            return SortNumber;
        }

        public void setSortNumber(int SortNumber) {
            this.SortNumber = SortNumber;
        }

        public int getCirculation() {
            return Circulation;
        }

        public void setCirculation(int Circulation) {
            this.Circulation = Circulation;
        }

        public boolean isIsSupportWallet() {
            return IsSupportWallet;
        }

        public void setIsSupportWallet(boolean IsSupportWallet) {
            this.IsSupportWallet = IsSupportWallet;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public boolean isIsListing() {
            return IsListing;
        }

        public void setIsListing(boolean IsListing) {
            this.IsListing = IsListing;
        }

        public String getCreatorID() {
            return CreatorID;
        }

        public void setCreatorID(String CreatorID) {
            this.CreatorID = CreatorID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getLastUpdateUserID() {
            return LastUpdateUserID;
        }

        public void setLastUpdateUserID(String LastUpdateUserID) {
            this.LastUpdateUserID = LastUpdateUserID;
        }

        public String getLastUpdateTime() {
            return LastUpdateTime;
        }

        public void setLastUpdateTime(String LastUpdateTime) {
            this.LastUpdateTime = LastUpdateTime;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

}
