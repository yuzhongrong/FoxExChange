package com.cjwsc.idcm.base.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by shaco on 16/5/7.
 * noscrollrecycle 并不能解决问题 相反 这个可以解决问题
 */
public class NoScroolListView extends ListView {

    public NoScroolListView(Context context) {
        super(context);
    }

    public NoScroolListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScroolListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //不出现滚动条
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

