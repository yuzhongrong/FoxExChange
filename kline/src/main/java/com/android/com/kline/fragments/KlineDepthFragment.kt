package com.android.com.kline.fragments

import android.os.Bundle

import com.android.com.kline.R
import com.cjwsc.idcm.base.ui.widget.NoScroolListView
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.lqr.adapter.LQRAdapterForAbsListView
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForAbsListView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import java.util.*

class KlineDepthFragment : BaseKTFragment() {

    var lqrAdapterForListView:LQRAdapterForAbsListView<Object>?=null
    var lqrListView: NoScroolListView?=null
    override val layoutId: Int
        get() = R.layout.fragment_depth_layout

    override fun onInitView(bundle: Bundle?) {
        lqrListView=`$`<NoScroolListView>(R.id.kline_depth_lqrlist)
        lqrAdapterForListView=object :LQRAdapterForAbsListView<Object>(activity, arrayListOf()
                ,R.layout.kline_item_depth){
            override fun convert(helper: LQRViewHolderForAbsListView?, item: Object?, position: Int) {
            }


        }

        lqrListView?.adapter=lqrAdapterForListView
    }

    override fun onEvent() {

        for(i in 1..5)lqrAdapterForListView?.addLastItem(Object())




    }

    override fun lazyFetchData() {

    }
}
