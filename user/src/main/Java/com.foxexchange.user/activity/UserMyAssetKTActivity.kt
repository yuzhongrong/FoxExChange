package com.foxexchange.user

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.adapter.MyPagerAdapter
import com.foxexchange.user.fragments.UserC2CAssetKTFragment
import com.foxexchange.user.fragments.UserCoinAssetKTFragment
import kotlinx.android.synthetic.main.activity_my_asset_layout.*

/**
 * 我的资产
 * @Ling
 */
@Route(path = "/user/activity/UserMyAssetKTActivity",extras = 0)
class UserMyAssetKTActivity : BaseFoxExChangeKTActivity(), ViewPager.OnPageChangeListener {

    var tabs : Array<String> =
            arrayOf( "币币资产", "法币资产")

    //fragment集合
    private var fragmentList : ArrayList<Fragment> = arrayListOf(
             UserCoinAssetKTFragment(), UserC2CAssetKTFragment())

    private var img_back : ImageView ?= null
    private var tv_title : TextView ?= null

    override val layoutId: Int
        get() = R.layout.activity_my_asset_layout

    override fun onInitView(bundle: Bundle?) {
        tv_title = findViewById(R.id.tv_title)
        img_back = findViewById(R.id.img_back)
        tv_title?.text = getString(R.string.str_my_asset)
        frag_vp.adapter = MyPagerAdapter(supportFragmentManager, fragmentList)
        frag_vp.addOnPageChangeListener(this)
        frg_tab.setViewPager(frag_vp, tabs, this, fragmentList)
    }

    override fun onEvent() {
        img_back?.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()
        }
    }


    override fun onPageSelected(position: Int) {
        when(position){
            0 -> {
//                tv_account_asset.text = "我的资产"
            }
            1 ->{
//                tv_account_asset.text = "币币账户资产"
            }
            2 ->{
//                tv_account_asset.text = "C2C账户资产"
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}

