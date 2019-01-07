package com.foxexchange.c2c.activitys

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.PaytypeBean
import com.foxexchange.c2c.beans.UPBean
import com.foxexchange.c2c.popwindow.C2CSelectPayTypePop
import com.foxexchange.c2c.widget.SwitchTabLayout
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import com.orhanobut.logger.Logger
import com.zhl.cbdialog.CBDialogBuilder
import kotlinx.android.synthetic.main.activity_c2c_add_paytype.*
import java.util.*

@Route(path = "/c2c/activitys/C2CAddPayTypeActivity",extras = 1)
class C2CAddPayTypeActivity : BaseFoxExChangeKTActivity() {


    private var datalist: LQRRecyclerView? = null
    var tv_title:TextView?=null
    var imageView:ImageView?=null
    var c2c_pay_type_value:TextView?=null

    override val layoutId: Int
        get() =R.layout.activity_c2c_add_paytype





    override fun onInitView(bundle: Bundle?) {
        datalist=findViewById(R.id.datalist)
        tv_title=findViewById(R.id.tv_title)
        imageView=findViewById(R.id.c2c_pay_type_select)

        c2c_pay_type_value=findViewById(R.id.c2c_pay_type_value)
        tv_title?.text=getString(R.string.str_c2c_add_pay_type)
        c2c_paytype_paycontainer.switchTabLayout(R.layout.view_c2c_wx_paytype_layout,null)

    }


    override fun onEvent() {
        imageView?.setOnClickListener {C2CSelectPayTypePop(this).setOnItemSelectListener(object :C2CSelectPayTypePop.OnItemSelectedListener{
            override fun onItemSelected(result: PaytypeBean?) {

                c2c_pay_type_value?.text=result?.text
                when(result?.type){
                    0 -> {c2c_paytype_paycontainer.switchTabLayout(R.layout.view_c2c_wx_paytype_layout,null)}
                    1 -> {}
                    2 -> {}
                    3 -> {c2c_paytype_paycontainer.switchTabLayout(R.layout.view_c2c_bank_paytype_layout,null)}
                }


            }
        }).showPopupWindow(imageView)}
    }



//    fun showSelectPaytypeDilog(item: String){
//
//
//
//        CBDialogBuilder(this,R.layout.view_c2c_cancel_trade)
//                .setTouchOutSideCancelable(true)
//                .showCancelButton(true)
//                .setTitle(getString(R.string.str_c2c_del_tip))
//                .setTitleTextColor(resources.getColor(R.color.white))
//                .setMessage(getString(R.string.str_c2c_del_tip_msg))
//                .setConfirmButtonText(getString(R.string.dialog_ok))
//                .setConfirmButtonTextColor(resources.getColor(R.color.color_0994FE))
//                .setCancelButtonText(getString(R.string.dialog_cancel))
//                .setCancelButtonTextColor(resources.getColor(R.color.black))
//                .setButtonClickListener(false) { context, dialog, i ->
//                    when (i) {
//
//                        0 -> {/**请求服务器取消订单 关闭页面**/ dialog.dismiss() }
//                        1 -> { dialog.dismiss()}
//
//
//                    }
//                }
//                .setCancelable(false)
//                .showIcon(false)
//                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
//                .create().show()
//
//
//
//
//
//    }


}
