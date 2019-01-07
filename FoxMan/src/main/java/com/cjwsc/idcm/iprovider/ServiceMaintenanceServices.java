package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.ServiceStateBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：服务器维修接口
 */

public interface ServiceMaintenanceServices
        extends IProvider{
    /**
     *
     * @param context
     * @return
     */
    Flowable<ServiceStateBean> checkServiceMaintenance(Context context);
}
