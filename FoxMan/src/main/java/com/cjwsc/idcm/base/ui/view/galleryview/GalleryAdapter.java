package com.cjwsc.idcm.base.ui.view.galleryview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liaoyunxia on 17/7/31.
 */
public abstract class GalleryAdapter extends PagerAdapter {

    public abstract int getGallerSize();
    public abstract View getItemView(int position);

    @Override
    public int getCount() {
        return getGallerSize() == 0 ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= getGallerSize();
        View view = getItemView(position);
//        ViewParent viewParent = view.getParent();
//        if (viewParent != null) {
//            ((ViewGroup) viewParent).removeView(view);
//        }
        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



}
