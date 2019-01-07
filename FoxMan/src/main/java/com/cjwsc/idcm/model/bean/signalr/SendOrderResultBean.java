package com.cjwsc.idcm.model.bean.signalr;

/**
 * Created by ${zipp} on 2018/1/3.
 * 功能描述：数组
 */

public class SendOrderResultBean {

    /**
     * Order : {"UserID":"JfXTa3Zsy0mqdR7U0JJJaA","OrderNO":"rZ4g1fBH","TradingConfigID":"_DSQ3BmslE-cS-HP3POlnA","Price":500,"TotalNum":5,"Num":5,"DealNum":0,"DealPrice":0,"TotalAmt":2500,"SurplusFreeAssets":2500,"TradingFundsFeeRate":0,"TradingQuantityFeeRate":0,"Status":0,"DealAmt":0,"CreatorID":"JfXTa3Zsy0mqdR7U0JJJaA","CreateTime":"2018-01-03T21:22:34.7886571+08:00","Side":0,"CacheVersion":503,"OrderType":1,"AvgExecutionPrice":0,"TradingFundsFee":0,"TradingQuantityFee":0,"TempTradingFundsFee":0,"TempTradingQuantityFee":0,"TempDealNum":0,"ID":"rZ4g1fBHTkyhEuF9k1gWxw"}
     * Status : true
     * StatusCode : 200
     */

    private OrderBean Order;
    private boolean Status;
    private String StatusCode;

    public OrderBean getOrder() {
        return Order;
    }

    public void setOrder(OrderBean Order) {
        this.Order = Order;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public static class OrderBean {
        /**
         * UserID : JfXTa3Zsy0mqdR7U0JJJaA
         * OrderNO : rZ4g1fBH
         * TradingConfigID : _DSQ3BmslE-cS-HP3POlnA
         * Price : 500.0
         * TotalNum : 5.0
         * Num : 5.0
         * DealNum : 0.0
         * DealPrice : 0.0
         * TotalAmt : 2500.0
         * SurplusFreeAssets : 2500.0
         * TradingFundsFeeRate : 0.0
         * TradingQuantityFeeRate : 0.0
         * Status : 0.0
         * DealAmt : 0.0
         * CreatorID : JfXTa3Zsy0mqdR7U0JJJaA
         * CreateTime : 2018-01-03T21:22:34.7886571+08:00
         * Side : 0.0
         * CacheVersion : 503.0
         * OrderType : 1.0
         * AvgExecutionPrice : 0.0
         * TradingFundsFee : 0.0
         * TradingQuantityFee : 0.0
         * TempTradingFundsFee : 0.0
         * TempTradingQuantityFee : 0.0
         * TempDealNum : 0.0
         * ID : rZ4g1fBHTkyhEuF9k1gWxw
         */

        private String UserID;
        private String OrderNO;
        private String TradingConfigID;
        private double Price;
        private double TotalNum;
        private double Num;
        private double DealNum;
        private double DealPrice;
        private double TotalAmt;
        private double SurplusFreeAssets;
        private double TradingFundsFeeRate;
        private double TradingQuantityFeeRate;
        private double Status;
        private double DealAmt;
        private String CreatorID;
        private String CreateTime;
        private double Side;
        private double CacheVersion;
        private double OrderType;
        private double AvgExecutionPrice;
        private double TradingFundsFee;
        private double TradingQuantityFee;
        private double TempTradingFundsFee;
        private double TempTradingQuantityFee;
        private double TempDealNum;
        private String ID;

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getOrderNO() {
            return OrderNO;
        }

        public void setOrderNO(String OrderNO) {
            this.OrderNO = OrderNO;
        }

        public String getTradingConfigID() {
            return TradingConfigID;
        }

        public void setTradingConfigID(String TradingConfigID) {
            this.TradingConfigID = TradingConfigID;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public double getTotalNum() {
            return TotalNum;
        }

        public void setTotalNum(double TotalNum) {
            this.TotalNum = TotalNum;
        }

        public double getNum() {
            return Num;
        }

        public void setNum(double Num) {
            this.Num = Num;
        }

        public double getDealNum() {
            return DealNum;
        }

        public void setDealNum(double DealNum) {
            this.DealNum = DealNum;
        }

        public double getDealPrice() {
            return DealPrice;
        }

        public void setDealPrice(double DealPrice) {
            this.DealPrice = DealPrice;
        }

        public double getTotalAmt() {
            return TotalAmt;
        }

        public void setTotalAmt(double TotalAmt) {
            this.TotalAmt = TotalAmt;
        }

        public double getSurplusFreeAssets() {
            return SurplusFreeAssets;
        }

        public void setSurplusFreeAssets(double SurplusFreeAssets) {
            this.SurplusFreeAssets = SurplusFreeAssets;
        }

        public double getTradingFundsFeeRate() {
            return TradingFundsFeeRate;
        }

        public void setTradingFundsFeeRate(double TradingFundsFeeRate) {
            this.TradingFundsFeeRate = TradingFundsFeeRate;
        }

        public double getTradingQuantityFeeRate() {
            return TradingQuantityFeeRate;
        }

        public void setTradingQuantityFeeRate(double TradingQuantityFeeRate) {
            this.TradingQuantityFeeRate = TradingQuantityFeeRate;
        }

        public double getStatus() {
            return Status;
        }

        public void setStatus(double Status) {
            this.Status = Status;
        }

        public double getDealAmt() {
            return DealAmt;
        }

        public void setDealAmt(double DealAmt) {
            this.DealAmt = DealAmt;
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

        public double getSide() {
            return Side;
        }

        public void setSide(double Side) {
            this.Side = Side;
        }

        public double getCacheVersion() {
            return CacheVersion;
        }

        public void setCacheVersion(double CacheVersion) {
            this.CacheVersion = CacheVersion;
        }

        public double getOrderType() {
            return OrderType;
        }

        public void setOrderType(double OrderType) {
            this.OrderType = OrderType;
        }

        public double getAvgExecutionPrice() {
            return AvgExecutionPrice;
        }

        public void setAvgExecutionPrice(double AvgExecutionPrice) {
            this.AvgExecutionPrice = AvgExecutionPrice;
        }

        public double getTradingFundsFee() {
            return TradingFundsFee;
        }

        public void setTradingFundsFee(double TradingFundsFee) {
            this.TradingFundsFee = TradingFundsFee;
        }

        public double getTradingQuantityFee() {
            return TradingQuantityFee;
        }

        public void setTradingQuantityFee(double TradingQuantityFee) {
            this.TradingQuantityFee = TradingQuantityFee;
        }

        public double getTempTradingFundsFee() {
            return TempTradingFundsFee;
        }

        public void setTempTradingFundsFee(double TempTradingFundsFee) {
            this.TempTradingFundsFee = TempTradingFundsFee;
        }

        public double getTempTradingQuantityFee() {
            return TempTradingQuantityFee;
        }

        public void setTempTradingQuantityFee(double TempTradingQuantityFee) {
            this.TempTradingQuantityFee = TempTradingQuantityFee;
        }

        public double getTempDealNum() {
            return TempDealNum;
        }

        public void setTempDealNum(double TempDealNum) {
            this.TempDealNum = TempDealNum;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }
}
