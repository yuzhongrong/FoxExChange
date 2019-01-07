package com.cjwsc.idcm.manager.TongLianPayManager;

public class TonlianInfoPayResp{


    /**
     * quickappData : {"inputCharset":1,"receiveUrl":"http://portalservice.pt.wmjtc.com/pay/notifypay/quickwapNotify","version":"v1.0","signType":0,"merchantId":"2000944035311411817","orderNo":"11104170929365669188","orderAmount":4150,"orderCurrency":0,"orderDatetime":"20170929191533","productName":"农特产APP购物","ext1":"<USER>170926308530823<\/USER>","payType":33,"sign":"1C6B194FAEC01468576881247373E189"}
     */

    private QuickappDataBean quickappData;

    public QuickappDataBean getQuickappData() {
        return quickappData;
    }

    public void setQuickappData(QuickappDataBean quickappData) {
        this.quickappData = quickappData;
    }

    public static class QuickappDataBean {
        /**
         * inputCharset : 1
         * receiveUrl : http://portalservice.pt.wmjtc.com/pay/notifypay/quickwapNotify
         * version : v1.0
         * signType : 0
         * merchantId : 2000944035311411817
         * orderNo : 11104170929365669188
         * orderAmount : 4150
         * orderCurrency : 0
         * orderDatetime : 20170929191533
         * productName : 农特产APP购物
         * ext1 : <USER>170926308530823</USER>
         * payType : 33
         * sign : 1C6B194FAEC01468576881247373E189
         */

        private int inputCharset;
        private String receiveUrl;
        private String version;
        private int signType;
        private String merchantId;
        private String orderNo;
        private int orderAmount;
        private int orderCurrency;
        private String orderDatetime;
        private String productName;
        private String ext1;
        private int payType;
        private String sign;

        public int getInputCharset() {
            return inputCharset;
        }

        public void setInputCharset(int inputCharset) {
            this.inputCharset = inputCharset;
        }

        public String getReceiveUrl() {
            return receiveUrl;
        }

        public void setReceiveUrl(String receiveUrl) {
            this.receiveUrl = receiveUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getSignType() {
            return signType;
        }

        public void setSignType(int signType) {
            this.signType = signType;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getOrderCurrency() {
            return orderCurrency;
        }

        public void setOrderCurrency(int orderCurrency) {
            this.orderCurrency = orderCurrency;
        }

        public String getOrderDatetime() {
            return orderDatetime;
        }

        public void setOrderDatetime(String orderDatetime) {
            this.orderDatetime = orderDatetime;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getExt1() {
            return ext1;
        }

        public void setExt1(String ext1) {
            this.ext1 = ext1;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}