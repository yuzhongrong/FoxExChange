package com.foxexchange.user.activity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.user.fragments.UserBiBiFragment
import com.foxexchange.user.fragments.UserLegalBiFragment
import kotlinx.android.synthetic.main.activity_my_orders.*

/**
 * 我的订单
 * @Ling
 */
@Route(path = "/user/activity/UserMyOrdersActivity", extras = 0)
class UserMyOrdersActivity : BaseFoxExChangeKTActivity() {

    private val titles : Array<String> = arrayOf("法币订单", "币币订单")

    private val fragmentList : ArrayList<Fragment> =
            arrayListOf(UserLegalBiFragment(), UserBiBiFragment())

    override val layoutId: Int
        get() = R.layout.activity_my_orders

    override fun onInitView(bundle: Bundle?) {
        my_orders_segment_tab.setTabData(titles, this, R.id.fragment_container, fragmentList)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?){
        when(v) {
            img_back -> finish()

        }
    }
}