package com.foxexchange.common.activitys

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.foxexchange.common.R

abstract class BaseFoxExChangeKTActivity : com.cjwsc.idcm.kotlin.base.BaseKTActivity(){


    override fun onInitView(bundle: Bundle?) {
        findViewById<ImageView>(R.id.img_back).setOnClickListener { finish()}
    }




}
