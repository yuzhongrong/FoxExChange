package com.foxexchange.user.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 添加地址
 */
@Route(path = "/user/activity/UserAddAddressActivity")
class UserAddAddressActivity : BaseFoxExChangeKTActivity() {
    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_add_address

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_user_add_address)
    }

    override fun onEvent() {
        img_back.setOnClickListener{finish()}
    }
}