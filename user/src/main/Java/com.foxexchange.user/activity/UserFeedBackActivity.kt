package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 关于我们
 * @Ling
 */
@Route(path = "/user/activity/UserFeedBackActivity", extras = 0)
class UserFeedBackActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_feedback

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_user_feed_back)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()
        }
    }

}