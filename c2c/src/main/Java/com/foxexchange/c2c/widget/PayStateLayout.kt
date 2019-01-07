package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.BasePayBean
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.common.beans.ExLoginBean
import com.foxexchange.common.widgets.LoginBeanUtil
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber

class PayStateLayout : RelativeLayout {

    private var mContext: Context? = null
    var c2c_pay_state:TextView?=null
    var c2c_pay_subtitle:TextView?=null
    

    constructor(context: Context) : super(context) {
        mContext = context
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        View.inflate(mContext, R.layout.pay_state_layout, this)
        initView()

    }

    private fun initView() {

        EventBus.getDefault().register(this)
        //load layout
        c2c_pay_state=findViewById(R.id.c2c_pay_state)
        c2c_pay_subtitle=findViewById(R.id.c2c_pay_subtitle)


    }

    @Subscriber(tag="ACTION_C2C_UPDATE_PAY_STATE")
    fun onUpdateState(bean: OrderInfoBean){

        //根据不同状态显示不同内容
        when(bean.state){
            0 -> {//待付款

                /**买单**/
                if(bean.uid=="0x11"&&bean.direction==0){
                    c2c_pay_state?.text=mContext?.getText(R.string.str_c2c_please_pay)
                    c2c_pay_subtitle?.text=mContext?.getString(R.string.str_c2c_action_order_success)

                }else{ /**卖单**/
                    c2c_pay_state?.text=mContext?.getString(R.string.str_c2c_wait_pay)
                    c2c_pay_subtitle?.text=mContext?.getString(R.string.str_c2c_ordersuccess_wait_pay)

                }
               }
            1 -> {//等待放币

                /**买单**/
                if(bean.uid=="0x11"&&bean.direction==0){
                    c2c_pay_state?.text=mContext?.getString(R.string.str_c2c_wait_release)

                    c2c_pay_subtitle?.text=mContext?.getString(R.string.str_ordersuccess_wait_release)


                }else{ /**卖单**/

                    c2c_pay_state?.text=mContext?.getString(R.string.str_c2c_please_release)
                    //买家已付款，请在规定时间内及时放行
                    c2c_pay_subtitle?.text=mContext?.getString(R.string.str_c2c_payed_release_wait_pease)
                   

                }

               }
            2 -> {//申诉中 买卖家都显示 申诉

                c2c_pay_state?.text=mContext?.getString(R.string.str_c2c_trade_apply)
                /**买单**/
                if(bean.uid=="0x11"&&bean.direction==0){
                    c2c_pay_subtitle?.text=mContext?.getString(R.string.str_c2c_apply_appeal)

                }else{ /**卖单**/

                    c2c_pay_subtitle?.text=mContext?.getString(R.string.str_c2c_appealed)

                }



            }
            3 -> {//交易取消 买卖家都显示 已取消
                c2c_pay_state?.text=mContext?.getString(R.string.str_c2c_trade_cancel)
                c2c_pay_subtitle?.text=mContext?.getString(R.string.str_c2c_trade_canced)

            }
            4 -> {//交易完成 买卖家都显示 已完成
                c2c_pay_state?.text=mContext?.getString(R.string.str_c2c_trade_finish)
                c2c_pay_subtitle?.text=mContext?.getString(R.string.str_trade_finish)

            }

        }


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }


}
