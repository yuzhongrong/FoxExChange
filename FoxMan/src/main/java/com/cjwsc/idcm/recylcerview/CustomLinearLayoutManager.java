package com.cjwsc.idcm.recylcerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by ${zipp} on 2018/1/26.
 * 功能描述：
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
