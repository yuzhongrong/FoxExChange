package com.foxexchange.android.fragments

import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.Utils.ACacheUtil
import com.cjwsc.idcm.Utils.ToastUtil
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.flyco.tablayout.SlidingTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.foxexchange.android.R
import com.foxexchange.common.widgets.AutoHeightViewPager
import com.foxexchange.exchange.fragments.CurrentDelegationFragment
import com.foxexchange.exchange.fragments.HistoryDelegationFragment
import com.foxexchange.exchange.widget.*

class ExchangeKTFragment : BaseKTFragment() {
    private val titles = arrayOf("当前委托", "历史委托")
    private var ivMenuPop : TextView ? = null
    private var popWinMenu: PopWinMenu? = null
    private var scrollView: ScrollView? = null
    private var tvAll: TextView? = null
    private var tabLayout: SlidingTabLayout? = null
    private var mViewPager: AutoHeightViewPager? = null
    private val fragments = ArrayList<Fragment>()
    private val mTabEntities = ArrayList<CustomTabEntity>()
    private var tradeContainer : LinearLayout ?= null


    override val layoutId: Int
        get() = R.layout.fragment_exchange_layout

    override fun onInitView(bundle: Bundle?) {
        ACacheUtil.get(activity).clear()
        scrollView = rootView?.findViewById(R.id.exchange_scrollview)
        mViewPager = rootView?.findViewById(R.id.exchange_viewpager)
        tabLayout = rootView?.findViewById(R.id.exchange_slidingTabLayout)
        ivMenuPop = rootView?.findViewById(R.id.exchange_iv_menu)
        tvAll = rootView?.findViewById(R.id.tv_exchange_all)
        tvAll?.paint?.flags = Paint. UNDERLINE_TEXT_FLAG     //下划线
        tvAll?.paint?.isAntiAlias = true//抗锯齿
        tradeContainer = rootView?.findViewById(R.id.exchange_trade_container)
        tradeContainer?.addView(ExchangeVerticalLinearlayout(activity))
    }

    override fun onEvent() {
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


        mViewPager!!.adapter = MyPagerAdapter(childFragmentManager)
        tabLayout?.setViewPager(mViewPager)
        mViewPager?.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mViewPager?.requestLayout()
            }

        })

        ivMenuPop?.setOnClickListener({this.onClick(it)})
        tvAll?.setOnClickListener({this.onClick(it)})
    }


    fun onClick(v: View) {
        val i = v.id
       if(i == R.id.exchange_iv_menu) {
            popWinMenu = null
            popWinMenu = PopWinMenu(activity, ClickListenerMenu,
                        ivMenuPop!!.width / 2, 0)
            popWinMenu = PopWinMenu(activity, ClickListenerMenu,
                    ivMenuPop!!.width / 2, 0)
            val location = IntArray(2)
            ivMenuPop!!.getLocationOnScreen(location)
            popWinMenu!!.showAsDropDown(ivMenuPop,0,20)
            popWinMenu!!.showAtLocation(ivMenuPop, Gravity.NO_GRAVITY, location[0], location[1] + ivMenuPop!!.height - popWinMenu!!.height)
        }else if(i == R.id.tv_exchange_all) {
           ARouter.getInstance().build("/user/activity/UserMyOrdersActivity")
                   .withString("", "")
                   .navigation(mContext)
       }
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

    /**弹出框的事件 */
    private val ClickListenerMenu = View.OnClickListener { v ->
        when (v.id) {
            R.id.exchange_order_centerpop ->
                startOrderCenterActivity()
            R.id.exchange_order_coincharging ->
                strartCoinchargingActivity()
            R.id.exchange_order_handicapchange -> changeHorizontalLayout()
            R.id.exchange_order_addoptional -> addOptional()
        }
    }

    private fun strartCoinchargingActivity() {
        ARouter.getInstance().build("/user/activity/UserRechargeCoinActivity")
                .withString("", "")
                .navigation(mContext)
        popWinMenu?.dismiss()
    }

    private fun startOrderCenterActivity() {
        ARouter.getInstance().build("/user/activity/UserMyOrdersActivity")
                .withString("", "")
                .navigation(mContext)
        popWinMenu?.dismiss()
    }

    private fun addOptional() {
        ToastUtil.show("添加自选成功!")
        popWinMenu?.dismiss()
    }

    private fun changeHorizontalLayout() {
        if(ACacheUtil.get(activity).getAsBoolean("isHorizontal",false)) {
            tradeContainer?.removeAllViews()
            tradeContainer?.addView(ExchangeVerticalLinearlayout(activity))
            ACacheUtil.get(activity).put("isHorizontal",false)
        }else {
            tradeContainer?.removeAllViews()
            tradeContainer?.addView(ExchangeHorizontalLinearlayout(activity))
            ACacheUtil.get(activity).put("isHorizontal",true)
        }
        popWinMenu!!.dismiss()
    }
}