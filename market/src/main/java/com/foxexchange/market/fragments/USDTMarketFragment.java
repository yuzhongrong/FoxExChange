package com.foxexchange.market.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjwsc.idcm.base.BaseFragment;
import com.cjwsc.idcm.base.BaseView;
import com.cjwsc.idcm.kotlin.base.BaseKTFragment;
import com.foxexchange.market.beans.MarketUSDTBean;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import bitchat.android.com.market.R;

/**
 * created by pzw on 2018/12/11.
 */
public class USDTMarketFragment extends BaseFragment{
    private LQRRecyclerView mRecyclerView;
    private LQRAdapterForRecyclerView<MarketUSDTBean> adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market_usdt;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
    }

    @Override
    protected void onEvent() {
        mRecyclerView.setAdapter(adapter = new LQRAdapterForRecyclerView<MarketUSDTBean>(getActivity(),new ArrayList<MarketUSDTBean>(),R.layout.item_recyclerview_added) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, MarketUSDTBean item, int position) {

            }

        });
        for(int i=0;i<5;i++) {
            adapter.addLastItem(new MarketUSDTBean());
        }
    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }

}
