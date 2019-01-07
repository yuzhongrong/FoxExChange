package com.cjwsc.idcm.event;

import com.cjwsc.idcm.model.bean.signalr.AssetsChangeCallBackBean;

import java.util.List;

/**
 * Created by ${zipp} on 2018/1/4.
 * 功能描述：
 */

public class AssetsChangeCallbackEvent {
    List<AssetsChangeCallBackBean> mChangeCallBackBeanList;

    public List<AssetsChangeCallBackBean> getChangeCallBackBeanList() {
        return mChangeCallBackBeanList;
    }

    public void setChangeCallBackBeanList(List<AssetsChangeCallBackBean> changeCallBackBeanList) {
        mChangeCallBackBeanList = changeCallBackBeanList;
    }
}
