package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.UserAssetPropertyBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/21.
 * 功能描述：
 */

public interface AccountPropertyTotalProviderService extends IProvider {
    /**
     * 获取用户总资产
     * @param context
     * @return
     */
    Flowable<UserAssetPropertyBean> getUserTotalProperty(Context context);
}
