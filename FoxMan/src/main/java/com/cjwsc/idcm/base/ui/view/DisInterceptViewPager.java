package com.cjwsc.idcm.base.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liaoyunxia on 17/8/1.
 */

public class DisInterceptViewPager extends ViewPager {

    public DisInterceptViewPager(Context context) {
        super(context);
    }

    public DisInterceptViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}