package com.android.com.kline.fragments

import android.os.Bundle

import com.android.com.kline.R
import com.cjwsc.idcm.base.ui.widget.NoScroolListView
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.lqr.adapter.LQRAdapterForAbsListView
import com.lqr.adapter.LQRViewHolderForAbsListView

class KlineCoinIntroductionFragment : BaseKTFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_coin_introduction_layout

    override fun onInitView(bundle: Bundle?) {
    }

    override fun onEvent() {
    }

    override fun lazyFetchData() {

    }
}
