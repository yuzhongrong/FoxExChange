package com.foxexchange.common.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

/**
 * fragmentPagerAdapter 公用类
 *
 */
class MyPagerAdapter(fm: FragmentManager,
                     fragmentList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    private var mFragments = ArrayList<Fragment>()

    init {
        this.mFragments = fragmentList
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

}
