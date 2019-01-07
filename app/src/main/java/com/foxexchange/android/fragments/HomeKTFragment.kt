package com.foxexchange.android.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.flyco.tablayout.SegmentTabLayout
import com.foxexchange.android.R
import com.foxexchange.android.beans.HomeDataBean
import com.foxexchange.android.utils.GlideImageLoader
import com.cjwsc.idcm.base.ui.widget.NoScroolListView
import com.lqr.adapter.LQRAdapterForAbsListView
import com.lqr.adapter.LQRViewHolderForAbsListView
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

class HomeKTFragment : BaseKTFragment() {


    var adapter:LQRAdapterForAbsListView<Any>?=null
    var titles= mutableListOf<String>("涨幅榜","跌幅榜")
    var fragments:ArrayList<Fragment>?= arrayListOf(IncreaseorDownKTFragment(),IncreaseorDownKTFragment())
    var homelist: NoScroolListView?=null

    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    override fun onInitView(bundle: Bundle?) {

        homelist=rootView?.findViewById<NoScroolListView>(R.id.homelist)


        adapter=object : LQRAdapterForAbsListView<Any>(activity,ArrayList<Any>(),R.layout.item_home_list){
            override fun convert(helper: LQRViewHolderForAbsListView?, item: Any?, position: Int) {
            }

        }

        initData()
        homelist?.adapter=adapter

        for(item:Int in 1..9){
            adapter?.addLastItem(HomeDataBean("1","1","1","1"))

        }



    }


    fun initData(){



       var tab= rootView?.findViewById<SegmentTabLayout>(R.id.segmenttablayout)

        tab?.setTabData(titles.toTypedArray(),activity,
                R.id.tablayoutcontainer,fragments)


        initBanner()





    }


    override fun onEvent() {

    }

    override fun lazyFetchData() {

    }




    private fun initBanner() {
        var banner=rootView?.findViewById<Banner>(R.id.banner)

        //设置banner样式
        banner?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        banner?.setImageLoader(GlideImageLoader())
        //设置图片集合
       // banner.setImages(uris)
        //设置banner动画效果
        banner?.setBannerAnimation(Transformer.Default)
        //设置标题集合（当banner样式有显示title时）
        //
        //  banner.setBannerTitles(titles)
        //设置自动轮播，默认为true
        banner?.isAutoPlay(true)
        //设置轮播时间
        banner?.setDelayTime(10000)
        //设置指示器位置（当banner模式中有指示器时）
        banner?.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        banner?.start()

        banner?.setOnBannerListener {
//            val intent = Intent(activity, WebViewActivity::class.java)
//            intent.putExtra("url", "https://cmccoin.io/")
//
//            startActivity(intent)
        }



    }
}