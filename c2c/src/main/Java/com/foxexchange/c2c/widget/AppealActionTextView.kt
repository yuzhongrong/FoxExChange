package com.foxexchange.c2c.widget

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.widget.textview.EndTimerTextView
import com.foxexchange.c2c.R
import com.foxexchange.c2c.activitys.C2CBuyActivity
import com.foxexchange.c2c.beans.OrderInfoBean
import com.foxexchange.common.widgets.LoginBeanUtil
import com.orhanobut.logger.Logger
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber

/**自定义申诉按钮**/
class AppealActionTextView : EndTimerTextView {


    var mContext:Context?=null
    var mBean: OrderInfoBean?=null
    constructor(context: Context) : super(context) {
        mContext=context
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext=context
        initView()
    }


    private fun initView() {
        EventBus.getDefault().register(this)
        ellipsize = TextUtils.TruncateAt.valueOf("END")
        setBackgroundColor(mContext?.resources?.getColor(R.color.color_343b60)!!)

        isEnabled=false
        setSingleLine(true)
       gravity=Gravity.CENTER
        setOnClickListener { /**跳转到申诉页面**/

            /**待放币状态点击申诉|申诉状态取消申诉**/
            if(mBean?.state==1){
                Logger.d("----------发起申诉---->")
                ARouter.getInstance().build("/c2c/activitys/C2CAppealActivity").navigation(mContext as C2CBuyActivity,0x1000)
            }
            if(mBean?.state==2){
                //取消申诉-请求网络 更新UI
                var onErrorAction=OrderInfoBean(1,"0x11","0x12",0,0)
                EventBus.getDefault().post(onErrorAction,"ACTION_C2C_UPDATE_PAY_STATE")

            }


        }
    }



    @Subscriber(tag="ACTION_C2C_UPDATE_PAY_STATE")
    fun onUpdateState(bean: OrderInfoBean){

        mBean=bean
        //根据不同状态显示不同内容
        when(bean.state){
            1 -> {
                 //等待放币
                /**买家**/
                if(bean.uid=="0x11"&&bean.direction==0){
                    this.visibility= View.VISIBLE
                    text=mContext?.resources?.getString(R.string.str_c2c_appeal_time)
                    setVarTime(bean.countdown,object :EndCallBack{
                        override fun endTimer() {
                            setBackgroundColor(mContext?.resources?.getColor(R.color.color_0994fe)!!)
                            isEnabled=true
                            text=getCustomerText()
                        }
                    })

                }
                else{
                    this.visibility= View.GONE
                }

            }
            2 -> {//申诉中
                /**买家**/
                if(bean.uid=="0x11"&&bean.direction==0)
                    this.visibility= View.VISIBLE
                    this.text=mContext?.resources?.getString(R.string.str_c2c_trade_apply_cancel)
                 }
            0,3,4 -> { this.visibility= View.GONE}

        }
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }


    override fun getCustomerText(): String? {

        return mContext?.resources?.getString(R.string.str_c2c_appeal_time)
    }
}
