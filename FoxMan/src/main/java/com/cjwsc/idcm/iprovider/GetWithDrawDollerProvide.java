package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.WithDrawConfigBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2018/1/1.
 * 功能描述： 体现配置信息
 */

public interface GetWithDrawDollerProvide extends IProvider {
    Flowable<WithDrawConfigBean> getWithDrawDollerProvide(Context context, String assetsID);
}
