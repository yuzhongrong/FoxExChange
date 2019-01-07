package com.foxexchange.c2c.activitys

import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.foxexchange.c2c.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.widgets.CustomerTextView

@Route(path = "/c2c/activitys/C2CAppealActivity",extras = 1)
class C2CAppealActivity :BaseFoxExChangeKTActivity(){


    var c2c_action_appeal:CustomerTextView?=null


    var tv_title:TextView? = null
    override val layoutId: Int
        get() = R.layout.activity_c2c_input_appeal

    override fun onInitView(bundle: Bundle?) {

        tv_title=findViewById(R.id.tv_title)
        tv_title?.text=getString(R.string.str_c2c_trade_appeal)

        findViewById<CustomerTextView>(R.id.c2c_action_appeal).setOnClickListener { finish() }

    }

    override fun onEvent() {



    }






}