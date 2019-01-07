package com.cjwsc.idcm.manager;

import android.content.Context;


/**
 * Created by zc 2016年4月26日
 * 微信支付管理类
 */
public class WxPayManager {
    public static String APP_ID;//回调有用到
    private Context mContext;
    private static final int SDK_PAY_FLAG = 1;
    private static WxPayManager instance;

    public WxPayManager(Context context) {
        mContext = context;
    }


    /**
     * 双重校验锁单例(线程安全)
     */
    public static WxPayManager getInstance(Context context) {
        if (instance == null) {
            synchronized (WxPayManager.class) {
                if (instance == null) {
                    instance = new WxPayManager(context);
                }
            }
        }
        return instance;
    }



}
