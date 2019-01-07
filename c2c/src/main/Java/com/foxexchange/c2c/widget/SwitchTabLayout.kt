package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

import com.cjwsc.idcm.model.bean.WxPayBean
import com.foxexchange.c2c.beans.BasePayBean

class SwitchTabLayout : RelativeLayout {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context

    }


    fun switchTabLayout(resid: Int, payBean: BasePayBean?) {
        val view = View.inflate(mContext, resid, null)
        this.removeAllViews()
        this.addView(view)
    }


}
