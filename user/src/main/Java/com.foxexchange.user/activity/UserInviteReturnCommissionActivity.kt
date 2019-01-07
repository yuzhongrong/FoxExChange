package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_invite_return_commission.*

/**
 * 邀请返佣
 */
@Route(path = "/user/activity/UserInviteReturnCommissionActivity",extras = 0)
class UserInviteReturnCommissionActivity : BaseFoxExChangeKTActivity() {



    override val layoutId: Int
        get() = R.layout.activity_invite_return_commission

    override fun onInitView(bundle: Bundle?) {

    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
        tv_return_detail.setOnClickListener(this::onClick)
        btn_invite_copy_code.setOnClickListener(this::onClick)
        btn_invite_copy_link.setOnClickListener(this::onClick)
        btn_invite_select_poster.setOnClickListener(this::onClick)
        tv_invite_rule.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v){
            img_back -> finish()

            tv_return_detail -> {  //返佣详情
                ARouter.getInstance().build("/user/activity/UserReturnCommissionDetailActivity")
                        .navigation()
            }

            btn_invite_copy_code -> {  //复制邀请码

            }

            btn_invite_copy_link -> {  //复制邀请链接

            }

            btn_invite_select_poster -> {  //选择海报

            }

            tv_invite_rule -> {  //返佣规则
                ARouter.getInstance().build("/user/activity/UserReturnCommissionRulesActivity")
                        .navigation()
            }
        }
    }
}