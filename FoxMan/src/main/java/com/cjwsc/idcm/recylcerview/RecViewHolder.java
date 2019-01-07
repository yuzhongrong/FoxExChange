package com.cjwsc.idcm.recylcerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;


/**
 * RecyclerView的通用适配器的ViewHolder
 */
public final class RecViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews = new SparseArray<>();

    public RecViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 通过控件的Id获取对于的控件初始化
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 为textview设置字符串
     *
     * @param viewId
     * @param str
     */
    public void setText(int viewId, String str) {
        ((TextView) getView(viewId)).setText(str);
    }
    public void setTextColor(int viewId,int color){
        ((TextView) getView(viewId)).setTextColor(color);
    }



    /**
     * 为textview设置隐藏
     *
     * @param viewId
     * @param
     */
    public void setTextVisibility(int viewId) {
        setTextVisibility(viewId,false);
    }
    public void setTextVisibility(int viewId,boolean isVisible){
        if (isVisible){
            (getView(viewId)).setVisibility(View.VISIBLE);
        }else {
            (getView(viewId)).setVisibility(View.GONE);
        }

    }

}
