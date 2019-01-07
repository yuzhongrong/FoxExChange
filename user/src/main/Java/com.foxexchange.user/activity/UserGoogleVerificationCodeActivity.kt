package com.foxexchange.user.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.jyn.vcview.VerificationCodeView
import kotlinx.android.synthetic.main.activity_google_verification_code.*
import android.databinding.adapters.TextViewBindingAdapter.setText
import com.cjwsc.idcm.Utils.LogUtil


/**
 * 谷歌验证码
 */
@Route(path = "/user/activity/UserGoogleVerificationCodeActivity")
class UserGoogleVerificationCodeActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    lateinit var view_verify_code : VerificationCodeView

    override val layoutId: Int
        get() = R.layout.activity_google_verification_code

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_google_verification_code)

        view_verify_code = findViewById(R.id.view_verify_code)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)

        view_verify_code.setOnCodeFinishListener {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()
        }
    }

}