package com.cjwsc.idcm.Utils;

/**
 * Created by yuzhongrong on 2017/9/13.
 */

public class ClickUtils {


    private static long lastClickTime;
    /**
     * 判断是否多次点击 @author Ysw created at 2017/3/15 13:06
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
