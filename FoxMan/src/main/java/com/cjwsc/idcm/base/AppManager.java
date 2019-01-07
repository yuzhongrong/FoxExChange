package com.cjwsc.idcm.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;


import com.cjwsc.idcm.Utils.LogUtil;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Created by xz on 2016/4/8.
 */
public  class AppManager {

    protected static Stack<Activity> activityStack;
    private static AppManager instance;



    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivityClass(Class<?> cls) {
        for (Activity a:activityStack) {
            if (a.getClass().equals(cls)) {
              finishActivity(a);
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivityClassName(String clsName) {
        for (Activity a:activityStack) {
            if (a.getClass().getSimpleName().equals(clsName)) {
                finishActivity(a);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Stack<Activity> tempStack = new Stack<>();
        for (Activity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                if (!activity.isFinishing())
                    activity.finish();
                tempStack.add(activity);
            }
        }
        activityStack.removeAll(tempStack);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {

        if(activityStack==null)return;//初始化的时候为空

        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                LogUtil.e("activity:"+activityStack.get(i).getLocalClassName().toString());
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }








    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
