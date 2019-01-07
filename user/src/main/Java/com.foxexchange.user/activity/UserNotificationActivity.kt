package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R

import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_security_center.*
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.widget.TextView
import com.ms.square.android.expandabletextview.ExpandableTextView




/**
 * created by pzw on 2018/12/29.
 */

@Route(path = "/user/activity/UserNotificationActivity", extras = 0)
class UserNotificationActivity : BaseFoxExChangeKTActivity() {
    lateinit var img_back : ImageView

    override val layoutId: Int
        get() = R.layout.activity_notification

    override fun onInitView(bundle: Bundle?) {
        tv_title.text = "系统通知"
        img_back = findViewById(R.id.img_back)

        // sample code snippet to set the text content on the ExpandableTextView
        val expTv1 =
                findViewById(R.id.expand_text_view) as ExpandableTextView
        val expTv2 =
                findViewById(R.id.expand_text_view1) as ExpandableTextView
        val expTv3 =
                findViewById(R.id.expand_text_view2) as ExpandableTextView

// IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        expTv1.text = getString(R.string.str_about_us_setting_detail)
        expTv2.text = getString(R.string.str_through_the_way_of_inviting)
        expTv3.text = getString(R.string.str_c2c_sell_warning)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)

//            //先设置原始文本
//            tvNotification.setText("帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功。")
//            //使用post方法，在TextView完成绘制流程后在消息队列中被调用
//            tvNotification.post(Runnable {
//            //获取第一行的宽度
//            val lineWidth = tvNotification.getLayout().getLineWidth(0)
//            //获取第一行最后一个字符的下标
//            val lineEnd = tvNotification.getLayout().getLineEnd(0)
//            //计算每个字符占的宽度
//            val widthPerChar = lineWidth / (lineEnd + 1)
//            //计算TextView一行能够放下多少个字符
//            val numberPerLine = Math.floor((tvNotification.getWidth() / widthPerChar).toDouble()).toInt()
//            //在原始字符串中插入一个空格，插入的位置为numberPerLine - 1
//            val stringBuilder = StringBuilder("帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功帐号注册成功。").insert(numberPerLine - 1, "  ")
//
//            //SpannableString的构建
//            val spannableString = SpannableString(stringBuilder.toString() + " ")
//            val drawable = resources.getDrawable(R.mipmap.icon_arrow_gray_up)
//            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
//            val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)
//            spannableString.setSpan(imageSpan, spannableString.length - 1, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
//            tvNotification.setText(spannableString)
//        })


    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()
        }
    }
}
