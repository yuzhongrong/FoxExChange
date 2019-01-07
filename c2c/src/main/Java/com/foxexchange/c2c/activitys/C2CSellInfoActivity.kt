package com.foxexchange.c2c.activitys

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.widget.edittext.FixedEditText
import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.widgets.CustomerTextView
import com.zhl.cbdialog.CBDialogBuilder
import org.simple.eventbus.EventBus

@Route(path = "/c2c/activitys/C2CSellInfoActivity",extras = 1)
class C2CSellInfoActivity:BaseFoxExChangeKTActivity(){
    var title:TextView?=null
    var c2c_trade_money:TextView?=null
    var c2c_btn_action_sell:CustomerTextView?=null

    override val layoutId: Int
        get() = R.layout.activity_sell_info

    override fun onInitView(bundle: Bundle?) {

        title=findViewById(R.id.tv_title)
        c2c_trade_money=findViewById(R.id.c2c_trade_money)
        c2c_btn_action_sell=findViewById(R.id.c2c_btn_action_sell)
        title?.text=getString(R.string.exchange_sale)
        c2c_btn_action_sell?.setOnClickListener {
            //弹出卖出对话框
            showConfirmSell()
             }

    }

    override fun onEvent() {
    }


    fun showConfirmSell(){
              CBDialogBuilder(this,R.layout.view_c2c_input_pwd_sell)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle(getString(R.string.str_c2c_confirm_sassets_title))
                .setTitleTextColor(resources.getColor(R.color.white))
                .setMessage("")
                .showConfirmButton(false)
                .showCancelButton(false)
                .showIcon(false)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
                .create()
                  .show()








    }

}