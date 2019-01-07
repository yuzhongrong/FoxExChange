package com.foxexchange.android.fragments

import android.os.Bundle
import android.widget.TextView

import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.foxexchange.android.R
import com.foxexchange.android.beans.IncreaseDownBean
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

import java.util.ArrayList

class IncreaseorDownKTFragment :BaseKTFragment() {

    var adapter:LQRAdapterForRecyclerView<Any>?=null
    var lqrRecyclerView:LQRRecyclerView?=null

    override val layoutId: Int
        get() = R.layout.fragment_increaseordown



    override fun onInitView(bundle: Bundle?) {



    }



    override fun onEvent() {


    }

    override fun lazyFetchData() {

    }
}
