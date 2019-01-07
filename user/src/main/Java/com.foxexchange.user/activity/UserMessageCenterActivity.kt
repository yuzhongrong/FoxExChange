package com.foxexchange.user.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_security_center.*
import q.rorbin.badgeview.QBadgeView

/**
 * created by pzw on 2018/12/29.
 */

@Route(path = "/user/activity/UserMessageCenterActivity", extras = 0)
class UserMessageCenterActivity : BaseFoxExChangeKTActivity() {
    lateinit var img_back : ImageView
    lateinit var rlNotification : RelativeLayout
    lateinit var ivNotification : ImageView

    override val layoutId: Int
        get() = R.layout.activity_message_center

    override fun onInitView(bundle: Bundle?) {
        tv_title.text = "消息中心"
        img_back = findViewById(R.id.img_back)
        ivNotification = findViewById(R.id.user_iv_notification)
        rlNotification = findViewById(R.id.user_rl_notification)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
        rlNotification.setOnClickListener(this::onClick)
        QBadgeView(this).bindTarget(ivNotification).
                setBadgeTextSize(11f,true).
                setBadgeBackground(resources.getDrawable(R.drawable.bg_badgeview)).setGravityOffset(1f,1f,false).setBadgeText("1")
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()
            rlNotification -> ARouter.getInstance().build("/user/activity/UserNotificationActivity")
                    .withString("", "")
                    .navigation(this)
        }
    }
}
