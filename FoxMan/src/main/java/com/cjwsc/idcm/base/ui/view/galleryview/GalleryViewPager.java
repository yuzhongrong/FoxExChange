package com.cjwsc.idcm.base.ui.view.galleryview;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.ScreenUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liaoyunxia on 17/7/31.
 */
public class GalleryViewPager extends LinearLayout {
    private Context context;
    private ViewPager viewPager;
    private LinearLayout mLinearPointIndicator;
    private int mDataSize;
    private List<ImageView> mPointViewList = new ArrayList<>();
    private int[] mPointResIds = new int[]{R.drawable.shape_banner_dot_focused, R.drawable.shape_banner_dot_normal};
    public GalleryViewPager(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public GalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init(){
        LayoutInflater.from(context).inflate(R.layout.viewpager_gallery, this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setPageMargin(DensityUtil.dp2px(context, 15));
        viewPager.setOffscreenPageLimit(3);
//
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        recoverCycle();
                        break;
                }
                return false;
            }
        });
        mLinearPointIndicator = (LinearLayout) findViewById(R.id.liner_point_indicator);
    }


    public void setPageMargin(int px){
        viewPager.setPageMargin(px);
    }

    public void setOffscreenPageLimit(int count){
        viewPager.setOffscreenPageLimit(count);
    }

    public GalleryViewPager setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer){
        viewPager.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    public void setViewPagerMargin(int px){
        MarginLayoutParams layoutParams = (MarginLayoutParams) viewPager.getLayoutParams();
        layoutParams.setMargins(px, 0, px, 0);
        viewPager.setLayoutParams(layoutParams);
    }

    public void setCurrentItem(int position){
        viewPager.setCurrentItem(position);
    }

    public GalleryViewPager setAdapter(PagerAdapter adapter,int dataSize){
        mDataSize = dataSize;
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE/2);
//        viewPager.setCurrentItem(1);
        initPintCantainerView();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
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

    private void initPintCantainerView() {
        mLinearPointIndicator.removeAllViews();
        mPointViewList.clear();
        if (mDataSize == 0) {
            return;
        }
        for (int i = 0; i < mDataSize; i++) {
            ImageView ivPointView = new ImageView(getContext());
            ivPointView.setPadding(ScreenUtil.dp2px(2,getContext()), 0, ScreenUtil.dp2px(3,getContext()), 0);
            ivPointView.setImageResource(mPointResIds[0]);
            if ((Integer.MAX_VALUE / 2) % mDataSize == i) {
                ivPointView.setImageResource(mPointResIds[1]);
            }
            mPointViewList.add(ivPointView);
            mLinearPointIndicator.addView(ivPointView);
        }
    }


    private Timer mCycleTimer;
    private TimerTask mCycleTask;

    /**
     */
    private Timer mResumingTimer;
    private TimerTask mResumingTask;

    /**
     */
    private boolean mCycling;

    private long mSliderDuration = 4000;

    private boolean mAutoCycle;
    private boolean mAutoRecover = true;

    private android.os.Handler mh = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            moveNextPosition(true);
        }
    };

    public GalleryViewPager startAutoCycle(){
        startAutoCycle(mSliderDuration, mSliderDuration, mAutoRecover);
        return this;
    }

    /**
     * start auto cycle.
     * @param delay delay time
     * @param duration animation duration time.
     * @param autoRecover if recover after user touches the slider.
     */
    public void startAutoCycle(long delay,long duration,boolean autoRecover){
        if(mCycleTimer != null) mCycleTimer.cancel();
        if(mCycleTask != null) mCycleTask.cancel();
        if(mResumingTask != null) mResumingTask.cancel();
        if(mResumingTimer != null) mResumingTimer.cancel();
        mSliderDuration = duration;
        mCycleTimer = new Timer();
        mAutoRecover = autoRecover;
        mCycleTask = new TimerTask() {
            @Override
            public void run() {
                mh.sendEmptyMessage(0);
            }
        };
        mCycleTimer.schedule(mCycleTask,delay,mSliderDuration);
        mCycling = true;
        mAutoCycle = true;
    }

    /**
     * pause auto cycle.
     */
    public void pauseAutoCycle(){
        if(mCycling){
            mCycleTimer.cancel();
            mCycleTask.cancel();
            mCycling = false;
        }else{
            if(mResumingTimer != null && mResumingTask != null){
                recoverCycle();
            }
        }
    }

    /**
     * set the duration between two slider changes. the duration value must >= 500
     * @param duration
     */
    public GalleryViewPager setDuration(long duration){
        if(duration >= 500){
            mSliderDuration = duration;
            if(mAutoCycle && mCycling){
                startAutoCycle();
            }
        }

        return this;
    }

    /**
     * stop the auto circle
     */
    public void stopAutoCycle(){
        if(mCycleTask!=null){
            mCycleTask.cancel();
        }
        if(mCycleTimer!= null){
            mCycleTimer.cancel();
        }
        if(mResumingTimer!= null){
            mResumingTimer.cancel();
        }
        if(mResumingTask!=null){
            mResumingTask.cancel();
        }
        mAutoCycle = false;
        mCycling = false;
    }

    /**
     * when paused cycle, this method can weak it up.
     */
    public void recoverCycle(){
        if(!mAutoRecover || !mAutoCycle){
            return;
        }

        if(!mCycling){
            if(mResumingTask != null && mResumingTimer!= null){
                mResumingTimer.cancel();
                mResumingTask.cancel();
            }
            mResumingTimer = new Timer();
            mResumingTask = new TimerTask() {
                @Override
                public void run() {
                    startAutoCycle();
                }
            };
//            mResumingTimer.schedule(mResumingTask, 6000);
            mResumingTimer.schedule(mResumingTask, 3000);
        }
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                pauseAutoCycle();
                break;
        }
        return false;
    }

    /**
     * set the duration between two slider changes.
     * @param period
     * @param interpolator
     */
    public GalleryViewPager setSliderTransformDuration(int period,Interpolator interpolator){
        try{
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(),interpolator, period);
            mScroller.set(viewPager,scroller);
        }catch (Exception e){

        }

        return this;
    }

    /**
     *set current slider
     * @param position
     */
    public void setCurrentPosition(int position, boolean smooth) {
        if (getRealAdapter() == null)
            throw new IllegalStateException("You did not set a slider adapter");
        if(position >= getRealAdapter().getCount()){
            throw new IllegalStateException("Item position is not exist");
        }
        int p = viewPager.getCurrentItem() % getRealAdapter().getCount();
        int n = (position - p) + viewPager.getCurrentItem();
        viewPager.setCurrentItem(n, smooth);
    }

    public void setCurrentPosition(int position) {
        setCurrentPosition(position, true);
    }

    /**
     * move to prev slide.
     */
    public void movePrevPosition(boolean smooth) {

        if (getRealAdapter() == null)
            throw new IllegalStateException("You did not set a slider adapter");

        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, smooth);
    }

    public void movePrevPosition(){
        movePrevPosition(true);
    }

    /**
     * move to next slide.
     */
    public void moveNextPosition(boolean smooth) {

        if (getRealAdapter() == null)
            throw new IllegalStateException("You did not set a slider adapter");

        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, smooth);
    }

    public void moveNextPosition() {
        moveNextPosition(true);
    }



    private PagerAdapter getRealAdapter(){
        PagerAdapter adapter = viewPager.getAdapter();
        return adapter;
    }
}
