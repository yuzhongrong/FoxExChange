package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.EmptyBean;
import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/25.
 * 功能描述：
 */

public interface SendEmailVerifyCodeProviderServices extends IProvider {
    Flowable<EmptyBean> sendEmailVerify(Context context, String PhoneOrEmail, String BussinessType, String LanguageCode);
}
