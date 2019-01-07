package com.cjwsc.idcm.event;

import com.cjwsc.idcm.model.bean.signalr.RealTrendCallbackBean;

import java.util.List;

/**
 * Created by ${zipp} on 2018/1/4.
 * 功能描述：推送实时行情数据
 */

public class RealTreandCallBackEvent {
    List<RealTrendCallbackBean> mRealTrendCallbackBeanList;

    public List<RealTrendCallbackBean> getRealTrendCallbackBeanList() {
        return mRealTrendCallbackBeanList;
    }

    public void setRealTrendCallbackBeanList(List<RealTrendCallbackBean> realTrendCallbackBeanList) {
        mRealTrendCallbackBeanList = realTrendCallbackBeanList;
    }
}
