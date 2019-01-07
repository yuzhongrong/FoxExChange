package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.BasePayBean
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.c2c.popwindow.ChoosePaytypePop
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_buy.*
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber

class PayTypeLayout : RelativeLayout,ChoosePaytypePop.ItemClickListener{

    private var mContext: Context? = null
    var choosePaytypePop:ChoosePaytypePop?=null
    var c2c_paycontainer:SwitchTabLayout?=null

    constructor(context: Context) : super(context) {
        mContext = context
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        View.inflate(mContext, R.layout.view_c2c_pay_type, this)
        initView()

    }

    private fun initView() {

        //load layout
        EventBus.getDefault().register(this)
        findViewById<TextView>(R.id.c2c_paytype_select).setOnClickListener {
            choosePaytypePop= ChoosePaytypePop(mContext!!)
            choosePaytypePop?.setOnItemClickListener(this)
            choosePaytypePop?.showPopupWindow() }



    }



    fun setPayTypeContainer(c2c_paycontainer:SwitchTabLayout? ){

        this.c2c_paycontainer=c2c_paycontainer
        c2c_paycontainer?.switchTabLayout(R.layout.pay_wx_layout,null)
    }


    override fun onItemSelect(resid: Int) {
        when(resid){
            R.id.c2c_paytype0 -> {c2c_paycontainer?.switchTabLayout(R.layout.pay_bank_layout,null)}
            R.id.c2c_paytype1 -> { Logger.d("----select ali---->")}
            R.id.c2c_paytype2 -> {c2c_paycontainer?.switchTabLayout(R.layout.pay_wx_layout,null)}
            R.id.c2c_paytype3 -> { Logger.d("----select up---->")}

        }

    }



    @Subscriber(tag="ACTION_C2C_UPDATE_PAY_STATE")
    fun onUpdateState(bean: OrderInfoBean){

        //根据不同状态显示不同内容
        when(bean.state){
            0 -> {this.visibility= View.VISIBLE}//待付款
            1,2,3,4 -> {this.visibility= View.GONE}//等待放币,申诉中,交易取消,交易完成
        }


    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }




}
