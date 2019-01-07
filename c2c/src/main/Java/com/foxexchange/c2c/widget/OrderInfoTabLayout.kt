package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.BasePayBean
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.common.widgets.LoginBeanUtil
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber

class OrderInfoTabLayout : RelativeLayout {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        initView()

    }

    private fun initView() {
        EventBus.getDefault().register(this)
        View.inflate(mContext, R.layout.view_c2c_buy_tradeinfo,this)
    }


    fun switchOrderInfoTabLayout(resid: Int, payBean: BasePayBean?) {
        val view = View.inflate(mContext, resid, null)
        this.removeAllViews()
        this.addView(view)
    }


    @Subscriber(tag="ACTION_C2C_UPDATE_PAY_STATE")
    fun onUpdateState(bean: OrderInfoBean) {

        //根据不同状态显示不同内容
        when (bean.state) {
            0 -> {

                //待付款
                /**买家**/
                if (bean.uid == "0x11" && bean.direction == 0) {
                    switchOrderInfoTabLayout(R.layout.view_c2c_buy_tradeinfo, null)
                }

            }
            1 -> {//等待放币
                /**买家**/
                if (bean.uid == "0x11" && bean.direction == 0) {
                    switchOrderInfoTabLayout(R.layout.view_c2c_common_tradeinfo, null)
                } else {


                }
            }
            2 -> {
            }//申诉中
            3 -> {
            }//交易取消
            4 -> {
            }//交易完成

        }
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }


}
