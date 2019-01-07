package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.mineBean.BindEmailBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/25.
 * 功能描述：
 */

public interface UserModifyEmailProvider extends IProvider {

        Flowable<BindEmailBean> modifyEmail(Context context, String email, String pin, String tpwd);

}
