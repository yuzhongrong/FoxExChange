package com.foxexchange.user.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.adapter.MyPagerAdapter
import com.foxexchange.user.fragments.UserInviteRecordFragment
import com.foxexchange.user.fragments.UserReturnCommissionRecordFragment
import kotlinx.android.synthetic.main.activity_return_commission_detail.*

/**
 * 返佣详情
 * @Ling
 */
@Route(path = "/user/activity/UserReturnCommissionDetailActivity")
class UserReturnCommissionDetailActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    private val titles : Array<String> = arrayOf("邀请记录", "返佣记录")

    private val fragmentList : ArrayList<Fragment> =
            arrayListOf(UserInviteRecordFragment(), UserReturnCommissionRecordFragment())

    override val layoutId: Int
        get() = R.layout.activity_return_commission_detail

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_return_commission_detail)

        vp_invite_detail.adapter = MyPagerAdapter(supportFragmentManager, fragmentList)
        tab_invite_detail.setViewPager(vp_invite_detail, titles, this, fragmentList)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()
        }
    }
}