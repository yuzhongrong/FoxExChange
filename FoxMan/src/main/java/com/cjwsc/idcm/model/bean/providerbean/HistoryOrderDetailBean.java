package com.cjwsc.idcm.model.bean.providerbean;


/**
 * Created by ${zipp} on 2017/12/30.
 * 功能描述：
 */

public class HistoryOrderDetailBean {

        /**
         * OrderID : string
         * OrderNO : string
         * UserID : string
         * FundsFee : 0
         * TradeSide : 0
         * ID : string
         * TradingConfigID : string
         * DealNO : string
         * Price : 0
         * Num : 0
         * TotalAmt : 0
         * CreateTime : 2017-12-30T04:00:06.986Z
         * TriggerType : 1
         * Symbol : string
         */

        private String OrderID;
        private String OrderNO;
        private String UserID;
        private double FundsFee;
        private int TradeSide;
        private String ID;
        private String TradingConfigID;
        private String DealNO;
        private double Price;
        private double Num;
        private double TotalAmt;
        private String CreateTime;
        private int TriggerType;
        private String Symbol;

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public String getOrderNO() {
            return OrderNO;
        }

        public void setOrderNO(String OrderNO) {
            this.OrderNO = OrderNO;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public double getFundsFee() {
            return FundsFee;
        }

        public void setFundsFee(int FundsFee) {
            this.FundsFee = FundsFee;
        }

        public int getTradeSide() {
            return TradeSide;
        }

        public void setTradeSide(int TradeSide) {
            this.TradeSide = TradeSide;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTradingConfigID() {
            return TradingConfigID;
        }

        public void setTradingConfigID(String TradingConfigID) {
            this.TradingConfigID = TradingConfigID;
        }

        public String getDealNO() {
            return DealNO;
        }

        public void setDealNO(String DealNO) {
            this.DealNO = DealNO;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(int Price) {
            this.Price = Price;
        }

        public double getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public double getTotalAmt() {
            return TotalAmt;
        }

        public void setTotalAmt(int TotalAmt) {
            this.TotalAmt = TotalAmt;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getTriggerType() {
            return TriggerType;
        }

        public void setTriggerType(int TriggerType) {
            this.TriggerType = TriggerType;
        }

        public String getSymbol() {
            return Symbol;
        }

        public void setSymbol(String Symbol) {
            this.Symbol = Symbol;
        }

}
