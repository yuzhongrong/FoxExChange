package com.foxexchange.user.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_security_center.*

/**
 * 联系我们
 * @Ling
 */
@Route(path = "/user/activity/UserConnectUsActivity")
class UserConnectUsActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_connect_us

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getText(R.string.str_connect_us)
    }

    override fun onEvent() {
        img_back.setOnClickListener{ finish()}
    }


}