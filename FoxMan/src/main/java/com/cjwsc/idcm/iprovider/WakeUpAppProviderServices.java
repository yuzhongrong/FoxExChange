package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.EmptyBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/25.
 * 功能描述：建议PIN是否正确
 */

public interface WakeUpAppProviderServices
        extends IProvider
{
    Flowable<EmptyBean> wakeUpApp(Context context, String pin);
}
