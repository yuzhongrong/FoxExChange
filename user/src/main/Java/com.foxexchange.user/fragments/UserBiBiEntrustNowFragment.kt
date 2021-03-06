package com.foxexchange.user.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 * 币币订单-当前委托
 */
class UserBiBiEntrustNowFragment : BaseKTFragment() {

    var my_order_list : LQRRecyclerView ?= null

    var list : ArrayList<Any> = arrayListOf("1", "2", "3", "4", "5", "6")

    override val layoutId: Int
        get() = R.layout.fragment_my_order_list

    override fun onInitView(bundle: Bundle?) {
        my_order_list = rootView?.findViewById(R.id.my_order_list)

        my_order_list?.adapter = object :
                LQRAdapterForRecyclerView<Any>(context, list, R.layout.item_bibi_entrust) {
            override fun convert(holder: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
                holder?.getView<Button>(R.id.btn_bibi_cancel)?.visibility = View.VISIBLE
            }

        }
    }

    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }
}