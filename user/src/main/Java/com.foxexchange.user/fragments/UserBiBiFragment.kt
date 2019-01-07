package com.foxexchange.user.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import com.cjwsc.idcm.flycotablayout.SlidingTabLayoutInner
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.foxexchange.common.adapter.MyPagerAdapter
import java.util.ArrayList

/**
 * 币币订单
 * @Ling
 */
class UserBiBiFragment : BaseKTFragment(){

    private var bibi_tab : SlidingTabLayoutInner ?= null

    private var frag_vp : ViewPager ?= null

    private val fragmentList = ArrayList<Fragment>()

    private val titles : Array<String> =
            arrayOf("当前委托", "历史委托")

    override val layoutId: Int
        get() = R.layout.fragment_legal_bi

    override fun onInitView(bundle: Bundle?) {
        bibi_tab = rootView?.findViewById(R.id.frag_tab)
        frag_vp = rootView?.findViewById(R.id.frag_vp)

        fragmentList.add(UserBiBiEntrustNowFragment())
        fragmentList.add(UserBiBiEntrustHistoryFragment())

        frag_vp?.adapter = MyPagerAdapter(childFragmentManager, fragmentList)

        bibi_tab?.setViewPager(frag_vp, titles, childFragmentManager, fragmentList)

    }

    override fun lazyFetchData() {

    }


    override fun onEvent() {

    }

    fun onClick(v : View?) {
        when(v){

        }
    }



}