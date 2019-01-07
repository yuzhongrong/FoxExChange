package com.foxexchange.c2c.popwindow

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView

import com.cjwsc.idcm.base.application.BaseApplication
import com.foxexchange.c2c.R
import com.foxexchange.c2c.R.id.c2c_paytype0
import com.foxexchange.c2c.R.id.c2c_paytype1

import razerdp.basepopup.BasePopupWindow

class ChoosePaytypePop(context: Context) : BasePopupWindow(context),View.OnClickListener{


    lateinit var animview: LinearLayout

    lateinit var cancel: TextView

    lateinit var c2c_paytype0: TextView

    lateinit var c2c_paytype1: TextView

    lateinit var c2c_paytype2: TextView

    lateinit var c2c_paytype3: TextView


    override fun initShowAnimation(): Animation {
        return AnimationUtils.loadAnimation(BaseApplication.getContext(), com.cjwsc.idcm.R.anim.slide_in_bottom)
    }

    override fun initExitAnimation(): Animation {
        return AnimationUtils.loadAnimation(BaseApplication.getContext(), com.cjwsc.idcm.R.anim.slide_out_bottom)
    }

    override fun getClickToDismissView(): View? {
        return cancel
    }

    override fun onCreatePopupView(): View {

        return initView(createPopupById(R.layout.pop_paytype_layout))

    }

    override fun initAnimaView(): View? {
        return animview
    }

    private fun initView(view: View):View {
        animview=view.findViewById(R.id.pop_paytype_root)
        cancel=view.findViewById(R.id.c2c_paytype_cancel)
        c2c_paytype0=view.findViewById(R.id.c2c_paytype0)
        c2c_paytype1=view.findViewById(R.id.c2c_paytype1)
        c2c_paytype2=view.findViewById(R.id.c2c_paytype2)
        c2c_paytype3=view.findViewById(R.id.c2c_paytype3)

        c2c_paytype0.setOnClickListener(this)
        c2c_paytype1.setOnClickListener(this)
        c2c_paytype2.setOnClickListener(this)
        c2c_paytype3.setOnClickListener(this)

        return  view
    }



    override fun onClick(v: View?) {

        mItemClickListener?.onItemSelect(v!!.id)
        dismiss()
    }

    var mItemClickListener:ItemClickListener?=null

    interface ItemClickListener{
        fun onItemSelect(resid:Int)
    }


    fun setOnItemClickListener(mItemClickListener:ItemClickListener){
        this.mItemClickListener=mItemClickListener
    }


}
