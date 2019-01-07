package com.foxexchange.market.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cjwsc.idcm.base.BaseActivity;
import com.cjwsc.idcm.base.BaseView;
import com.foxexchange.market.beans.MarketUSDTBean;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;

import bitchat.android.com.market.R;

@Route(path = "/market/activity/AddOptionalActivity",extras = 1)
public class AddOptionalActivity extends BaseActivity {

    private LQRRecyclerView mRecyclerView;
    private LQRAdapterForRecyclerView<MarketUSDTBean> adapter;
    private ImageView ivBack;
    private EditText etRxTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_optional;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mRecyclerView = (LQRRecyclerView) $(R.id.recycler_view);
        ivBack = (ImageView) $(R.id.iv_search_back);
        etRxTextView = (EditText) $(R.id.market_rxTextView);
        adapter = new LQRAdapterForRecyclerView<MarketUSDTBean>(this,new ArrayList<MarketUSDTBean>(),R.layout.item_optional_search) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, MarketUSDTBean item, int position) {

            }

        };
        mRecyclerView.setAdapter(adapter.getHeaderAndFooterAdapter());
        for(int i=0;i<10;i++) {
            adapter.addLastItem(new MarketUSDTBean());
        }
    }

    @Override
    protected void onEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected BaseView getView() {
        return null;
    }
}
