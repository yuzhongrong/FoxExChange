package com.cjwsc.idcm.iprovider.logic;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.iprovider.ServiceMaintenanceServices;
import com.cjwsc.idcm.model.bean.ServiceStateBean;
import com.cjwsc.idcm.net.http.HttpUtils;
import com.cjwsc.idcm.net.transformer.DefaultTransformer;

import io.reactivex.Flowable;

import static com.cjwsc.idcm.constant.ProviderPath.checkServiceMaintenance;


/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：
 */
@Route(path = checkServiceMaintenance, name = "检查服务器是否升级维护")
public class ServiceMaintenanceLogic
        implements ServiceMaintenanceServices
{
    private Context context;


    @Override
    public void init(Context context) {
        this.context=context;
    }

    @Override
    public Flowable<ServiceStateBean> checkServiceMaintenance(Context context) {
        return HttpUtils.getInstance(BaseApplication.getContext())
                               .getRetrofitClient()
                               .builder(BaseApi.class)
                               .checkServiceMaintenance()
                               .compose(new DefaultTransformer<ServiceStateBean>());
    }
}
