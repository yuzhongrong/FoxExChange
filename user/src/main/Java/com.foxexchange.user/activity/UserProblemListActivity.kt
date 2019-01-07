package com.foxexchange.user.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.flyco.tablayout.SlidingTabLayout

import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.adapter.MyPagerAdapter
import com.foxexchange.user.fragments.UserUnsolvedKTFragment

/**
 * created by pzw on 2019/1/2.
 */

@Route(path = "/user/activity/UserProblemListActivity", extras = 0)
class UserProblemListActivity : BaseFoxExChangeKTActivity(), ViewPager.OnPageChangeListener  {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

    var tabs : Array<String> =
            arrayOf( "未解决", "已解决")

    private var img_back : ImageView?= null
    private var tv_title : TextView?= null
    private var viewpager : ViewPager?= null
    private var tablayout : SlidingTabLayout?= null

    //fragment集合
    private var fragmentList : ArrayList<Fragment> = arrayListOf(
            UserUnsolvedKTFragment(), UserUnsolvedKTFragment())

    override val layoutId: Int
        get() = R.layout.activity_problem_list

    override fun onInitView(bundle: Bundle?) {
        tv_title = findViewById(R.id.tv_title)
        img_back = findViewById(R.id.img_back)
        viewpager = findViewById(R.id.user_problem_viewpager)
        tablayout = findViewById(R.id.user_problem_detail)
        tv_title?.text = getString(R.string.str_user_problem_list)
    }

    override fun onEvent() {
        img_back?.setOnClickListener(this::onClick)
        viewpager?.adapter = MyPagerAdapter(supportFragmentManager, fragmentList)
        viewpager?.addOnPageChangeListener(this)
        tablayout?.setViewPager(viewpager, tabs, this, fragmentList)
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()
        }
    }
}
