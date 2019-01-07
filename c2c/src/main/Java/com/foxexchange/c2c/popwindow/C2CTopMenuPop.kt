package com.foxexchange.c2c.popwindow

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter

import com.cjwsc.idcm.base.application.BaseApplication
import com.foxexchange.c2c.R
import com.foxexchange.c2c.R.id.c2c_paytype0
import com.foxexchange.c2c.R.id.c2c_paytype1

import razerdp.basepopup.BasePopupWindow

class C2CTopMenuPop(context: Context) : BasePopupWindow(context),View.OnClickListener{


    lateinit var animview: LinearLayout

    lateinit var cancel: TextView

    lateinit var item1: TextView

    lateinit var item2: TextView

    lateinit var item3: TextView

    lateinit var item4: TextView


    override fun initShowAnimation(): Animation {
        return AnimationUtils.loadAnimation(BaseApplication.getContext(), com.cjwsc.idcm.R.anim.slide_in_bottom)
    }

    override fun initExitAnimation(): Animation {
        return AnimationUtils.loadAnimation(BaseApplication.getContext(), com.cjwsc.idcm.R.anim.slide_out_bottom)
    }

    override fun getClickToDismissView(): View? {
        return null
    }

    override fun onCreatePopupView(): View {

        return initView(createPopupById(R.layout.c2c_pop_top_layout))

    }

    override fun initAnimaView(): View? {
        return null
    }

    private fun initView(view: View):View {
        animview=view.findViewById(R.id.pop_root)
        item1=view.findViewById(R.id.c2c_top_menu_item1)
        item2=view.findViewById(R.id.c2c_top_menu_item2)
        item3=view.findViewById(R.id.c2c_top_menu_item3)
        item4=view.findViewById(R.id.c2c_top_menu_item4)

        item1.setOnClickListener(this)
        item2.setOnClickListener(this)
        item3.setOnClickListener(this)
        item4.setOnClickListener(this)

        return  view
    }



    override fun onClick(v: View?) {
        dismiss()
        when(v?.id){
            R.id.c2c_top_menu_item1 -> { ARouter.getInstance().build("/user/activity/UserTransferAssetActivity").navigation(context) }//进入添加支付方式}
            R.id.c2c_top_menu_item2 -> {   ARouter.getInstance().build("/c2c/activitys/C2COrderCenterActivity").navigation(context) }
            R.id.c2c_top_menu_item3 -> { ARouter.getInstance().build("/c2c/activitys/C2CPayTypeListActivity").navigation(context) }//进入添加支付方式
            R.id.c2c_top_menu_item4 -> { ARouter.getInstance().build("/user/activity/UserModifyCapitalPwdActivity").navigation(context) }

        }
    }





}
