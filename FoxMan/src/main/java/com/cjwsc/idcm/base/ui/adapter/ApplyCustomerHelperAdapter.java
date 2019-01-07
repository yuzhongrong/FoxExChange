package com.cjwsc.idcm.base.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by yuzhongrong on 2017/8/24.
 */

public class ApplyCustomerHelperAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private  List<String> mtitles;

    public ApplyCustomerHelperAdapter(FragmentManager fm) {
        super(fm);
    }

    public ApplyCustomerHelperAdapter(FragmentManager fm, List<Fragment> fragments,List<String> mtitles) {
        super(fm);
        this.fragments=fragments;
        this.mtitles=mtitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mtitles.size();
    }


}
