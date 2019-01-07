package com.foxexchange.exchange.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.foxexchange.exchange.fragments.CurrentDelegationFragment;
import com.foxexchange.exchange.fragments.HistoryDelegationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vihaan on 1/9/15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private int mCurrentPosition = -1;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new CurrentDelegationFragment());
        fragments.add(new HistoryDelegationFragment());
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            CustomViewPager pager = (CustomViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}