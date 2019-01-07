package com.foxexchange.exchange.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exchange.R;

/**
 * created by pzw on 2018/12/21.
 */
public class SaleoutLinearLayout extends LinearLayout{
    public SaleoutLinearLayout(Context context) {
        this(context,null);
    }

    public SaleoutLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        /*
         * 获取到自定义控件需要的的布局
         */
        LinearLayout llContianerSaleout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.ll_contianer_saleout, null);
        TextView tvPriceType = llContianerSaleout.findViewById(R.id.exchange_price_type);
        //gone
        LimitedPriceLayout llLimitedPrice = llContianerSaleout.findViewById(R.id.exchange_limitedprice_layout);
        TextView tvPretradePrice =  llContianerSaleout.findViewById(R.id.exchange_pretrade_price);
        TextView tvCoinprice  = llContianerSaleout.findViewById(R.id.exchange_trade_coinprice);
        TextView tvCoinRMBprice  = llContianerSaleout.findViewById(R.id.tv_exchange_tradermb);
        //限价买入提示
        TextView tvPriceOff = llContianerSaleout.findViewById(R.id.exchange_tv_priceoff);

        //输入数量
        EditText etBuyCount = llContianerSaleout.findViewById(R.id.exchange_buy_count);
        etBuyCount.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        llContianerSaleout.findViewById(R.id.exchange_price_type).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvPretradePrice.getVisibility() == VISIBLE) {
                    tvPriceType.setText("市价");
                    tvCoinprice.setVisibility(GONE);
                    tvCoinRMBprice.setVisibility(GONE);
                    llLimitedPrice.setVisibility(GONE);
                    tvPretradePrice.setVisibility(GONE);
                    tvPriceOff.setVisibility(VISIBLE);
                }else {
                    tvPriceType.setText("限价");
                    tvCoinprice.setVisibility(VISIBLE);
                    tvCoinRMBprice.setVisibility(VISIBLE);
                    llLimitedPrice.setVisibility(VISIBLE);
                    tvPretradePrice.setVisibility(VISIBLE);
                    tvPriceOff.setVisibility(GONE);
                }
            }
        });
        addView(llContianerSaleout);
    }
}
