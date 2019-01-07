package com.foxexchange.c2c.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.UPBean
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.constants.RequestCode.x_1001
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import com.zhl.cbdialog.CBDialogBuilder
import java.util.*

@Route(path = "/c2c/activitys/C2CPayTypeListActivity",extras = 1)
class C2CPayTypeListActivity : BaseFoxExChangeKTActivity() {


    private var datalist: LQRRecyclerView? = null
    private var adapter: LQRAdapterForRecyclerView<String>? = null
    var tv_title:TextView?=null
    var footview:View?=null


    override val layoutId: Int
        get() =R.layout.activity_c2c_paytype_list





    override fun onInitView(bundle: Bundle?) {
        datalist=findViewById(R.id.datalist)
        tv_title=findViewById(R.id.tv_title)
        tv_title?.text=getString(R.string.str_c2c_pay_type)

      adapter = object : LQRAdapterForRecyclerView<String>(this, ArrayList<String>(), R.layout.item_c2c_paytype) {
            override fun convert(helper: LQRViewHolderForRecyclerView,item : String, position: Int) {

                helper.getView<TextView>(R.id.pay_name).text=item

//                helper.convertView.setOnClickListener {
//
//                    adapter?.removeItem(position)
//                    notifyDataSetChanged()
//                }

                helper.getView<Button>(R.id.btn_delete_address).setOnClickListener {

                   showDelItemDialog(item)
                }

            }

        }
        footview=View.inflate(this,R.layout.view_footer_add_paytype,null)
        adapter?.addFooterView(footview)
        datalist?.adapter=adapter?.headerAndFooterAdapter
    }


    override fun onEvent() {

        var str="测试"
        for (i in 0..10) {

            adapter?.addLastItem(str+i)
        }
        footview?.findViewById<View>(R.id.c2c_add_paytype)?.setOnClickListener { ARouter.getInstance().build("/c2c/activitys/C2CAddPayTypeActivity").navigation(this,x_1001)  }

    }

    fun showDelItemDialog(item: String){

        CBDialogBuilder(this,R.layout.view_c2c_cancel_trade)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle(getString(R.string.str_c2c_del_tip))
                .setTitleTextColor(resources.getColor(R.color.white))
                .setMessage(getString(R.string.str_c2c_del_tip_msg))
                .setConfirmButtonText(getString(R.string.dialog_ok))
                .setConfirmButtonTextColor(resources.getColor(R.color.color_0994FE))
                .setCancelButtonText(getString(R.string.dialog_cancel))
                .setCancelButtonTextColor(resources.getColor(R.color.black))
                .setButtonClickListener(false) { context, dialog, i ->
                    when (i) {

                        0 -> {/**请求服务器取消订单 关闭页面**/
                            dialog.dismiss()
                            adapter?.removeItem(item)}
                        1 -> { dialog.dismiss()}


                    }
                }
                .setCancelable(false)
                .showIcon(false)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
                .create().show()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode== x_1001){
            //刷新支付方式列表

        }

    }


}
