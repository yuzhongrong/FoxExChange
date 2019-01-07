package com.foxexchange.exchange.widget

import android.content.Context
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cjwsc.idcm.flycotablayout.SlidingTabLayoutInner
import com.cjwsc.idcm.kotlin.base.BaseKTActivity

import com.example.exchange.R
import com.flyco.tablayout.SlidingTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.foxexchange.exchange.fragments.CurrentDelegationFragment
import com.foxexchange.exchange.fragments.HistoryDelegationFragment
import com.foxexchange.common.adapter.MyPagerAdapter



/**
 * created by pzw on 2018/12/26.
 */
class ExchangeFragmentFootview @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {


    private var tabLayout: SlidingTabLayoutInner? = null
    private var mViewPager: ViewPager? = null
    private val fragments = ArrayList<Fragment>()
    private val titles = arrayOf("当前委托", "历史委托")
    private val mTabEntities = ArrayList<CustomTabEntity>()
    var adapter: com.foxexchange.common.adapter.MyPagerAdapter?=null



    init {
        initView(context)
    }

    private fun initView(context: Context) {
        val footView = View.inflate(context, R.layout.footview_exchange_fragment, this) as LinearLayout
        //        footView.findViewById()
        tabLayout = footView?.findViewById(R.id.exchange_slidingTabLayout)
        mViewPager = footView?.findViewById(R.id.exchange_viewpager)

        val currentDelegationFragment = CurrentDelegationFragment()
        val historyDelegationFragment = HistoryDelegationFragment()
        fragments.add(currentDelegationFragment)
        fragments.add(historyDelegationFragment)
        for (i in 0..1) {
            val tabEntity = object : CustomTabEntity {
                override fun getTabTitle(): String {
                    return titles[i]
                }

                override fun getTabSelectedIcon(): Int {
                    return 0
                }

                override fun getTabUnselectedIcon(): Int {
                    return 0
                }
            }
            mTabEntities.add(tabEntity)
        }
     //   mViewPager!!.adapter = MyPagerAdapter((context as BaseKTActivity).supportFragmentManager)
        var exchangefragment=(context as BaseKTActivity).supportFragmentManager.findFragmentByTag("交易")
        var manager= exchangefragment.childFragmentManager
//        mViewPager!!.adapter= SlidingTabLayoutInner.InnerPagerAdapter(manager,fragments,titles)



        adapter = MyPagerAdapter(manager, fragments)
        mViewPager?.setAdapter(adapter)


//        tabLayout!!.setViewPager(mViewPager)

        tabLayout?.setViewPager(mViewPager,  titles,manager, fragments)

        //tab滑动监听
//        tabLayout!!.setOnTabSelectListener(object : OnTabSelectListener {
//            override fun onTabSelect(position: Int) {
//                mViewPager!!.resetHeight(position)
//            }
//
//            override fun onTabReselect(position: Int) {
//                mViewPager!!.resetHeight(position)
//            }
//        })
       // addView(footView)
    }

    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
    }
}
