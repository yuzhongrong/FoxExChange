package com.foxexchange.user.fragments

import android.os.Bundle
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 * 法币订单 已完成
 */
class UserFaBiFragmented :BaseKTFragment() {

    var lqr_fb_list : LQRRecyclerView ?= null

    var adapter : LQRAdapterForRecyclerView<*> ?= null

    var list : ArrayList<Any> = arrayListOf("1", "2", "3", "4", "5", "6")

    override val layoutId: Int
        get() = R.layout.fragment_my_order_list

    override fun onInitView(bundle: Bundle?) {
        lqr_fb_list = rootView?.findViewById(R.id.my_order_list)

        lqr_fb_list?.adapter = object : LQRAdapterForRecyclerView<Any>(context, list, R.layout.item_fabi) {
            override fun convert(holder: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {

            }

        }
    }

    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }
}