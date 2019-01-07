package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.EmptyBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：
 */

public interface AccountGetImgCodeServices extends IProvider{
    /**
     *
     * @param context
     * @param codeKey
     * @param verifyDeviceType
     * @param clientType
     * @return
     */
    Flowable<EmptyBean> getImgCode(Context context, String codeKey, String verifyDeviceType, String clientType);
}
