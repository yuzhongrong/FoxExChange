package com.cjwsc.idcm.model.bean;

import java.util.List;

/**
 * Created by liaoyunxia on 17/9/7.
 */

public class OpenGroupDetailBean {

    private long createTime;
    private long endTime;
    private int openGroupPersionId;
    private String openGroupPersionUrl;
    private int openNum;//开团差数
    private int groupPersionNum;//需要的组团人数
    private List<OpenGroupChildsBean> openGroupChilds;
    private int openStatus;//开团状态 0 开团中 1 已开团 2 开团失败
    private String openGroupPersionName;
    /**
     * 手动添加 自己是否在拼团列表里
     */
    private boolean inOpenGroupChilds;

    /**
     * 手动添加 是否登录
     * @return
     */
    private boolean isLogin;

    /**
     * 手动添加 拼团ID
     */
    private int groupId;

    /**
     * 收到添加 当前服务器时间
     */
    private long currentServerTime;

    /**
     * 手动添加商品信息
     */
    /*商品名称*/
    private String productName;
    /*商品简介*/
    private String productDesc;
    /*拼团价*/
    private double price;
    /*主图*/
    private String mainPic;
    /*用户角色*/
    private int user_role;
    /*用户Id*/
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public long getCurrentServerTime() {
        return currentServerTime;
    }

    public void setCurrentServerTime(long currentServerTime) {
        this.currentServerTime = currentServerTime;
    }

    private String wapHostShare;

    public String getWapHostShare() {
        return wapHostShare;
    }

    public void setWapHostShare(String wapHostShare) {
        this.wapHostShare = wapHostShare;
    }

    public String getOpenGroupPersionName() {
        return openGroupPersionName;
    }

    public void setOpenGroupPersionName(String openGroupPersionName) {
        this.openGroupPersionName = openGroupPersionName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public int getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }

    public boolean isInOpenGroupChilds() {
        return inOpenGroupChilds;
    }

    public void setInOpenGroupChilds(boolean inOpenGroupChilds) {
        this.inOpenGroupChilds = inOpenGroupChilds;
    }

    public int getGroupPersionNum() {
        return groupPersionNum;
    }

    public void setGroupPersionNum(int groupPersionNum) {
        this.groupPersionNum = groupPersionNum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getOpenGroupPersionId() {
        return openGroupPersionId;
    }

    public void setOpenGroupPersionId(int openGroupPersionId) {
        this.openGroupPersionId = openGroupPersionId;
    }
    public String getOpenGroupPersionUrl() {
        return openGroupPersionUrl;
    }

    public void setOpenGroupPersionUrl(String openGroupPersionUrl) {
        this.openGroupPersionUrl = openGroupPersionUrl;
    }

    public int getOpenNum() {
        return openNum;
    }

    public void setOpenNum(int openNum) {
        this.openNum = openNum;
    }

    public List<OpenGroupChildsBean> getOpenGroupChilds() {
        return openGroupChilds;
    }

    public void setOpenGroupChilds(List<OpenGroupChildsBean> openGroupChilds) {
        this.openGroupChilds = openGroupChilds;
    }

    public static class OpenGroupChildsBean {

        private int tourPersionId;
        private String tourPersionName;
        private String tourPersionUrl;
        private long tourTime;//跟团时间
        private boolean isUnkownPersonUrl;

        public boolean isUnkownPersonUrl() {
            return isUnkownPersonUrl;
        }

        public void setUnkownPersonUrl(boolean unkownPersonUrl) {
            isUnkownPersonUrl = unkownPersonUrl;
        }

        public long getTourTime() {
            return tourTime;
        }

        public void setTourTime(long tourTime) {
            this.tourTime = tourTime;
        }

        public int getTourPersionId() {
            return tourPersionId;
        }

        public void setTourPersionId(int tourPersionId) {
            this.tourPersionId = tourPersionId;
        }

        public String getTourPersionName() {
            return tourPersionName;
        }

        public void setTourPersionName(String tourPersionName) {
            this.tourPersionName = tourPersionName;
        }

        public String getTourPersionUrl() {
            return tourPersionUrl;
        }

        public void setTourPersionUrl(String tourPersionUrl) {
            this.tourPersionUrl = tourPersionUrl;
        }
    }
}
