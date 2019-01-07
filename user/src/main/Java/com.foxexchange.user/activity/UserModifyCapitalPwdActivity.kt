package com.foxexchange.user.activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_modify_capital_password.*

/**
 * 修改资金密码
 */
@Route(path = "/user/activity/UserModifyCapitalPwdActivity")
class UserModifyCapitalPwdActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    private var isPwd : Boolean = false
    private var isPwd2 : Boolean = false

    override val layoutId: Int
        get() = R.layout.activity_modify_capital_password

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_user_modify_capital_pwd)
    }

    override fun onEvent() {
        img_back.setOnClickListener{finish()}

        img_capital_eye1.setOnClickListener{
            isPwd = !isPwd
            if (isPwd){
                et_capital_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                img_capital_eye1.setImageResource(R.mipmap.icon_eye_open)
            } else {
                et_capital_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                img_capital_eye1.setImageResource(R.mipmap.icon_eye_close)
            }
            et_capital_pwd.setSelection(et_capital_pwd.text.toString().trim().length)
        }

        img_capital_eye2.setOnClickListener{
            isPwd2 = !isPwd2
            if (isPwd2){
                et_confirm_capital_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                img_capital_eye2.setImageResource(R.mipmap.icon_eye_open)
            } else {
                et_confirm_capital_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                img_capital_eye2.setImageResource(R.mipmap.icon_eye_close)
            }
            et_confirm_capital_pwd.setSelection(et_confirm_capital_pwd.text.toString().trim().length)
        }
    }
}