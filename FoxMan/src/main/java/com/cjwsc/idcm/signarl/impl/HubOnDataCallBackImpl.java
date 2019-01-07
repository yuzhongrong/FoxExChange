package com.cjwsc.idcm.signarl.impl;

import android.os.Looper;

import com.cjwsc.idcm.Utils.JsonUtil;
import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.Utils.TypeUtils;
import com.google.gson.Gson;


import java.lang.reflect.Type;
import java.util.logging.Handler;

import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;

/**
 * Created by admin-2 on 2018/3/30.
 * 实现JSON转换成bean对象
 *
 * E:拿到的结果字符串
 * T:需要解析的结果对象
 */

public abstract class  HubOnDataCallBackImpl<T> implements SubscriptionHandler1<Object>{
    private static android.os.Handler handler=new android.os.Handler(Looper.getMainLooper());
    private Type type;
    public HubOnDataCallBackImpl() {
        type= TypeUtils.getPresnterClazz(getClass(),0);
    }

    @Override
    public void run(Object t) {
        try {
            String json=  JsonUtil.toJson(t);
            LogUtil.e("------signalr subscribe data------------>"+json);
           T bean = new Gson().fromJson(json, type);
          if(t!=null){
              handler.post(new Runnable() {
                  @Override
                  public void run() {
                      convert(bean);
                  }
              });

          }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void convert(T t);




}
