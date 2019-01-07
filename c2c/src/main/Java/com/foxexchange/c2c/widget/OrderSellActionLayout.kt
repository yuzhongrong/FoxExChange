package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.BasePayBean

class OrderSellActionLayout : LinearLayout {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        initView()

    }

    private fun initView() {
        View.inflate(mContext, R.layout.view_c2c_sell_action,this)
    }


    fun switchOrderInfoTabLayout(resid: Int, payBean: BasePayBean?) {
        val view = View.inflate(mContext, resid, null)
        this.removeAllViews()
        this.addView(view)
    }


}
