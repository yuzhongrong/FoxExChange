package com.foxexchange.user.activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_modify_login_pwd.*

/**
 * 修改登录密码
 */
@Route(path = "/user/activity/UserModifyLoginPwdActivity")
class UserModifyLoginPwdActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    var isOld : Boolean = false
    var isNew : Boolean = false
    var isNew2 : Boolean = false

    override val layoutId: Int
        get() = R.layout.activity_modify_login_pwd

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_modify_login_password)

    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
        img_old_eye.setOnClickListener(this::onClick)
        img_new_eye1.setOnClickListener(this::onClick)
        img_new_eye2.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()

            img_old_eye -> {
                isOld = !isOld
                if (isOld){
                    et_old_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    img_old_eye.setImageResource(R.mipmap.icon_eye_open)
                } else {
                    et_old_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                    img_old_eye.setImageResource(R.mipmap.icon_eye_close)
                }
                et_old_pwd.setSelection(et_old_pwd.text.toString().length)
            }

            img_new_eye1 -> {
                isNew = !isNew
                if (isNew){
                    et_new_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    img_new_eye1.setImageResource(R.mipmap.icon_eye_open)
                } else {
                    et_new_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                    img_new_eye1.setImageResource(R.mipmap.icon_eye_close)
                }
                et_new_pwd.setSelection(et_new_pwd.text.toString().length)
            }

            img_new_eye2 -> {
                isNew2 = !isNew2
                if (isNew2){
                    et_confirm_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    img_new_eye2.setImageResource(R.mipmap.icon_eye_open)
                } else {
                    et_confirm_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                    img_new_eye2.setImageResource(R.mipmap.icon_eye_close)
                }
                et_confirm_pwd.setSelection(et_confirm_pwd.text.toString().length)
            }
        }
    }

}