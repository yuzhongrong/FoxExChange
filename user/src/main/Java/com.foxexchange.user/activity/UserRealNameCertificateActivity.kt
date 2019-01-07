package com.foxexchange.user.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_real_name_certificate_layout.*

/**
 * 实名认证
 * @Ling
 */
@Route(path = "/user/activity/UserRealNameCertificateActivity", extras = 0)
class UserRealNameCertificateActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView
//    lateinit var tv_junior_identify : TextView
////    lateinit var tv_medium_identify : TextView
////    lateinit var junior_identify_ly : RelativeLayout
////    lateinit var medium_identify_ly : RelativeLayout

    override val layoutId: Int
        get() = R.layout.activity_real_name_certificate_layout

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getText(R.string.str_certification)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
        junior_identify_ly.setOnClickListener(this::onClick)
        medium_identify_ly.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()

            junior_identify_ly ->{
                var juniorIntent = Intent(this, UserJuniorIdentifyActivity().javaClass)
                startActivity(juniorIntent)
//                Toast.makeText(this, "初级认证通过", Toast.LENGTH_SHORT).show()
//                junior_identify_ly.setBackgroundResource(R.mipmap.bg_identify_junior)
//                tv_junior_identify.text = "已认证"
            }

            medium_identify_ly ->{
                startActivity(Intent(this, UserMediumIdentifyActivity().javaClass))
//                Toast.makeText(this, "中级认证通过", Toast.LENGTH_SHORT).show()
//                medium_identify_ly.setBackgroundResource(R.mipmap.bg_identify_medium)
//                tv_medium_identify.text = "已认证"
            }
        }
    }
}