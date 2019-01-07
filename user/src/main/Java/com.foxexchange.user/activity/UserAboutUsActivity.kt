package com.foxexchange.user.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_about_us.*

/**
 * 关于我们
 * @Ling
 */
@Route(path = "/user/activity/UserAboutUsActivity", extras = 0)
class UserAboutUsActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_about_us

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_about_us)
    }

    override fun onEvent() {
        img_back.setOnClickListener{ finish()}

        connect_us_ly.setOnClickListener{
            ARouter.getInstance().build("/user/activity/UserConnectUsActivity")
                    .navigation()
        }
    }

}