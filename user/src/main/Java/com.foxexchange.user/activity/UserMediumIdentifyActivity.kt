package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity

/**
 * 中级认证
 * @Ling
 */
class UserMediumIdentifyActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    override val layoutId: Int
        get() = selectLayout()

    var select : Boolean = true
    private fun selectLayout(): Int {
        return if(select){
            R.layout.activity_medium_identify
        } else{
            R.layout.activity_junior_identify
        }
    }

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_medium_identify)
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