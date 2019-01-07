package com.cjwsc.idcm.event;

import com.cjwsc.idcm.model.bean.trade.AllTradeListHistoryBean;

import java.util.List;

/**
 * Created by ${zipp} on 2018/3/2.
 * 功能描述：
 */

public class AllTradeHistoryEvent {
    List<AllTradeListHistoryBean> allTradeListHistoryBeans;

    public List<AllTradeListHistoryBean> getAllTradeListHistoryBeans() {
        return allTradeListHistoryBeans;
    }

    public void setAllTradeListHistoryBeans(List<AllTradeListHistoryBean> allTradeListHistoryBeans) {
        this.allTradeListHistoryBeans = allTradeListHistoryBeans;
    }
}
