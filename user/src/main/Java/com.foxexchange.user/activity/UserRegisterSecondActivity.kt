package com.foxexchange.user.activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_register_second.*

/**
 * 注册第二步
 * @Ling
 */
@Route(path = "/user/activity/UserRegisterSecondActivity")
class UserRegisterSecondActivity : BaseFoxExChangeKTActivity() {

    var eye1 : Boolean = false
    var eye2 : Boolean = false

    override val layoutId: Int
        get() = R.layout.activity_register_second

    override fun onInitView(bundle: Bundle?) {
        tv_title.text = getString(R.string.str_register)
    }


    override fun onEvent() {
        tv_cancel.setOnClickListener(this::onClick)

        img_eye1.setOnClickListener(this::onClick)
        img_eye2.setOnClickListener(this::onClick)
    }

    fun onClick(v: View?) {
        when (v) {
            tv_cancel -> finish()

            img_eye1 -> {
                eye1 = !eye1
                if (eye1){
                    img_eye1.setImageResource(R.mipmap.icon_eye_open)
                    et_register_pwd1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    img_eye1.setImageResource(R.mipmap.icon_eye_close)
                    et_register_pwd1.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                et_register_pwd1.setSelection(et_register_pwd1.text.toString().length)
            }

            img_eye2 -> {
                eye2 = !eye2
                if (eye2){
                    img_eye2.setImageResource(R.mipmap.icon_eye_open)
                    et_register_pwd2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    img_eye2.setImageResource(R.mipmap.icon_eye_close)
                    et_register_pwd2.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                et_register_pwd2.setSelection(et_register_pwd2.text.toString().length)
            }
        }
    }
}