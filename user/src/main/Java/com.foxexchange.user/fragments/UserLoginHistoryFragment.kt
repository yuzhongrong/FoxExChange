package com.foxexchange.user.fragments

import android.os.Bundle
import android.widget.TextView
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import kotlinx.android.synthetic.main.fragment_operation_list.*

/**
 * 登录历史fragment
 */
class UserLoginHistoryFragment : BaseKTFragment() {

    var tv_way : TextView ?= null
    
    var lqr_login_record : LQRRecyclerView ?= null

    var list : ArrayList<Any> = arrayListOf("1", "2", "3", "4", "5", "6")

    override val layoutId: Int
        get() = R.layout.fragment_operation_list

    override fun onInitView(bundle: Bundle?) {
        tv_way = rootView?.findViewById(R.id.tv_way)
        tv_way?.text = resources.getText(R.string.str_the_way_of_login)
        lqr_login_record = rootView?.findViewById(R.id.lqr_operation_record)

        lqr_login_record?.adapter = object :
                LQRAdapterForRecyclerView<Any>(mContext, list, R.layout.item_invite_record) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {

            }

        }
        
    }

    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }
}