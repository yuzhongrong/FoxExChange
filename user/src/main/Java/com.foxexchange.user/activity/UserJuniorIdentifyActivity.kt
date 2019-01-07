package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import kotlinx.android.synthetic.main.activity_junior_identify.*

/**
 * 初级认证
 * @Ling
 */
class UserJuniorIdentifyActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView
    lateinit var identify_message_ly : LinearLayout

    override val layoutId: Int
        get() = R.layout.activity_junior_identify


    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        identify_message_ly = findViewById(R.id.identify_message_ly)
        tv_title.text = resources.getText(R.string.str_junior_identify)
//        input_message_ly.visibility = View.VISIBLE
        identify_message_ly.visibility = View.VISIBLE
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?){
        when(v){
            img_back -> finish()
        }
    }
}