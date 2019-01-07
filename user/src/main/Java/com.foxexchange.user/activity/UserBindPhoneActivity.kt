package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 绑定手机
 */
@Route(path = "/user/activity/UserBindPhoneActivity")
class UserBindPhoneActivity : BaseFoxExChangeKTActivity(){

    private var img_back : ImageView?= null
    private var tv_title : TextView?= null

    override val layoutId: Int
        get() = R.layout.activity_bind_phone

    override fun onInitView(bundle: Bundle?) {
        tv_title = findViewById(R.id.tv_title)
        img_back = findViewById(R.id.img_back)
        tv_title?.text = getString(R.string.str_bind_phone)
    }

    override fun onEvent() {
        img_back?.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()
        }
    }

}