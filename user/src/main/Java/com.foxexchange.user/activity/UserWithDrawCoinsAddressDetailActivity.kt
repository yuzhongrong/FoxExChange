package com.foxexchange.user.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 提币地址详情
 */
@Route(path = "/user/activity/UserWithDrawCoinsAddressDetailActivity")
class UserWithDrawCoinsAddressDetailActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_withdraw_coins_address_detail

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_with_draw_address)
    }

    override fun onEvent() {
        img_back.setOnClickListener{finish()}
    }
}