package com.foxexchange.c2c.activitys

import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.foxexchange.c2c.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.widgets.CustomerTextView

@Route(path = "/c2c/activitys/C2COrderCenterActivity",extras = 1)
class C2COrderCenterActivity :BaseFoxExChangeKTActivity(){

    override val layoutId: Int
        get() = R.layout.activity_orders_center

    override fun onInitView(bundle: Bundle?) {
        super.onInitView(bundle)
        findViewById<TextView>(R.id.tv_title).text="订单中心"

    }
    override fun onEvent() {



    }






}