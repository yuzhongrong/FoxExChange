package com.cjwsc.idcm.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yuzhongrong on 2017/9/7.
 */

public class WxPayBean {


    /**
     * weixinappData : {"appid":"wx6b853b8b8db80785","noncestr":"D9tuGYFgOMTll5MV","package":"Sign=WXpay","partnerid":"1489304512","prepayid":"wx20170929212130d8a27ee3860269333078","timestamp":1506691282,"sign":"650942600C95127316B4A8C905874D64"}
     */

    private WeixinappDataBean weixinappData;

    public WeixinappDataBean getWeixinappData() {
        return weixinappData;
    }

    public void setWeixinappData(WeixinappDataBean weixinappData) {
        this.weixinappData = weixinappData;
    }

    public static class WeixinappDataBean {
        /**
         * appid : wx6b853b8b8db80785
         * noncestr : D9tuGYFgOMTll5MV
         * package : Sign=WXpay
         * partnerid : 1489304512
         * prepayid : wx20170929212130d8a27ee3860269333078
         * timestamp : 1506691282
         * sign : 650942600C95127316B4A8C905874D64
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
