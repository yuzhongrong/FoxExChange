package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.PhoneCheckCodeBean;

import io.reactivex.Flowable;


/**
 * Created by ${zipp} on 2017/12/19.
 * 功能描述：
 */

public interface SendVerifyCodeProvideServices extends IProvider{
    Flowable<PhoneCheckCodeBean> getRegisterPhoneCheck(Context context,String codeKey, String imgCode, String verifyDeviceType, String businessType, String areaCode, String phoneOrEmail);
}
