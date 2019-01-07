package com.foxexchange.user.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.cjwsc.idcm.Utils.ToastUtil
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import com.yanzhenjie.recyclerview.swipe.SwipeMenuLayout
import kotlinx.android.synthetic.main.activity_withdraw_coins_address.*
import kotlinx.android.synthetic.main.item_coins_address.view.*
import java.util.*

/**
 * 提币地址
 */
@Route(path = "/user/activity/UserWithDrawCoinsAddressActivity")
class UserWithDrawCoinsAddressActivity : BaseFoxExChangeKTActivity() {

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView
    
    lateinit var lqr_coins_address_list : LQRRecyclerView
    
    private val list : ArrayList<Any> = arrayListOf("1", "2", "3","4", "5", "6","7", "8", "9")

    private var adapter :  LQRAdapterForRecyclerView<Any> ?= null

    lateinit var foot : View

    override val layoutId: Int
        get() = R.layout.activity_withdraw_coins_address

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_with_draw_address)

        lqr_coins_address_list = findViewById(R.id.lqr_coins_address_list)
        
        adapter = object : LQRAdapterForRecyclerView<Any>
        (this, list, R.layout.item_coins_address) {
            override fun convert(holder: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {
                holder?.getView<Button>(R.id.btn_delete_address)?.setOnClickListener{
                    ToastUtil.show("删除第 $position 项")
                    //请求服务器删除
                    adapter?.removeItem(item)
                }

                holder?.getView<Button>(R.id.btn_address_detail)?.setOnClickListener{
                    ARouter.getInstance().build("/user/activity/UserWithDrawCoinsAddressDetailActivity")
                            .navigation()
                }
            }

        }

        foot = LayoutInflater.from(this).inflate(R.layout.item_button_of_address, null)
        adapter?.headerAndFooterAdapter?.addFooterView(foot)
        lqr_coins_address_list?.adapter = adapter?.headerAndFooterAdapter
    }

    override fun onEvent() {
        img_back.setOnClickListener{finish()}
        
        foot?.findViewById<Button>(R.id.btn_add_address).setOnClickListener{
            ARouter.getInstance().build("/user/activity/UserAddAddressActivity")
                    .navigation()
        }
    }

}