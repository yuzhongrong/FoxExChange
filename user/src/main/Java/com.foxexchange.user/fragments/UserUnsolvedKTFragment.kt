package com.foxexchange.user.fragments

import android.os.Bundle
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.foxexchange.user.beans.HomeDataBean
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 * 币币资产fragment
 */
class UserUnsolvedKTFragment : BaseKTFragment() {
    private var recycleview : LQRRecyclerView?= null
    private var adapter : LQRAdapterForRecyclerView<Any>?= null

    override val layoutId: Int
        get() = R.layout.fragment_unsolved

    override fun onInitView(bundle: Bundle?) {
        recycleview = rootView?.findViewById(R.id.user_recyclerview_unsolved)
        adapter = object : LQRAdapterForRecyclerView<Any>(context, ArrayList<Any>(), R.layout.item_problems_todeal) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
            }

        }
        recycleview?.adapter = adapter?.headerAndFooterAdapter
        for (item: Int in 1..6) {
            adapter?.addLastItem(HomeDataBean("1", "1", "1", "1"))
            adapter?.addLastItem(HomeDataBean("1", "1", "1", "1"))
        }
    }


    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }

}