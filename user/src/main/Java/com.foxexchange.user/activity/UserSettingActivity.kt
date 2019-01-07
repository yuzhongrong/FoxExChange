package com.foxexchange.user.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * 设置
 * @Ling
 */
@Route(path = "/user/activity/UserSettingActivity")
class UserSettingActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_setting

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getText(R.string.str_personal_setting)
    }

    override fun onEvent() {
        img_back.setOnClickListener{ finish()}
        user_rl_aboutus.setOnClickListener{
            ARouter.getInstance()
                    .build("/user/activity/UserAboutUsActivity").navigation()
        }
    }


}