package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.orhanobut.logger.Logger

/**
 * 个人信息
 */
@Route(path ="/user/activity/UserPersonalInfoActivity", extras = 1)
class UserPersonalInfoActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView


    @Autowired
    lateinit var test:String

    override val layoutId: Int
        get() = R.layout.activity_personal_info

    override fun onInitView(bundle: Bundle?) {
        ARouter.getInstance().inject(this)
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_personal_info)
        Logger.d("-----aaa---->$test")
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