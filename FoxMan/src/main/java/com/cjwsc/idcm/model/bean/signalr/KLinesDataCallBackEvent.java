package com.cjwsc.idcm.model.bean.signalr;

import com.cjwsc.idcm.model.bean.mineBean.KLineDataBean;

import java.util.List;

/**
 * Created by ${zipp} on 2018/1/4.
 * 功能描述：
 */

public class KLinesDataCallBackEvent  {
    List<KLineDataBean> mLinesDataCallBackBeans;

    public List<KLineDataBean> getLinesDataCallBackBeans() {
        return mLinesDataCallBackBeans;
    }

    public void setLinesDataCallBackBeans(List<KLineDataBean> linesDataCallBackBeans) {
        mLinesDataCallBackBeans = linesDataCallBackBeans;
    }
}
