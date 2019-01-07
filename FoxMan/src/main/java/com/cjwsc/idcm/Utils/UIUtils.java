package com.cjwsc.idcm.Utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.cjwsc.idcm.Utils.LogUtil;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;

import static com.cjwsc.idcm.Utils.ScreenUtil.getStatusBarHeight;
import static io.rong.imlib.statistics.Statistics.TAG;

/**
 * Created by hpz on 2017/12/18.
 */

public class UIUtils {
    /**
     * 全局的上下文
     */
    private static Context mBaseContext;
    private static Handler mHandler;

    /**
     * 初始化工具
     *
     * @param application
     */
    public static void init(Application application) {
        mBaseContext = application;

        //在主线程中new
        mHandler = new Handler();
    }

    public static Context getContext() {
        return mBaseContext;
    }

    public static void post(Runnable task) {
        try {
            mHandler.post(task);
        } catch (Exception ex) {
            LogUtil.e("Exception Occurs ", ex.getMessage());
        }
    }

    public static void postDelayed(Runnable task, long delayed) {
        mHandler.postDelayed(task, delayed);
    }

    public static void removeCallbacks(Runnable task) {
        mHandler.removeCallbacks(task);
    }

    public static Resources getResources() {
        return mBaseContext.getResources();
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static File getCacheDir() {
        return getContext().getCacheDir();
    }

    /**
     * dp --> px
     *
     * @param dp
     * @return
     */
    public static float dp2px(float dp) {
        float dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics());
        return dimension;
    }

    public static float px2dp(float px) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                px,
                getResources().getDisplayMetrics());
    }

    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    public static Drawable getDrawable(int resId) {

        return getResources().getDrawable(resId);
    }

    public static int getVisibe(int value) {
        try {
            if (value == 0) return View.INVISIBLE;
            else return View.VISIBLE;
        } catch (Exception ex) {
            return View.VISIBLE;
        }

    }

    public static void toggleKeyboard(Context context) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public static void hideKeyBorad(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyBoard(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        im.showSoftInput(view, im.SHOW_FORCED);
    }

    public static String getVersionName() {
        String versionName = null;
        try {
            versionName = getContext().getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = getContext().getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取渠道
     *
     * @return
     */
    public static String getChannel() {
        String msg = "";
        try {
            ApplicationInfo appInfo = UIUtils.getContext().getPackageManager().getApplicationInfo(UIUtils.getPackageName(), PackageManager.GET_META_DATA);
            msg = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "360";
        }
        return msg;
    }

    public static String getNimAppKey() {
        String appKey = "";
        try {
            ApplicationInfo appInfo = UIUtils.getContext().getPackageManager().getApplicationInfo(UIUtils.getPackageName(), PackageManager.GET_META_DATA);
            appKey = appInfo.metaData.getString("com.netease.nim.appKey");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appKey;
    }

    /**
     * 检查登录
     * @return false为未登录, true为登录
     */
//    public static boolean checkLogin(){
//        if(PreferenceUtils.getString(UIUtils.getContext(), "gid")==null||PreferenceUtils.getString(UIUtils.getContext(),"gid").equals("6")){
//            Intent intent = new Intent(UIUtils.getContext(),LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("isEnter", true);
//            UIUtils.getContext().startActivity(intent);
//            return false;
//        }
//        return true;
//    }


    /**
     * dip-->px
     */
    public static int dip2Px(int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density = getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2dip(int px) {

        float density =  getContext().getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    /**
     * sp-->px
     */
    public static int sp2px(int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,  getContext().getResources().getDisplayMetrics()) + 0.5f);
    }


    public static Toast mToast;
    public static int screenWidth;
    public static int screenHeight;
    public static int screenMin;// 宽高中，小的一边
    public static int screenMax;// 宽高中，较大的值
    public static float density;
    public static float scaleDensity;
    public static float xdpi;
    public static float ydpi;
    public static int densityDpi;
    public static int statusbarheight;
    public static int navbarheight;


    public static int getDisplayWidth() {
        if (screenWidth == 0) {
            GetInfo(UIUtils.getContext());
        }
        return screenWidth;
    }



    public static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        statusbarheight = getStatusBarHeight(context);
        navbarheight = getNavBarHeight(context);
        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
    }


    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

}
