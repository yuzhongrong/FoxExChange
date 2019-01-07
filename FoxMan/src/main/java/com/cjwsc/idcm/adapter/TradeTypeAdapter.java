package com.cjwsc.idcm.adapter;

import android.content.Context;
import android.view.View;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.recylcerview.BaseRecItemClickedAdapter;
import com.cjwsc.idcm.recylcerview.RecViewHolder;

import java.util.List;

/**
 * Created by ${zipp} on 2018/2/5.
 * 功能描述：
 */

public class TradeTypeAdapter extends BaseRecItemClickedAdapter<String> {

    private int selePotion=0;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public TradeTypeAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convert(RecViewHolder holder, String bean, int position) {
        if (position==1){
            holder.getView(R.id.v_bottom).setVisibility(View.GONE);
        }
        if (selePotion==position){
            holder.getView(R.id.iv_trade_seleted).setVisibility(View.VISIBLE);
        }else {
            holder.getView(R.id.iv_trade_seleted).setVisibility(View.GONE);
        }
        holder.setText(R.id.tv_trade_type,bean);
    }

    public void setSelePosition(int position){
        this.selePotion=position;
    }
}
