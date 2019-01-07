package com.cjwsc.idcm.base.ui.adapter;

import android.content.Context;
import android.view.View;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.recylcerview.BaseRecItemClickedAdapter;
import com.cjwsc.idcm.recylcerview.RecViewHolder;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/23.
 * 功能描述：
 */

public class AssetsSeleteAdapter extends BaseRecItemClickedAdapter<String> {
    private int currentPosition;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public AssetsSeleteAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convert(RecViewHolder holder, String bean, int position) {
            holder.setText(R.id.tv_money_type,bean);
            if (position==currentPosition){
                holder.getView(R.id.iv_isShow).setVisibility(View.VISIBLE);
            }else {
                holder.getView(R.id.iv_isShow).setVisibility(View.GONE);
            }
    }

    public void setCurrentPosition(int position){
        this.currentPosition=position;
        notifyDataSetChanged();
    }
}
