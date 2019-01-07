package com.cjwsc.idcm.model.bean.mineBean;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：
 */

public class BankCardListBean {

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class ExtraBean {
    }

    public static class DataBean {
        /**
         * BankName : string
         * CardNumber : string
         * CardUserName : string
         * Branch : string
         * Province : string
         * City : string
         * UserID : string
         * IsDefault : true
         * LastUpdateTime : 2017-12-27T07:37:37.859Z
         * LastUpdateUserID : string
         * IsEnable : true
         * CreateTime : 2017-12-27T07:37:37.860Z
         * CreatorID : string
         * SWIFTCode : string
         * ID : string
         */

        private String BankName;
        private String CardNumber;
        private String CardUserName;
        private String Branch;
        private String Province;
        private String City;
        private String UserID;
        private boolean IsDefault;
        private String LastUpdateTime;
        private String LastUpdateUserID;
        private boolean IsEnable;
        private String CreateTime;
        private String CreatorID;
        private String SWIFTCode;
        private String ID;

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getCardNumber() {
            return CardNumber;
        }

        public void setCardNumber(String CardNumber) {
            this.CardNumber = CardNumber;
        }

        public String getCardUserName() {
            return CardUserName;
        }

        public void setCardUserName(String CardUserName) {
            this.CardUserName = CardUserName;
        }

        public String getBranch() {
            return Branch;
        }

        public void setBranch(String Branch) {
            this.Branch = Branch;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public boolean isIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(boolean IsDefault) {
            this.IsDefault = IsDefault;
        }

        public String getLastUpdateTime() {
            return LastUpdateTime;
        }

        public void setLastUpdateTime(String LastUpdateTime) {
            this.LastUpdateTime = LastUpdateTime;
        }

        public String getLastUpdateUserID() {
            return LastUpdateUserID;
        }

        public void setLastUpdateUserID(String LastUpdateUserID) {
            this.LastUpdateUserID = LastUpdateUserID;
        }

        public boolean isIsEnable() {
            return IsEnable;
        }

        public void setIsEnable(boolean IsEnable) {
            this.IsEnable = IsEnable;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCreatorID() {
            return CreatorID;
        }

        public void setCreatorID(String CreatorID) {
            this.CreatorID = CreatorID;
        }

        public String getSWIFTCode() {
            return SWIFTCode;
        }

        public void setSWIFTCode(String SWIFTCode) {
            this.SWIFTCode = SWIFTCode;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }
}
