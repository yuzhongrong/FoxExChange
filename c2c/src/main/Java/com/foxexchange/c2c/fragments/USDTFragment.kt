package com.foxexchange.c2c.fragments

import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter

import com.cjwsc.idcm.base.BaseView
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.UPBean
import com.foxexchange.c2c.beans.USDTBean
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

import java.util.ArrayList

class USDTFragment : BaseKTFragment() {
    private var datalist: LQRRecyclerView? = null
    private var adapter: LQRAdapterForRecyclerView<USDTBean>? = null

    override val layoutId: Int
        get() = R.layout.fragment_usdt

    override fun onInitView(bundle: Bundle?) {

        datalist=rootView!!.findViewById(R.id.datalist)
        adapter=object : LQRAdapterForRecyclerView<USDTBean>(activity, ArrayList(), R.layout.item_usdt) {
            override fun convert(helper: LQRViewHolderForRecyclerView, item: USDTBean, position: Int) {
                helper.getView<TextView>(R.id.buy).setOnClickListener {
                    ARouter.getInstance()
                            .build("/c2c/activitys/C2CBuyInfoActivity")
                            .navigation(activity)
                }


            }

        }

        datalist!!.adapter =adapter
    }

    override fun onEvent() {

        for (i in 0..9) {

            adapter?.addLastItem(USDTBean())
        }
    }
    override fun lazyFetchData() {

    }
}
