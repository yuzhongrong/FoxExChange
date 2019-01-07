package com.cjwsc.idcm.base.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可设置是否可滑动ViewPager
 * Created by xz on 2016/6/14.
 */
public class SetIsScrollViewPager extends ViewPager {

    private boolean isScrollable;

    public SetIsScrollViewPager(Context context) {
        super(context);
    }

    public SetIsScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);

    }

    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }
}
