package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_security_center.*

/**
 * 安全中心
 * @Ling
 */
@Route(path = "/user/activity/UserSecurityCenterActivity", extras = 0)
class UserSecurityCenterActivity : BaseFoxExChangeKTActivity() {


    override val layoutId: Int
        get() = R.layout.activity_security_center

    override fun onInitView(bundle: Bundle?) {
        tv_title.text = getString(R.string.str_security_center)
    }

    override fun onEvent() {

        img_back.setOnClickListener{finish()}

        //操作记录
        tv_right.setOnClickListener{
            ARouter.getInstance().build("/user/activity/OperationRecordActivity")
                    .navigation()
        }
        //绑定手机
        bind_phone_ly.setOnClickListener{
            ARouter.getInstance().build("/user/activity/UserBindPhoneActivity")
                    .navigation()
        }
        //绑定邮箱
        bind_email_ly.setOnClickListener{
            ARouter.getInstance().build("/user/activity/UserBindEMailActivity")
                    .navigation()
        }
        //谷歌验证
        google_check_ly.setOnClickListener{
            ARouter.getInstance().build("/user/activity/UserBindGoogleValidatorActivity")
                    .navigation()
        }
        //登录密码
        login_pwd_ly.setOnClickListener{
            ARouter.getInstance().build("/user/activity/ModifyLoginPwdActivity")
                    .navigation()
        }
        //资金密码
        asset_pwd_ly.setOnClickListener{
            ARouter.getInstance().build("/user/activity/ModifyCapitalPwdActivity")
                    .navigation()
        }
    }

}