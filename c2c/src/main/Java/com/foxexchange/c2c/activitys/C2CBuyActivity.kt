package com.foxexchange.c2c.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.c2c.widget.OrderInfoTabLayout
import com.foxexchange.c2c.widget.PayTypeLayout
import com.foxexchange.c2c.widget.SwitchTabLayout
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.widgets.CustomerTextView
import com.orhanobut.logger.Logger
import com.zhl.cbdialog.CBDialogBuilder
import org.simple.eventbus.EventBus

@Route(path = "/c2c/activitys/C2CBuyActivity")
class C2CBuyActivity :BaseFoxExChangeKTActivity(){


    var tv_title:TextView? = null
    var c2c_btn_cancel:CustomerTextView?=null
    var payTypeLayout:PayTypeLayout?=null
    var c2c_paycontainer:SwitchTabLayout?=null
    var orderinfo:OrderInfoTabLayout?=null
    var c2c_btn_payed:CustomerTextView?=null

    override val layoutId: Int
        get() = R.layout.activity_buy

    override fun onInitView(bundle: Bundle?) {

        ARouter.getInstance().inject(this)
        tv_title=findViewById(R.id.tv_title)
        c2c_btn_cancel=findViewById(R.id.c2c_btn_cancel)
        payTypeLayout=findViewById(R.id.paytype)
        orderinfo=findViewById(R.id.orderinfo)
        c2c_paycontainer=findViewById(R.id.c2c_paycontainer)
        c2c_btn_payed=findViewById(R.id.c2c_btn_payed)
        payTypeLayout?.setPayTypeContainer(c2c_paycontainer)
//        orderinfo?.switchOrderInfoTabLayout(R.layout.view_c2c_common_tradeinfo,null)


        initData()
    }

    override fun onEvent() {


        c2c_btn_cancel?.setOnClickListener {
            showCancelTradeDialog()
        }

        c2c_btn_payed?.setOnClickListener {
            showConfirmBuy()
        }

    }


    fun initData(){

        tv_title?.text=getString(R.string.str_c2c_buy)

    }


    fun showCancelTradeDialog(){

        CBDialogBuilder(this,R.layout.view_c2c_cancel_trade)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle(getString(R.string.str_c2c_cancel_trade))
                .setTitleTextColor(resources.getColor(R.color.white))
                .setMessage(getString(R.string.str_c2c_over_3_cancel))
                .setConfirmButtonText(getString(R.string.dialog_ok))
                .setConfirmButtonTextColor(resources.getColor(R.color.color_0994FE))
                .setCancelButtonText(getString(R.string.str_c2c_think_again))
                .setCancelButtonTextColor(resources.getColor(R.color.black))
                .setButtonClickListener(false) { context, dialog, i ->
                    when (i) {

                        0 -> {/**请求服务器取消订单 关闭页面**/ finish()}
                        1 -> {dialog.dismiss()}


                    }
                }
                .setCancelable(false)
                .showIcon(false)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
                .create().show()
    }

    fun showConfirmBuy(){

        CBDialogBuilder(this,R.layout.view_c2c_cancel_trade)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle(getString(R.string.str_c2c_confirm_payed_title))
                .setTitleTextColor(resources.getColor(R.color.white))
                .setMessage(getString(R.string.str_c2c_confirm_payed_msg))
                .setConfirmButtonText(getString(R.string.dialog_ok))
                .setConfirmButtonTextColor(resources.getColor(R.color.color_0994FE))
                .setCancelButtonText(getString(R.string.str_cancel_exchange))
                .setCancelButtonTextColor(resources.getColor(R.color.black))
                .setButtonClickListener(false) { context, dialog, i ->
                    when (i) {

                        0 -> {/**确认*/
                            dialog.dismiss()
                            //构造一个订单信息发出去
                            var onErrorAction=OrderInfoBean(1,"0x11","0x12",0,60)
                            EventBus.getDefault().post(onErrorAction,"ACTION_C2C_UPDATE_PAY_STATE")
                        }
                        1 -> {
                            /**取消**/
                            finish()
                        }
                    }
                }
                .setCancelable(false)
                .showIcon(false)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
                .create().show()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==0x1000){
            //提交申诉完成
            Logger.d("------------提交申诉完成 更新页面状态------->")

            var onErrorAction=OrderInfoBean(2,"0x11","0x12",0,0)
            EventBus.getDefault().post(onErrorAction,"ACTION_C2C_UPDATE_PAY_STATE")

        }
    }

}