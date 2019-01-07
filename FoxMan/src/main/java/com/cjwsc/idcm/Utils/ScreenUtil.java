package com.cjwsc.idcm.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;

import java.lang.reflect.Field;

import static com.cjwsc.idcm.Utils.UIUtils.getResources;


/**
 * @author: yzr
 * @createTime: 2016/10/30 14:06
 * @className:  ScreenUtil
 * @description: 获取屏幕信息工具类
 * @changed by:
 */
public class ScreenUtil {
    /**
     * 获得屏幕宽度，单位px
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取通知栏高度
     *
     * @param context 上下文
     * @return 通知栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int temp = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获得标题栏高度
     *
     * @param context 上下文，为Activity对象
     * @return 标题栏高度
     */
    public static int getTitleBarHeight(Activity context) {
        int contentTop = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return contentTop - getStatusBarHeight(context);
    }

    /**
     * 将dp转化为px
     *
     * @return dp对应的px值
     */
    public static int dp2px(float dp, Context context) {
        return (int) (dp * (context.getResources().getDisplayMetrics().density) + 0.5);
    }

    /**
     * 将px转化为dp
     *
     * @return px值对应的dp值
     */
    public static int px2dp(int px, Context context) {
        return (int) (px / (context.getResources().getDisplayMetrics().density) + 0.5);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param
     * @param
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param
     * @param
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
