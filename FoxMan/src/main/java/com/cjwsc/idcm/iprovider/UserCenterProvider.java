package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.AccountLoginBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2018/1/31.
 * 功能描述：
 */

public interface UserCenterProvider extends IProvider{
    Flowable<AccountLoginBean> getUserCenterData(Context context);
}
