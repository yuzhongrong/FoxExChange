package com.cjwsc.idcm.base.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by ASUS1 on 2017/9/19.
 */

public class AllShowRecycleView extends RecyclerView{
    public AllShowRecycleView(Context context) {
        super(context);
    }

    public AllShowRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AllShowRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(10000,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
