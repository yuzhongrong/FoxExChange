package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.SecurityStrategyBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：
 */

public interface GetSecurityStrategyProvider extends IProvider{
    Flowable<SecurityStrategyBean> getSecurityStrategy(Context context);
}
