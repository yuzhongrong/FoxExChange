package com.foxexchange.android.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.foxexchange.android.R
import com.foxexchange.exchange.beans.CurrentDelegationBean
import com.foxexchange.exchange.widget.ExchangeFragmentFootview
import com.foxexchange.exchange.widget.ExchangeHorizontalLinearlayout
import com.foxexchange.exchange.widget.ExchangeVerticalLinearlayout
import com.lqr.adapter.LQRAdapterForAbsListView
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForAbsListView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import java.util.ArrayList

class ExchangeKTFragment1 : BaseKTFragment() {
    private var recycleView : LQRRecyclerView? = null
    var adapter: LQRAdapterForRecyclerView<Any>?=null
    var contianer : LinearLayout? = null

    override val layoutId: Int
        get() = R.layout.fragment_exchange_layout1

    override fun onInitView(bundle: Bundle?) {
//        recycleView = rootView?.findViewById(R.id.exchange_lqr_list)
        contianer = rootView?.findViewById(R.id.contianer)
    }

    override fun onEvent() {
//        adapter = object : LQRAdapterForRecyclerView<Any>(context, ArrayList<Any>(), com.example.exchange.R.layout.emptyview) {
//            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
//            }
//
//        }
//        adapter = object : LQRAdapterForAbsListView<CurrentDelegationBean>(activity, ArrayList(), R.layout.emptyview) {
//            override fun convert(helper: LQRViewHolderForAbsListView, item: CurrentDelegationBean, position: Int) {
//            }
//        }

//        adapter?.headerAndFooterAdapter?.addHeaderView(ExchangeVerticalLinearlayout(activity))
//        adapter?.headerAndFooterAdapter?.addFooterView(ExchangeFragmentFootview(activity))
//        adapter?.headerAndFooterAdapter?.addHeaderView(ExchangeHorizontalLinearlayout(activity))
//        adapter?.headerAndFooterAdapter?.addFooterView(ExchangeHorizontalLinearlayout(activity))
//        recycleView?.adapter = adapter?.headerAndFooterAdapter

//        adapter=object : LQRAdapterForAbsListView<Any>(activity,ArrayList<Any>(),R.layout.emptyview){
//            override fun convert(helper: LQRViewHolderForAbsListView?, item: Any?, position: Int) {
//            }
//        }

//        adapter?.addLastItem(Object())
//        contianer?.addView(ExchangeVerticalLinearlayout(activity))
//        contianer?.addView(ExchangeVerticalLinearlayout(activity))
//        contianer?.addView(ExchangeFragmentFootview(activity))
//        recycleView?.addFooterView(ExchangeFragmentFootview(activity))
//        adapter?.notifyDataSetChanged()
    }


    override fun lazyFetchData() {

    }


}