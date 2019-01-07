package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 绑定邮箱
 */
@Route(path = "/user/activity/UserBindEMailActivity")
class UserBindEMailActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_bind_email

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_user_bind_email)
    }

    override fun onEvent() {
        img_back.setOnClickListener{finish()}
    }

}