package com.cjwsc.idcm.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

/**
 * Created by hpz on 2018/1/9.
 */

public class FingerPrintHelper {
    static final String PREFERENCE_FINGER_PRINT_CONFIG_NAME = "fingerPrint";
    static final String PREFERENCE_KEY_IS_SUPPORT_FINGER_PRINT = "isSupportFingerPrint";
    static final String PREFERENCE_KEY_HAS_EVER_CHECKED = "hasEverCheckedSupportFingerPrint";

    static final String TAG = "fingerPrint";

    public static SharedPreferences getConfig() {
        return ContextUtil.getContext().getSharedPreferences(PREFERENCE_FINGER_PRINT_CONFIG_NAME, Context.MODE_PRIVATE);
    }

    public static void clearConfig() {
        getConfig().edit().clear().commit();
    }

    private static void setSupportFingerPrint(boolean isSupport) {
        SharedPreferences.Editor editor = getConfig().edit();
        editor.putBoolean(PREFERENCE_KEY_IS_SUPPORT_FINGER_PRINT, isSupport);
        editor.commit();
    }

    /**
     * 判断当前手机支不支持指纹识别
     * 这种方法仅限于api低于安卓6.0但是手机厂家有会支持的手机。如小米，VIVO的一些机型。
     * 可取性不是很好。
     *
     * @return
     */
    public static boolean isSupportFingerPrint() {
        boolean res = false;
        if (!hasEverCheckedFingerPrintSupport()) {
            res = checkSupportFingerPrint();
            setSupportFingerPrint(res);
            setHasEverCheckedFingerPrintSupport(true);
        } else {
            res = getConfig().getBoolean(PREFERENCE_KEY_IS_SUPPORT_FINGER_PRINT, false);
        }
        return res;
    }

    private static boolean hasEverCheckedFingerPrintSupport() {
        return getConfig().getBoolean(PREFERENCE_KEY_HAS_EVER_CHECKED, false);
    }

    private static void setHasEverCheckedFingerPrintSupport(boolean hasEver) {
        SharedPreferences.Editor editor = getConfig().edit();
        editor.putBoolean(PREFERENCE_KEY_HAS_EVER_CHECKED, hasEver);
        editor.commit();
    }

    /**
     * 检测当前的手机支不支持指纹识别，实际上就是判断一下当前的手机API有没有{@link android.hardware.fingerprint.FingerprintManager}这个类
     *
     * @return
     */
    private static boolean checkSupportFingerPrint() {
        boolean result = false;
        try {
            Class.forName("android.hardware.fingerprint.FingerprintManager"); // 通过反射判断是否存在该类
            result = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 开始进行指纹识别
     * @param context
     * @param cancellationSignal 指纹识别取消的控制器
     * @param callback 指纹识别回调函数
     */
    public static void doFingerPrint(Context context, android.support.v4.os.CancellationSignal cancellationSignal, FingerprintManagerCompat.AuthenticationCallback callback) {
        FingerprintManagerCompat managerCompat = FingerprintManagerCompat.from(context);
        managerCompat.authenticate(null, 0, cancellationSignal, callback, null);
    }

    /**
     * 官方写法，用于判断是否支持指纹识别
     *
     * @param context
     * @return
     */
    public static boolean isHardWareDetected(Context context) {
        return FingerprintManagerCompat.from(context).isHardwareDetected();
    }

    /**
     * 官方写法，当前手机是否设置过指纹
     *
     * @param context
     * @return
     */
    public static boolean hasEnrolledFingerPrint(Context context) {
        return FingerprintManagerCompat.from(context).hasEnrolledFingerprints();
    }
}
