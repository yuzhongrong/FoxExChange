package com.cjwsc.idcm.base.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.UIUtils;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

public class AutoLinearLayoutManager extends LinearLayoutManager {

    private Context mContext;
    private RecyclerView recyclerView;

    public AutoLinearLayoutManager(Context context ,int  orientation) {

        super(context,orientation,false);
        this.mContext=context;

    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {

        if(state.getItemCount()>0 && getItemCount()>0) {
            View view=recycler.getViewForPosition(0);
            measureChild(view,widthSpec,heightSpec);
            int measuredWidth=View.MeasureSpec.getSize(widthSpec);
            int measuredHeight=view.getMeasuredHeight();
            setMeasuredDimension(measuredWidth>780?780:measuredWidth,150);
        }else{
            super.onMeasure(recycler,state,widthSpec,heightSpec);

        }


    }
}
