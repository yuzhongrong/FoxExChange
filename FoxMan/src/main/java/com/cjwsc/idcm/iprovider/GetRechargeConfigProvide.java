package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.RechargeConfigBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：充值
 */

public interface GetRechargeConfigProvide extends IProvider{
    Flowable<RechargeConfigBean> getRechargeConfigProvide(Context context, String assetsID);
}
