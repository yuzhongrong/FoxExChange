package com.foxexchange.c2c.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.cjwsc.idcm.base.BaseFragment;
import com.cjwsc.idcm.base.BaseView;
import com.foxexchange.c2c.R;
import com.foxexchange.common.adapter.MyPagerAdapter;
import com.cjwsc.idcm.flycotablayout.SlidingTabLayoutInner;

import java.util.ArrayList;

public class C2CTradeInFragment extends BaseFragment {

    private SlidingTabLayoutInner frg_tab;
    private MyPagerAdapter adapter;
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private String[] titles=new String[]{"UP","USDT"};
    private ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trade_in;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        frg_tab= (SlidingTabLayoutInner) $(R.id.frg_tab);
        viewPager= (ViewPager) $(R.id.frag_vp);
        fragmentList.add(new UPFragment());
        fragmentList.add(new USDTFragment());
        adapter=new MyPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        frg_tab.setViewPager(viewPager,titles,getChildFragmentManager(),fragmentList);

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }
}
