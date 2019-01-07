package com.foxexchange.c2c.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.foxexchange.c2c.R
import com.foxexchange.c2c.beans.BasePayBean
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.common.beans.ExLoginBean
import com.foxexchange.common.widgets.LoginBeanUtil
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber

class BottomTipLayout : LinearLayout {

    private var mContext: Context? = null
    var tip_content:TextView?=null


    constructor(context: Context) : super(context) {
        mContext = context

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context

        initView()

    }

    private fun initView() {
        EventBus.getDefault().register(this)
        tip_content= getChildAt(1) as TextView?

    }

    @Subscriber(tag="ACTION_C2C_UPDATE_PAY_STATE")
    fun onUpdateState(bean: OrderInfoBean){

        //根据不同状态显示不同内容
        when(bean.state){
            0 -> {//待付款

                /**买单**/
                if(bean.uid.equals(LoginBeanUtil.getLoginBean().account)&&bean.direction==0){
                    this.visibility=visibility
                    tip_content?.text=mContext?.getString(R.string.str_c2c_buy_warinig_tip_value)

                }else{ /**卖单**/
                    this.visibility=visibility
                    tip_content?.text=mContext?.getString(R.string.str_c2c_sell_warning_tip)


                }
               }
            1 -> {//等待放币

                /**买单**/
                if(bean.uid=="0x11"&&bean.direction==0){

                    this.visibility= View.GONE

                }else{ /**卖单**/

                    this.visibility=visibility
                    tip_content?.text=mContext?.getString(R.string.str_c2c_sell_warning_tip)

                }

               }
            2 -> {//申诉中 买卖家都显示 申诉


                /**买单**/
                if(bean.uid.equals(LoginBeanUtil.getLoginBean().account)&&bean.direction==0){




                }else{ /**卖单**/



                }



            }
            3 -> {//交易取消 买卖家都显示 已取消
                this.visibility= View.GONE
            }
            4 -> {//交易完成 买卖家都显示 已完成
                this.visibility= View.GONE
            }

        }


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }


}
