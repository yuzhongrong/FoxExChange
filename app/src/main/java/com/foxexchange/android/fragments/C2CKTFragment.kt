package com.foxexchange.android.fragments

import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.flyco.tablayout.SegmentTabLayout
import com.foxexchange.android.R
import com.foxexchange.android.R.id.fragmentcontainer
import com.foxexchange.c2c.fragments.C2CTradeInFragment
import com.foxexchange.c2c.fragments.C2CTradeOutFragment
import com.foxexchange.c2c.popwindow.C2CTopMenuPop
import com.lqr.adapter.LQRAdapterForRecyclerView

import java.util.*

class C2CKTFragment : BaseKTFragment() {


    var icon_list:ImageView?=null
    var title= mutableListOf<String>("买入","卖出")
    var fragments= ArrayList<Fragment>()
    var segmenttablayout: SegmentTabLayout?=null
    var lqrAdapterForRecyclerView:LQRAdapterForRecyclerView<Object>?=null
    override val layoutId: Int
        get() = R.layout.fragment_c2c

    override fun onInitView(bundle: Bundle?) {
        icon_list=rootView?.findViewById(R.id.icon_list)
        fragments.add(C2CTradeInFragment())
        fragments.add(C2CTradeOutFragment())
        segmenttablayout=rootView?.findViewById(R.id.segmenttablayout)
       segmenttablayout?.setTabData(title.toTypedArray(),activity,R.id.fragmentcontainer, fragments as ArrayList<Fragment>?)
    }
    override fun onEvent() {

        icon_list?.setOnClickListener { C2CTopMenuPop(activity).showPopupWindow(icon_list) }
    }
    override fun lazyFetchData() {
    }
}