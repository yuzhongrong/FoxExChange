package com.foxexchange.c2c.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter

import com.cjwsc.idcm.base.BaseView
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.flyco.tablayout.SlidingTabLayout
import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.UPBean
import com.foxexchange.common.adapter.MyPagerAdapter
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import com.zhl.cbdialog.CBDialogBuilder

import java.util.ArrayList

class UPFragmentOut : BaseKTFragment() {


    private var datalist: LQRRecyclerView? = null
    private var adapter: LQRAdapterForRecyclerView<UPBean>? = null


    override val layoutId: Int
        get() =R.layout.fragment_up





    override fun onInitView(bundle: Bundle?) {
        datalist=rootView?.findViewById(R.id.datalist)

      adapter = object : LQRAdapterForRecyclerView<UPBean>(activity, ArrayList<UPBean>(), R.layout.item_up_out) {
            override fun convert(helper: LQRViewHolderForRecyclerView, item: UPBean, position: Int) {



                helper.getView<TextView>(R.id.buy).setOnClickListener { ARouter.getInstance().build("/c2c/activitys/C2CSellInfoActivity").navigation(activity) }


            }

        }

        datalist?.adapter=adapter
    }


    override fun onEvent() {

        for (i in 0..10) {

            adapter?.addLastItem(UPBean())
        }

    }

    override fun lazyFetchData() {

    }

    fun showPop(){
        CBDialogBuilder(mContext,R.layout.dialog_common)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle(getString(R.string.str_tip_buy))
                .setTitleTextColor(mContext!!.resources.getColor(R.color.white))
                .setMessage(getString(R.string.str_verify_middel))
                .setConfirmButtonText(getString(R.string.dialog_ok))
                .setConfirmButtonTextColor(mContext!!.resources.getColor(R.color.color_0994FE))
                .setCancelButtonText(getString(R.string.dialog_cancel))
                .setCancelButtonTextColor(mContext!!.resources.getColor(R.color.black))
                .setCancelable(false)
                .showIcon(false)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
                .create().show()

    }
}
