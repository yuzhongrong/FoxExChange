package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_recharge_coin_layout.*

/**
 * 充币
 */
@Route(path = "/user/activity/UserRechargeCoinActivity",extras = 0)
class UserRechargeCoinActivity : BaseFoxExChangeKTActivity(){

    var tv_title : TextView ?= null
    var img_back : ImageView ?= null
    var img_right : ImageView ?= null
    var coins_type_select_ly : RelativeLayout ?= null

    var isSelect : Boolean = false

    override val layoutId: Int
        get() = R.layout.activity_recharge_coin_layout

    override fun onInitView(bundle: Bundle?) {
        tv_title = findViewById(R.id.tv_title)
        img_back = findViewById(R.id.img_back)
        img_right = findViewById(R.id.img_right)
        tv_title?.text = resources.getText(R.string.str_personal_recharge_coin)
        img_right?.visibility = View.VISIBLE
        img_right?.setImageResource(R.mipmap.icon_my_orders)
        coins_type_select_ly = findViewById(R.id.coins_type_select_ly)
    }

    override fun onEvent() {
        img_back?.setOnClickListener{
            finish()
        }
        coins_type_select_ly?.setOnClickListener{
            //选择币种
            isSelect = !isSelect
            if (isSelect){
                img_select_arrow.setImageResource(R.mipmap.icon_arrow_gray_up)
            } else {
                img_select_arrow.setImageResource(R.mipmap.icon_arrow_gray_down)
            }
        }
    }

}