package com.foxexchange.market.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.cjwsc.idcm.base.BaseFragment;
import com.cjwsc.idcm.base.BaseView;
import com.foxexchange.market.activity.AddOptionalActivity;
import com.foxexchange.market.beans.MarketUSDTBean;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;

import bitchat.android.com.market.R;

/**
 * created by pzw on 2018/12/11.
 */
public class OptionalFragment extends BaseFragment{
    private LQRRecyclerView mRecyclerView;
    private LQRAdapterForRecyclerView<MarketUSDTBean> adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market_optional;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mRecyclerView = (LQRRecyclerView) $(R.id.recycler_view);
        adapter = new LQRAdapterForRecyclerView<MarketUSDTBean>(getActivity(),new ArrayList<MarketUSDTBean>(),R.layout.item_recyclerview_optional) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, MarketUSDTBean item, int position) {

            }

        };

        View footView =LayoutInflater.from(getActivity()).inflate(R.layout.footview_optional, null);
        footView.findViewById(R.id.btn_add_optional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), AddOptionalActivity.class);
                startActivity(intent);
            }
        });
        adapter.addFooterView(footView);


        mRecyclerView.setAdapter(adapter.getHeaderAndFooterAdapter());


        for(int i=0;i<10;i++) {
            adapter.addLastItem(new MarketUSDTBean());
        }


    }

    @Override
    protected void onEvent() {

        View footView =LayoutInflater.from(getActivity()).inflate(R.layout.footview_optional, null);
        footView.setOnClickListener(this::onClick);

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }


    @Override
    protected void lazyFetchData() {

    }


    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.__leak_canary_action) {
        }else {

        }

    }

}
