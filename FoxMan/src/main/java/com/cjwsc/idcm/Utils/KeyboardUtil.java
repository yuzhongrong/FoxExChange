package com.cjwsc.idcm.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.cjwsc.idcm.base.BaseActivity;
import com.orhanobut.logger.Logger;

/**
 * @author: yzr
 * @createTime: 2016/10/30 13:42
 * @className:  KeyboardUtil
 * @description: 输入法操作工具类
 * @changed by:
 */
public class KeyboardUtil {
    /**
     * 隐藏键盘
     *
     * @param context 上下文
     * @param view    The currently focused view
     */
    public static void hideInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示输入键盘
     *
     * @param context 上下文
     * @param view    The currently focused view, which would like to receive soft keyboard input
     */
    public static void showInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏输入法
     *
     * @param mAct activity
     */
    public static void hideInputMethod(Activity mAct) {
        try {// hide keybord anyway
            View v = mAct.getWindow().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) mAct.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 显示输入法
     *
     * @param mAct activity
     */
    public static void showInputMethod(final Activity mAct) {
        View v = mAct.getCurrentFocus();
        if (null == v) {
            return;
        }
        ((InputMethodManager) mAct.getSystemService(Activity.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
    }
    /**
     *
     * @param context 必须是activity的context
     * @return
     */
    public static boolean isSoftShowing(Context context) {
        //获取当前屏幕内容的高度
        int screenHeight = ((Activity)context).getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight*2/3 > rect.bottom;
    }



    public static void KeyBoardListner(BaseActivity activity,EditText customized_edit,OnKeyBoardListener callback){



        //键盘显示监听
        customized_edit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {
                final Rect rect = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                final int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
             //   Log.e("TAG",rect.bottom+"#"+screenHeight);
                Logger.d("---------------------->"+rect.bottom+"#"+screenHeight);
                final int heightDifference = screenHeight - rect.bottom;
                boolean visible = heightDifference > screenHeight / 3;
                if(visible){
                   // SysoutUtils.out("软键盘显示");
              //      customized_submit.setVisibility(View.GONE);
                    callback.showKeyBoard();
                }else {
               //     SysoutUtils.out("软键盘隐藏");
                //    customized_submit.setVisibility(View.VISIBLE);
                    callback.hideKeyBoard();
                }
            }
        });

    }


    public interface OnKeyBoardListener{

        void showKeyBoard();
        void hideKeyBoard();


    }


}
