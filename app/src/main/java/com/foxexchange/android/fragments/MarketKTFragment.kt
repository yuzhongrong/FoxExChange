package com.foxexchange.android.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.cjwsc.idcm.kotlin.base.BaseKTFragment

import com.flyco.tablayout.SlidingTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.foxexchange.android.R
import com.foxexchange.market.fragments.OptionalFragment
import com.foxexchange.market.fragments.UPFragment
import com.foxexchange.market.fragments.USDTMarketFragment
import com.foxexchange.market.views.MarketItemSelectLayout

import java.util.ArrayList

/**
 * created by pzw on 2018/12/11.
 */
class MarketKTFragment : BaseKTFragment(){

    private var tabLayout: SlidingTabLayout? = null
    private val titles = arrayOf("UP", "USDT", "自选")
    private val fragments = ArrayList<Fragment>()
    private var mViewPager: ViewPager? = null
    private val mTabEntities = ArrayList<CustomTabEntity>()
    private var marketLayoutSort: MarketItemSelectLayout? = null

    override val layoutId: Int
        get() = R.layout.fragment_market_layout

    override fun onInitView(bundle: Bundle?) {
        tabLayout = rootView?.findViewById(R.id.commonTabLayout)
        mViewPager = rootView?.findViewById(R.id.viewpager)
        marketLayoutSort = rootView?.findViewById(R.id.market_layout_sort)
    }

    override fun onEvent() {
        fragments.add(UPFragment())
        fragments.add(USDTMarketFragment())
        fragments.add(OptionalFragment())
        for (i in 0..2) {
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
        mViewPager!!.adapter = MyPagerAdapter(activity.supportFragmentManager)
        tabLayout!!.setViewPager(mViewPager!!)
        tabLayout!!.currentTab = 0

        //tab滑动监听
        tabLayout!!.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //viewpager上面的排序方式
                marketLayoutSort!!.setTabSelectIcon(position)
            }

            override fun onTabReselect(position: Int) {
            }
        })

        mViewPager!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                marketLayoutSort!!.setTabSelectIcon(position)
            }

        })
        marketLayoutSort!!.setOnMarketSortClickListenner(object : MarketItemSelectLayout.OnMarketSortClickListenner{
            override fun clicked(positon: Int, sortType: String?) {
//                ToastUtil.show(positon.toString())
            }

        })
    }

    override fun lazyFetchData() {

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
