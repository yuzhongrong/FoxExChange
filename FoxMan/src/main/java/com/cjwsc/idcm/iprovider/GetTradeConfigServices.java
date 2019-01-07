package com.cjwsc.idcm.iprovider;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.TradeConfigBean;

import io.reactivex.Flowable;

/**
 *
 * @project name : IDCM2.1
 * @class name :   GetTradeConfigServices
 * @package name : com.cjwsc.idcm.iprovider
 * @author :       MayerXu10000@gamil.com
 * @date :         2018/2/28 11:32
 * @describe :     交易配置信息
 *
 */
public interface GetTradeConfigServices extends IProvider {
    Flowable<String> getTradeConfig();
}
