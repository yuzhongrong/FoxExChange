package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.Utils.ToastUtil
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 注册
 * @Ling
 */
@Route(path = "/user/activity/UserRegisterActivity")
class UserRegisterActivity : BaseFoxExChangeKTActivity(){

    var way : Boolean = true  //注册方式 true: 手机注册,false: 邮箱注册
    var accountNum : String ?= null
    var identifyCode : String ?= null

    override val layoutId: Int
        get() = R.layout.activity_register


    override fun onInitView(bundle: Bundle?) {
        tv_title.text = getString(R.string.str_register)
    }

    override fun onEvent() {
        tv_cancel.setOnClickListener(this::onClick)
        tv_change_way.setOnClickListener(this::onClick)

        //手机注册
        tv_register_get_phone_code.setOnClickListener(this::onClick)
        btn_register_phone.setOnClickListener(this::onClick)
        //邮箱注册
        tv_register_get_email_code.setOnClickListener(this::onClick)
        btn_register_email.setOnClickListener(this::onClick)

        to_login_ly.setOnClickListener(this::onClick)
    }

    fun onClick(v : View? ){
        when(v){
            tv_cancel -> finish()

            tv_register_get_phone_code ->{  //获取短信验证码

            }

            tv_register_get_email_code ->{  //获取邮箱验证码

            }

            tv_change_way ->{  //切换注册方式，变更注册输入
                way = !way
                if (way){
                    tv_phone_or_email.text = resources.getText(R.string.str_register_by_phone)
                    tv_change_way.text = resources.getText(R.string.str_register_by_email)
                    phone_register_ly.visibility = View.VISIBLE
                    email_register_ly.visibility = View.GONE
                } else {
                    tv_change_way.text = resources.getText(R.string.str_register_by_phone)
                    tv_phone_or_email.text = resources.getText(R.string.str_register_by_email)
                    phone_register_ly.visibility = View.GONE
                    email_register_ly.visibility = View.VISIBLE
                }
            }

            btn_register_phone ->{  //手机注册
                jumpToNext()
//                when {
//                    et_register_phone.text.toString().trim() == null -> {
//                        ToastUtil.showErrorToast("手机号码不能为空")
//                    }
//                    et_register_phone_code.text.toString().trim() == null -> {
//                        ToastUtil.showErrorToast("请输入验证码")
//                    }
//                    else -> {
//                        ToastUtil.showErrorToast("注册成功")
//                    }
//                }
            }

            btn_register_email ->{  //邮箱注册
                jumpToNext()
//                when{
//                    et_register_email.text.toString().trim() == null -> {
//                        ToastUtil.showErrorToast("邮箱地址不能为空")
//                    }
//                    et_register_email_code.text.toString().trim() == null -> {
//                        ToastUtil.showErrorToast("请输入验证码")
//                    }
//                    else -> {
//                        ToastUtil.showErrorToast("注册成功")
//                    }
//                }
            }

            to_login_ly -> {
                ARouter.getInstance().build("/user/activity/UserLoginActivity")
                        .navigation()
            }
        }
    }


    fun jumpToNext(){
        ARouter.getInstance().build("/user/activity/UserRegisterSecondActivity")
                .navigation()
    }
}