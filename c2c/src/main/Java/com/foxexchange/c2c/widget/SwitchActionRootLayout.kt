package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout

import com.cjwsc.idcm.model.bean.WxPayBean
import com.foxexchange.c2c.beans.BasePayBean
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.common.widgets.LoginBeanUtil
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber

class SwitchActionRootLayout : RelativeLayout {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        initView()

    }


    fun initView(){

        EventBus.getDefault().register(this)


    }

    @Subscriber(tag="ACTION_C2C_UPDATE_PAY_STATE")
    fun onUpdateState(bean: OrderInfoBean){

        //根据不同状态显示不同内容
        when(bean.state){
            0 -> {//待付款

                /**买家待付款状态才显示这个布局**/
                if(bean.uid=="0x11"&&bean.direction==0){
                    this.visibility=visibility
                }

            }
            1 -> {this.visibility= View.GONE}//等待放币
            2 -> {this.visibility= View.GONE}//申诉中
            3 -> {this.visibility= View.GONE}//交易取消
            4 -> {this.visibility= View.GONE}//交易完成

        }


    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }




}
