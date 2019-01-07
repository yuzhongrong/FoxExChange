package com.foxexchange.exchange.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.exchange.R;

/**
 * created by pzw on 2018/12/19.
 */
public class LimitedPriceLayout extends LinearLayout{

    public LimitedPriceLayout(Context context) {
        this(context,null);
    }

    public LimitedPriceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout relativeLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.relativelayout_limited_price, null);
        EditText etLimitPrice = relativeLayout.findViewById(R.id.exchange_limit_price);
        etLimitPrice.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        addView(relativeLayout);
    }
}
