package com.cjwsc.idcm.adapter;

import android.content.Context;
import android.graphics.Color;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.recylcerview.BaseRecItemClickedAdapter;
import com.cjwsc.idcm.recylcerview.RecViewHolder;

import java.util.List;

/**
 * Created by ${zipp} on 2018/2/5.
 * 功能描述：
 */

public class KlineTradeTypeAdapter extends BaseRecItemClickedAdapter<String> {

    private int selePotion=0;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public KlineTradeTypeAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convert(RecViewHolder holder, String bean, int position) {
        if (selePotion==position){
            holder.setTextColor(R.id.tv_trade_type, Color.parseColor("#2E7FD0"));
        }else {
            holder.setTextColor(R.id.tv_trade_type, Color.parseColor("#FFFFFF"));
        }
        holder.setText(R.id.tv_trade_type,bean);
    }

    public void setSelePosition(int position){
        this.selePotion=position;
    }
}
