package com.cjwsc.idcm.model.bean.providerbean;


/**
 * Created by ${zipp} on 2018/3/6.
 * 功能描述：
 */

public class TradeStrategyBean {


        /**
         * TradeGroupID : string
         * TradingConfigID : string
         * ApplyFeeRate : 0
         * TriggerFeeRate : 0
         * IsEnable : true
         * CreateTime : 2018-03-06T08:04:17.327Z
         * CreatorID : string
         * LastUpdateTime : 2018-03-06T08:04:17.327Z
         * LastUpdateUserID : string
         * ID : string
         */

        private String TradeGroupID;
        private String TradingConfigID;
        private double ApplyFeeRate;
        private double TriggerFeeRate;
        private boolean IsEnable;
        private String CreateTime;
        private String CreatorID;
        private String LastUpdateTime;
        private String LastUpdateUserID;
        private String ID;

        public String getTradeGroupID() {
            return TradeGroupID;
        }

        public void setTradeGroupID(String TradeGroupID) {
            this.TradeGroupID = TradeGroupID;
        }

        public String getTradingConfigID() {
            return TradingConfigID;
        }

        public void setTradingConfigID(String TradingConfigID) {
            this.TradingConfigID = TradingConfigID;
        }

        public double getApplyFeeRate() {
            return ApplyFeeRate;
        }

        public void setApplyFeeRate(double ApplyFeeRate) {
            this.ApplyFeeRate = ApplyFeeRate;
        }

        public double getTriggerFeeRate() {
            return TriggerFeeRate;
}

        public void setTriggerFeeRate(double TriggerFeeRate) {
            this.TriggerFeeRate = TriggerFeeRate;
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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

}
