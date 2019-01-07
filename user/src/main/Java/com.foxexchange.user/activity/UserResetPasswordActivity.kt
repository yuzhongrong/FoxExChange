package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_reset_password.*

/**
 * 重置密码
 * @Ling
 */
@Route(path = "/user/activity/UserResetPasswordActivity")
class UserResetPasswordActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_reset_password

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_reset_pwd)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
        btn_reset.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()

            btn_reset -> { //重置第二步
                ARouter.getInstance().build("/user/activity/UserResetPwdSecondActivity")
                        .navigation()
            }
        }
    }
}