package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.AccountLoginBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/19.
 * 功能描述：
 */

public interface AccountLoginProviderServices extends IProvider {
    /**
     * 用户登入
     * @param account
     * @param password
     * @param areaCode
     * @return
     */
    Flowable<AccountLoginBean> userLogin(Context context,String account, String password, String areaCode);
}
