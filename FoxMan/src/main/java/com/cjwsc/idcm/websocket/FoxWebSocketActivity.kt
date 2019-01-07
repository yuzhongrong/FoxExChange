package com.cjwsc.idcm.websocket

import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Autowired

import com.cjwsc.idcm.kotlin.base.BaseKTActivity

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

abstract class FoxWebSocketActivity : BaseKTActivity() {

    private var client: OkHttpClient? = null
    private var sendTime = 0L
    // 发送心跳包
    private var mHandler: Handler? = Handler()

    private var mSocket: WebSocket? = null
    private var request: Request? = null
    private var listener: FoxWebSocketListener? = null




    protected abstract fun sendData():String

    protected abstract fun getWebsocketUrl():String

    companion object {
        // 每隔2秒发送一次心跳包，检测连接没有断开
        private val HEART_BEAT_RATE = (2 * 1000).toLong()
    }


    // 发送心跳包
    private val heartBeatRunnable = object : Runnable {
        override fun run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                val message = sendData()
                mSocket!!.send(message)
                sendTime = System.currentTimeMillis()
            }
            if (mHandler != null) {
                mHandler!!.postDelayed(this, HEART_BEAT_RATE) //每隔一定的时间，对长连接进行一次心跳检测
            }
        }
    }


    override fun onInitView(bundle: Bundle?) {

        listener = FoxWebSocketListener(FoxWebSocketListener.disConnectListener {
            if (mHandler != null) {
                mSocket = client!!.newWebSocket(request!!, listener)
            }
        })
        //        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        request = Request.Builder().url(getWebsocketUrl()).build()
        client = OkHttpClient()
        mSocket = client!!.newWebSocket(request!!, listener)
        mHandler!!.postDelayed(heartBeatRunnable, HEART_BEAT_RATE)

    }

    override fun onEvent() {

    }


    override fun onDestroy() {
        super.onDestroy()
        mHandler!!.removeCallbacksAndMessages(null)
        mHandler = null
        if(mSocket!=null){
            mSocket!!.cancel()
            mSocket!!.close(1000, null)
        }

    }

}
