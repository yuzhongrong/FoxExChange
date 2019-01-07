package com.cjwsc.idcm.adapter;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;


import java.util.List;


/**
 * Created by: Ysw on 2016/12/13.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private Activity mActivity;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setParent(Activity activity, List<Fragment> list) {
        this.mActivity = activity;
        this.mList = list;
    }
}
