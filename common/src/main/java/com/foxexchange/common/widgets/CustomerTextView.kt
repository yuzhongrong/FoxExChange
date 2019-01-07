package com.foxexchange.common.widgets

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView

class CustomerTextView : TextView {
    constructor(context: Context) : super(context) {
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {

        ellipsize = TextUtils.TruncateAt.valueOf("END")
        setSingleLine(true)
        gravity= Gravity.CENTER
    }


}
