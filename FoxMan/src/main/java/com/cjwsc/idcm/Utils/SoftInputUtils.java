package com.cjwsc.idcm.Utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by yuzhongrong on 2017/8/19.
 */

public class SoftInputUtils {

    //隐藏输入法
    public static void hideSoftInput(Activity activity){

        //隐藏输入法
        ((InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(
                        activity.getCurrentFocus()
                                .getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

    }

}
