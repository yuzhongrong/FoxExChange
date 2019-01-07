package com.foxexchange.user.fragments

import android.os.Bundle
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView

/**
 *返佣记录fragment
 */
class UserReturnCommissionRecordFragment : BaseKTFragment() {

    var list : ArrayList<Any> = arrayListOf("1", "2", "3", "2", "3", "2", "3", "2", "3", "2", "3")

    private var lqr_commission_record : LQRRecyclerView?= null

    override val layoutId: Int
        get() = R.layout.fragment_asset_list_layout

    override fun onInitView(bundle: Bundle?) {
        lqr_commission_record = rootView?.findViewById(R.id.asset_list)
        lqr_commission_record?.adapter = object :
                LQRAdapterForRecyclerView<Any>(mContext,list, R.layout.item_commission_record) {
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {

            }

        }
    }

    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }
}