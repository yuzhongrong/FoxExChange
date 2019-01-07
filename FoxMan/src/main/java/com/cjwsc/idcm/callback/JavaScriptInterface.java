package com.cjwsc.idcm.callback;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * WebView 和 JavaScript 交互的接口；
 */
public class JavaScriptInterface {
    Context mContext;

    /**
     * 实例化接口
     **/
    public JavaScriptInterface(Context c) {
        mContext = c;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

}
