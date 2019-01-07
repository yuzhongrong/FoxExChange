package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 提币
 */
@Route(path = "/user/activity/UserWithDrawCoinActivity", extras = 0)
class UserWithDrawCoinActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView
    lateinit var img_right : ImageView

    override val layoutId: Int
        get() = R.layout.activity_withdraw_coin

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        img_right = findViewById(R.id.img_right)
        img_right.visibility = View.VISIBLE
        tv_title.text = getText(R.string.str_withdraw_coins)
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