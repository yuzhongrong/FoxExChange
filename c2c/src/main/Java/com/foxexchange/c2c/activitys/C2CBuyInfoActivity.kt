package com.foxexchange.c2c.activitys

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.widget.edittext.FixedEditText
import com.foxexchange.c2c.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.widgets.CustomerTextView

@Route(path = "/c2c/activitys/C2CBuyInfoActivity",extras = 1)
class C2CBuyInfoActivity:BaseFoxExChangeKTActivity(){
    var title:TextView?=null
    var c2c_trade_money:TextView?=null
    var c2c_btn_action_buy:CustomerTextView?=null

    override val layoutId: Int
        get() = R.layout.activity_buy_info

    override fun onInitView(bundle: Bundle?) {

        title=findViewById(R.id.tv_title)
        c2c_trade_money=findViewById(R.id.c2c_trade_money)
        c2c_btn_action_buy=findViewById(R.id.c2c_btn_action_buy)
        title?.text=getString(R.string.str_buy)
        c2c_btn_action_buy?.setOnClickListener { ARouter.getInstance().build("/c2c/activitys/C2CBuyActivity").navigation() }

    }

    override fun onEvent() {
    }
}