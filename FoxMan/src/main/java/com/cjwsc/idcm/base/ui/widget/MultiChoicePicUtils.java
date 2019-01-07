package com.cjwsc.idcm.base.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.List;

import com.cjwsc.idcm.Utils.ScreenUtil;
import com.cjwsc.idcm.base.ui.adapter.AddImageAdapter;
import com.cjwsc.idcm.model.bean.PictureInfo;


/**
 * Created by ASUS1 on 2017/9/20.
 */

public class MultiChoicePicUtils {
    private RecyclerView mRecyclerView;
    private Context mContext;
    private AddImageAdapter mAddAdapter;
    private List<PictureInfo> mImagsList;
    public MultiChoicePicUtils(RecyclerView mRecyclerView, Activity mContext) {
        this.mRecyclerView = mRecyclerView;
        this.mContext = mContext;
    }

    public void setMultiPic(int spanCount,int limitsize,int heihtOffist){
        GridLayoutManager gridManager = new GridLayoutManager(mContext,spanCount);
        mRecyclerView.setLayoutManager(gridManager);
        mAddAdapter=new AddImageAdapter(mContext,5,mImagsList);
        mRecyclerView.setAdapter(mAddAdapter);
        final int distance = ScreenUtil.dp2px(heihtOffist, mContext);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom=distance;


            }
        });





    }


}
