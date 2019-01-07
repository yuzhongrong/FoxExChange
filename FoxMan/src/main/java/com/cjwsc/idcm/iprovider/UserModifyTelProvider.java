package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.mineBean.BindTelBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/25.
 * 功能描述：
 */

public interface UserModifyTelProvider extends IProvider {
    Flowable<BindTelBean> modifyTel(Context context, String aredCode, String phone, String pin, String tpwd);
}

