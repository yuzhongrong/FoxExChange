package com.foxexchange.user.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.example.user.R
import com.lqr.adapter.LQRAdapterForRecyclerView
import com.lqr.adapter.LQRViewHolderForRecyclerView
import com.lqr.recyclerview.LQRRecyclerView
import kotlinx.android.synthetic.main.fragment_asset_list_layout.*

/**
 * 资产列表fragment
 */
class UserAssetListKTFragment  : BaseKTFragment() {

    var adapter : LQRAdapterForRecyclerView<*> ?= null

    var list : ArrayList<Any> = arrayListOf("1", "2", "3")

    var foot: View ?= null

    var asset_list : LQRRecyclerView ?= null

    override val layoutId: Int
        get() = R.layout.fragment_asset_list_layout

    override fun onInitView(bundle: Bundle?) {
        asset_list = rootView?.findViewById(R.id.asset_list)
        adapter = object:LQRAdapterForRecyclerView<Any>(activity, list, R.layout.item_my_asset_list){
            override fun convert(helper: LQRViewHolderForRecyclerView?, item: Any?, position: Int) {

            }
        }
        foot = LayoutInflater.from(context).inflate(R.layout.item_my_asset_foot, null)
        adapter?.headerAndFooterAdapter?.addFooterView(foot)
        asset_list?.adapter = adapter?.headerAndFooterAdapter
    }


    override fun onEvent() {
    }

    override fun lazyFetchData() {
    }

}