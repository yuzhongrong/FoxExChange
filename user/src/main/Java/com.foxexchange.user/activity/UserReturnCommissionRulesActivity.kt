package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 返佣规则
 */
@Route(path = "/user/activity/UserReturnCommissionRulesActivity")
class UserReturnCommissionRulesActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = R.layout.activity_return_commission_rules

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_return_commission_rules)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()
        }
    }
}