package com.foxexchange.c2c.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.cjwsc.idcm.flycotablayout.SlidingTabLayoutInner
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.foxexchange.c2c.R

import com.foxexchange.common.adapter.MyPagerAdapter
import java.util.ArrayList

/**
 * 法币Fragment
 * @Ling
 */
class C2CLegalBiFragment : BaseKTFragment() {
    private var legal_tab: SlidingTabLayoutInner? = null
    private val fragmentList = ArrayList<Fragment>()
    private val titles = arrayOf("进行中", "已完成")
    private var viewPager: ViewPager? = null

    override val layoutId: Int
        get() = R.layout.fragment_c2c_legal_bi

    override fun onInitView(bundle: Bundle?) {
//        frg_tab = `$`(R.id.frg_tab) as SlidingTabLayout
//        viewPager = `$`(R.id.frag_vp) as ViewPager
        legal_tab = rootView?.findViewById(R.id.frag_tab)
        viewPager = rootView?.findViewById(R.id.frag_vp)
        fragmentList.add(C2CFaBiFragmenting())
        fragmentList.add(C2CFaBiFragmented())
        viewPager?.adapter = MyPagerAdapter(childFragmentManager, fragmentList)
        legal_tab?.setViewPager(viewPager, titles, childFragmentManager, fragmentList)
    }

    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }

}