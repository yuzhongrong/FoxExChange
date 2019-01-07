package com.foxexchange.user.activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录
 */
@Route(path = "/user/activity/UserLoginActivity")
class UserLoginActivity : BaseFoxExChangeKTActivity() {

    var isShow : Boolean = false

    override val layoutId: Int
        get() = R.layout.activity_login

    override fun onInitView(bundle: Bundle?) {

    }

    override fun onEvent() {
        tv_cancel.setOnClickListener(this::onClick)

        tv_register.setOnClickListener(this::onClick)
        tv_forget_pwd.setOnClickListener(this::onClick)

        img_login_eye.setOnClickListener(this::onClick)
    }

    fun onClick(v : View? ) {
        when (v) {
            tv_cancel -> finish()

            tv_register -> {  //注册
                ARouter.getInstance().build("/user/activity/UserRegisterActivity")
                        .navigation()
            }

            tv_forget_pwd -> { //忘记密码
                ARouter.getInstance().build("/user/activity/UserResetPasswordActivity")
                        .navigation()
            }

            img_login_eye -> {
                isShow = !isShow
                if (isShow){   //显示
                    img_login_eye.setBackgroundResource(R.mipmap.icon_eye_open)
                    et_login_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {       //隐藏
                    img_login_eye.setBackgroundResource(R.mipmap.icon_eye_close)
                    et_login_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                et_login_pwd.setSelection(et_login_pwd.text.toString().length)
            }


        }
    }

}