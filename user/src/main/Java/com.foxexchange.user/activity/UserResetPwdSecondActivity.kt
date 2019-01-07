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
import kotlinx.android.synthetic.main.activity_reset_pwd_second.*

/**
 * 重置第二步
 * @Ling
 */
@Route(path = "/user/activity/UserResetPwdSecondActivity")
class UserResetPwdSecondActivity : BaseFoxExChangeKTActivity() {

    var eye1 : Boolean = false
    var eye2 : Boolean = false

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_reset_pwd_second

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_reset_pwd)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)

        img_eye1.setOnClickListener(this::onClick)
        img_eye2.setOnClickListener(this::onClick)

        btn_reset.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()

            btn_reset -> {  //重置

            }

            img_eye1 -> {
                eye1 = !eye1
                if (eye1){
                    img_eye1.setImageResource(R.mipmap.icon_eye_open)
                    et_reset_pwd1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    img_eye1.setImageResource(R.mipmap.icon_eye_close)
                    et_reset_pwd1.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                et_reset_pwd1.setSelection(et_reset_pwd1.text.toString().length)
            }

            img_eye2 -> {
                eye2 = !eye2
                if (eye2){
                    img_eye2.setImageResource(R.mipmap.icon_eye_open)
                    et_reset_pwd2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    img_eye2.setImageResource(R.mipmap.icon_eye_close)
                    et_reset_pwd2.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                et_reset_pwd2.setSelection(et_reset_pwd2.text.toString().length)
            }
        }
    }
}