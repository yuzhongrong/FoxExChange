package com.foxexchange.exchange.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exchange.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.foxexchange.common.widgets.CustomerTextView;
import com.foxexchange.exchange.beans.CurrentDelegationBean;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;

/**
 * created by pzw on 2018/12/21.
 */
public class ExchangeHorizontalLinearlayout extends LinearLayout{

    public ExchangeHorizontalLinearlayout(Context context) {
        this(context,null);
    }

    public ExchangeHorizontalLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout exchangeHorizontalLinearlayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.ll_transaction_horizontal_screen,null);
        //最优价买入
        TextView tvPriceOff = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_tv_priceoff);
        TextView tvPriceOffh = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_tv_priceoffh);

        UpdownPriceLayout updownPriceLayout = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_limitedprice_layout);
        UpdownPriceLayout updownPriceLayouth = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_limitedpriceh_layout);
        TextView tvPriceRMBh = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_pretradeh_horizontalprice);
        TextView tvPriceRMB = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_pretrade_horizontalprice);
        //交易额
        TextView tvTradeSum = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_trade_coinprice);
        TextView tvTradeSumh = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_tradeh_coinprice);
        TextView tvTradeRmbh = exchangeHorizontalLinearlayout.findViewById(R.id.tv_exchangeh_tradermb);
        TextView tvTradeRmb = exchangeHorizontalLinearlayout.findViewById(R.id.tv_exchange_tradermb);

        //输入数量
        EditText etBuyCount = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_buy_count);
        EditText etBuyCounth = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_buyh_count);
        etBuyCount.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        etBuyCounth.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        //tab
        CommonTabLayout tab  = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_horizontal_tablayout);
        ArrayList tabEntrts = new ArrayList();
        CustomTabEntity tabEntity = new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "限价";
            }

            @Override
            public int getTabSelectedIcon() {
                return 0;
            }

            @Override
            public int getTabUnselectedIcon() {
                return 0;
            }
        };
        CustomTabEntity tabEntity1 = new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "市价";
            }

            @Override
            public int getTabSelectedIcon() {
                return 0;
            }

            @Override
            public int getTabUnselectedIcon() {
                return 0;
            }
        };
        tabEntrts.add(tabEntity);
        tabEntrts.add(tabEntity1);
        tab.setTabData(tabEntrts);
        //为tab设置点击事件进行下面控件内容
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    tvPriceOff.setVisibility(GONE);
                    tvPriceOffh.setVisibility(GONE);
                    updownPriceLayout.setVisibility(VISIBLE);
                    updownPriceLayouth.setVisibility(VISIBLE);
                    tvPriceRMBh.setVisibility(VISIBLE);
                    tvPriceRMB.setVisibility(VISIBLE);
                    tvTradeSum.setVisibility(VISIBLE);
                    tvTradeSumh.setVisibility(VISIBLE);
                    tvTradeRmbh.setVisibility(VISIBLE);
                    tvTradeRmb.setVisibility(VISIBLE);
                }else if(position == 1) {
                    tvPriceOff.setVisibility(VISIBLE);
                    tvPriceOffh.setVisibility(VISIBLE);
                    updownPriceLayout.setVisibility(GONE);
                    updownPriceLayouth.setVisibility(GONE);
                    tvPriceRMBh.setVisibility(GONE);
                    tvPriceRMB.setVisibility(GONE);
                    tvTradeSum.setVisibility(GONE);
                    tvTradeSumh.setVisibility(GONE);
                    tvTradeRmbh.setVisibility(GONE);
                    tvTradeRmb.setVisibility(GONE);
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        LQRRecyclerView rvUpdata = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_horizontal_updata);
        LQRRecyclerView rvDowndata = exchangeHorizontalLinearlayout.findViewById(R.id.exchange_horizontal_downdata);
        LQRAdapterForRecyclerView<CurrentDelegationBean> updataAdapter = new LQRAdapterForRecyclerView<CurrentDelegationBean>(getContext(),new ArrayList<CurrentDelegationBean>(),R.layout.item_recylerview_updata_horizontal) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, CurrentDelegationBean item, int position) {

            }
        };
        LQRAdapterForRecyclerView<CurrentDelegationBean> downDataAdapter = new LQRAdapterForRecyclerView<CurrentDelegationBean>(getContext(),new ArrayList<CurrentDelegationBean>(),R.layout.item_recylerview_downdata_horizontal) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, CurrentDelegationBean item, int position) {

            }
        };

        //添加头布局
        updataAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.item_title_updata_horizontal,null));
        downDataAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.item_title_downdata_horizontal,null));
        rvUpdata.setAdapter(updataAdapter.getHeaderAndFooterAdapter());
        rvDowndata.setAdapter(downDataAdapter.getHeaderAndFooterAdapter());
        for(int i=0;i < 6;i++) {
            updataAdapter.addLastItem(new CurrentDelegationBean());
            downDataAdapter.addLastItem(new CurrentDelegationBean());

        }
        addView(exchangeHorizontalLinearlayout);
    }
}
