package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.FindPsFinalBean;

import io.reactivex.Flowable;


/**
 * Created by ${zipp} on 2017/12/20.
 * 功能描述：
 */

public interface FindLoginPassWordFinalProviderSer extends IProvider {
    Flowable<FindPsFinalBean> foundPassWordFinal(Context context, String VerifyCode, String AreaCode, String PhoneOrEmail, String Password, String ConfirmPassword);
}
