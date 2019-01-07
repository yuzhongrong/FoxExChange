package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_bing_google_validator.*

/**
 * 绑定谷歌身份验证器
 */
@Route(path = "/user/activity/UserBindGoogleValidatorActivity")
class UserBindGoogleValidatorActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_bing_google_validator

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_bing_google_identity_validator)

    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)

        btn_google_copy.setOnClickListener(this::onClick)
        btn_google_next_step.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()

            btn_google_copy -> {  //复制

            }

            btn_google_next_step -> {  //下一步
                ARouter.getInstance().build("/user/activity/UserGoogleVerificationCodeActivity")
                        .navigation()
            }
        }
    }

}