package com.foxexchange.exchange.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.alibaba.android.arouter.launcher.ARouter

import com.example.exchange.R
import com.foxexchange.exchange.beans.HomeDataBean
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 * created by pzw on 2018/12/21.
 */
class ExchangeVerticalLinearlayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private var recylerviewUp : LQRRecyclerView? = null
    private var recylerviewDown : LQRRecyclerView? = null
    var adapterUp: LQRAdapterForRecyclerView<Any>?=null
    var adapterDown: LQRAdapterForRecyclerView<Any>?=null
    private var btnBuy : Button? = null
    private var btnSale : Button? = null
    private var contianer : LinearLayout ? = null
    private var exchange_btn_kline:Button?=null

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        ARouter.getInstance().inject(this)
        val exchangeVerticalLinearlayout = LayoutInflater.from(context).inflate(R.layout.ll_transaction_vertical_screen, this) as LinearLayout
        //初始化布局
        recylerviewUp = exchangeVerticalLinearlayout.findViewById(R.id.exchange_recylerview_updata)
        recylerviewDown = exchangeVerticalLinearlayout.findViewById(R.id.exchange_recylerview_downdata)
        btnBuy = exchangeVerticalLinearlayout.findViewById(R.id.exchange_btn_buy)
        btnSale = exchangeVerticalLinearlayout.findViewById(R.id.exchange_btn_saleout)
        contianer = exchangeVerticalLinearlayout.findViewById(R.id.exhange_contianer)
        exchange_btn_kline= exchangeVerticalLinearlayout.findViewById(R.id.exchange_btn_kline)
        contianer?.addView(BuyinLinearLayout(context))

        exchange_btn_kline?.setOnClickListener { ARouter.getInstance().build("/kline/activitys/KLineActivity").navigation(context) }



        //为两个button 设置监听事件
        btnSale?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                btnBuy?.setBackgroundResource(R.mipmap.icon_buy_black)
                btnSale?.setBackgroundResource(R.mipmap.icon_sale_red)
                contianer?.removeAllViews()
                contianer?.addView(SaleoutLinearLayout(context))
            }
        })
        //为两个button 设置监听事件
        btnBuy?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                btnBuy?.setBackgroundResource(R.mipmap.icon_buy_select)
                btnSale?.setBackgroundResource(R.mipmap.icon_sale)
                contianer?.removeAllViews()
                contianer?.addView(BuyinLinearLayout(context))
            }
        })
        adapterUp = object : LQRAdapterForRecyclerView<Any>(context, ArrayList<Any>(), R.layout.item_recylerview_updata) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
            }

        }
        adapterDown = object : LQRAdapterForRecyclerView<Any>(context, ArrayList<Any>(), R.layout.item_recylerview_updata) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
            }

        }
        recylerviewUp?.adapter = adapterUp?.headerAndFooterAdapter
        recylerviewDown?.adapter = adapterDown?.headerAndFooterAdapter
        for (item: Int in 1..6) {
            adapterUp?.addLastItem(HomeDataBean("1", "1", "1", "1"))
            adapterDown?.addLastItem(HomeDataBean("1", "1", "1", "1"))
        }
    }
}
