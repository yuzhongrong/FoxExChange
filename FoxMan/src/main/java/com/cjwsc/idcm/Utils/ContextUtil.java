package com.cjwsc.idcm.Utils;

import android.content.Context;

/**
 * Created by hpz on 2018/1/9.
 */

public class ContextUtil {
    private static Context context;

    private ContextUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        ContextUtil.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init ContextUtil int your application class firstly");
    }
}
