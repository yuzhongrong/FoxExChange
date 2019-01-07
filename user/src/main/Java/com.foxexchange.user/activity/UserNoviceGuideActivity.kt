package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R

import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.user.beans.HomeDataBean
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 * created by pzw on 2019/1/2.
 */

@Route(path = "/user/activity/UserNoviceGuideActivity", extras = 0)
class UserNoviceGuideActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView
    lateinit var recyclerView : LQRRecyclerView
    lateinit var adapter : LQRAdapterForRecyclerView<Any>


    override val layoutId: Int
        get() = R.layout.activity_novice_guide

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        recyclerView = findViewById(R.id.user_feedback_recyclerview)
        tv_title.text = getString(R.string.str_user_novice_guide)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
        adapter = object : LQRAdapterForRecyclerView<Any>(this, ArrayList<Any>(), R.layout.item_novice_guide) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
            }

        }
        recyclerView.adapter = adapter.headerAndFooterAdapter
        for (item: Int in 1..6) {
            adapter.addLastItem(HomeDataBean("1", "1", "1", "1"))
        }
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()
        }
    }
}
