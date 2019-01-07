package com.cjwsc.idcm.base.ui.view.galleryview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.ScreenUtil;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoyunxia on 17/10/31.
 */

public class SmartGalleryViewPager extends RelativeLayout {
    private Context context;
    private ViewPager viewPager;
    private LinearLayout mLinearPointIndicator;
    private int mDataSize;
    private List<ImageView> mPointViewList = new ArrayList<>();
    private int[] mPointResIds = new int[]{R.drawable.shape_banner_dot_focused, R.drawable.shape_banner_dot_normal};
    private boolean mAutoPlayAble = true;
    private RelativeLayout mLinearBannerParent;


    public SmartGalleryViewPager(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SmartGalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mAutoPlayTask = new AutoPlayTask(this);
        init();
    }


    private void init() {
        LayoutInflater.from(context).inflate(R.layout.viewpager_gallery, this);
        mLinearBannerParent = (RelativeLayout) findViewById(R.id.linear_banner_parent);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOverScrollMode(2);
        mLinearPointIndicator = (LinearLayout) findViewById(R.id.liner_point_indicator);

    }

    public SmartGalleryViewPager setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer){
        viewPager.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    /**
     * set the duration between two slider changes.
     * @param period
     * @param interpolator
     */
    public SmartGalleryViewPager setSliderTransformDuration(int period,Interpolator interpolator){
        try{
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(),interpolator, period);
            mScroller.set(viewPager,scroller);
        }catch (Exception e){

        }

        return this;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mAutoPlayAble) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopAutoPlay();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean mIsFirstInvisible = true;
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            startAutoPlay();
        } else if (visibility == INVISIBLE || visibility == GONE) {
            onInvisibleToUser();
        }
    }

    private void onInvisibleToUser() {
        stopAutoPlay();

        // 处理 RecyclerView 中从对用户不可见变为可见时卡顿的问题
        if (!mIsFirstInvisible && mAutoPlayAble && viewPager != null && viewPager.getAdapter().getCount() > 0) {
            moveNextPosition();
        }
        mIsFirstInvisible = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onInvisibleToUser();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }
    public void setCurrentItem(int position) {
        viewPager.setCurrentItem(position);
    }

    public SmartGalleryViewPager setAdapter(PagerAdapter adapter, int dataSize) {
        mDataSize = dataSize;
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        initPintCantainerView();
        startAutoPlay();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mLinearBannerParent.setClipChildren(false);
            }

            @Override
            public void onPageSelected(int position) {
                mLinearBannerParent.setClipChildren(false);
                for (int i = 0; i < mPointViewList.size(); i++) {
                    mPointViewList.get(i).setImageResource(mPointResIds[1]);
                    if (position % mDataSize != i) {
                        mPointViewList.get(i).setImageResource(mPointResIds[0]);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return this;
    }
    //轮播机制开始
    private AutoPlayTask mAutoPlayTask;
    private int mAutoPlayInterval = 4000;
    public void startAutoPlay() {
        stopAutoPlay();
        if (mAutoPlayAble) {
            postDelayed(mAutoPlayTask, mAutoPlayInterval);
        }
    }
    public void stopAutoPlay() {
        if (mAutoPlayTask != null) {
            removeCallbacks(mAutoPlayTask);
        }
    }

    private static class AutoPlayTask implements Runnable {
        private final WeakReference<SmartGalleryViewPager> mBanner;

        private AutoPlayTask(SmartGalleryViewPager banner) {
            mBanner = new WeakReference<>(banner);
        }

        @Override
        public void run() {
            SmartGalleryViewPager banner = mBanner.get();
            if (banner != null) {
                banner.moveNextPosition();
                banner.startAutoPlay();
            }
        }
    }



    private void initPintCantainerView() {
        mLinearPointIndicator.removeAllViews();
        mPointViewList.clear();
        if (mDataSize == 0) {
            return;
        }
        for (int i = 0; i < mDataSize; i++) {
            ImageView ivPointView = new ImageView(getContext());
            ivPointView.setPadding(ScreenUtil.dp2px(2, getContext()), 0, ScreenUtil.dp2px(3, getContext()), 0);
            ivPointView.setImageResource(mPointResIds[0]);
            if ((Integer.MAX_VALUE / 2) % mDataSize == i) {
                ivPointView.setImageResource(mPointResIds[1]);
            }
            mPointViewList.add(ivPointView);
            mLinearPointIndicator.addView(ivPointView);
        }
    }



    /**
     * move to next slide.
     */
    public void moveNextPosition(boolean smooth) {

        if (getRealAdapter() == null)
            throw new IllegalStateException("You did not set a slider adapter");

        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1,smooth);
    }

    public void moveNextPosition() {
        moveNextPosition(true);
    }


    private PagerAdapter getRealAdapter() {
        PagerAdapter adapter = viewPager.getAdapter();
        return adapter;
    }
}
