package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.FinePSFirstBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/20.
 * 功能描述：
 */

public interface FoundLoginPassWordFirstProviderSer extends IProvider {
    Flowable<FinePSFirstBean> foundPassWordFirst(Context context, String verifyDeviceType, String areaCode, String phoneOrEmail, String verifyCode);
}
