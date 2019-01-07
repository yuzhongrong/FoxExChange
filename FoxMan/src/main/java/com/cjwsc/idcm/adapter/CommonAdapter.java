package com.cjwsc.idcm.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by yuzhongrong on 2017/8/24.
 */

public abstract class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private TextView mTvEmpty;
    private TextView mTvLoading;
    private boolean mIsBindEmptyView;

    public CommonAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public CommonAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        commonconvert(helper, item);
    }

    @Override
    public void setNewData(@Nullable List<T> data) {
        super.setNewData(data);
        if (mIsBindEmptyView && (data == null || data.size() == 0)) {
            setTvEmptyVisible(true);
        }
    }

    public abstract void commonconvert(BaseViewHolder helper, T item);

//    public void bindEmptyView(RecyclerView recyclerView) {
//        mIsBindEmptyView = true;
//        View emptyView = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.view_no_data, recyclerView, false);
//        mTvEmpty = (TextView) emptyView.findViewById(R.id.img_empty);
//        mTvLoading = (TextView) emptyView.findViewById(R.id.tv_loading);
//        setEmptyView(emptyView);
//    }



    public void setTvEmptyVisible(boolean v) {
        mTvLoading.setVisibility(View.GONE);
        mTvEmpty.setVisibility(v ? View.VISIBLE : View.GONE);
    }
}
