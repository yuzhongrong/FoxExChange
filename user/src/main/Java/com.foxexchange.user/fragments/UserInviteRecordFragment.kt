package com.foxexchange.user.fragments

import android.os.Bundle
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 * 邀请记录fragment
 */
class UserInviteRecordFragment : BaseKTFragment() {

    var list : ArrayList<Any> = arrayListOf("1", "2", "3", "2", "3", "2", "3", "2", "3", "2", "3")

    var lqr_invite_record : LQRRecyclerView ?= null

    override val layoutId: Int
        get() = R.layout.fragment_invite_record

    override fun onInitView(bundle: Bundle?) {
        lqr_invite_record = rootView?.findViewById(R.id.lqr_invite_record)
        lqr_invite_record?.adapter = object :
                LQRAdapterForRecyclerView<Any>(mContext,list,R.layout.item_invite_record) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {

            }

        }
    }

    override fun onEvent() {

    }

    override fun lazyFetchData() {
    }
}