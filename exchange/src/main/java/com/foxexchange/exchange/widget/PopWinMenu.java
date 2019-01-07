package com.foxexchange.exchange.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjwsc.idcm.Utils.ACacheUtil;
import com.example.exchange.R;

/**
 * created by pzw on 2018/12/21.
 */
public class PopWinMenu extends PopupWindow {
    private View mainView;
    private RelativeLayout layoutCenter, layoutCoinCharging,layoutAddOptional,layoutHandicap;

    public PopWinMenu(Activity paramActivity, View.OnClickListener paramOnClickListener, int paramInt1, int paramInt2){
        super(paramActivity);
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.pop_exchange_menu, null);
        layoutCenter = ((RelativeLayout)mainView.findViewById(R.id.exchange_order_centerpop));
        layoutCoinCharging = (RelativeLayout)mainView.findViewById(R.id.exchange_order_coincharging);
        layoutAddOptional = (RelativeLayout)mainView.findViewById(R.id.exchange_order_addoptional);
        layoutHandicap = (RelativeLayout)mainView.findViewById(R.id.exchange_order_handicapchange);
        TextView tvHandicapchange = layoutHandicap.findViewById(R.id.exchange_tv_handicapchange);
        if(ACacheUtil.get(paramActivity).getAsBoolean("isHorizontal",false)) {
            tvHandicapchange.setText("竖盘盘口");
        }else {
            tvHandicapchange.setText("横盘盘口");
        }
        //设置每个子布局的事件监听器
        if (paramOnClickListener != null){
            layoutCenter.setOnClickListener(paramOnClickListener);
            layoutCoinCharging.setOnClickListener(paramOnClickListener);
            layoutAddOptional.setOnClickListener(paramOnClickListener);
            layoutHandicap.setOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置高度
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置显示隐藏动画
//        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
        setFocusable(true);
        setOutsideTouchable(true);
    }
}