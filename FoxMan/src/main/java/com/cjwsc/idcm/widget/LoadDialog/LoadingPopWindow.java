package com.cjwsc.idcm.widget.LoadDialog;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.cjwsc.idcm.R;

import razerdp.basepopup.BasePopupWindow;


/**
 * Created by yuzhongrong on 2017/8/21.
 */

public class LoadingPopWindow extends BasePopupWindow {
    private static final int INTERVAL = 90;

    private ImageView mImageView;
    private AnimationDrawable mAnimation;

    public LoadingPopWindow(Context context) {
        super(context);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    protected Animation initExitAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }


    @Override
    public View onCreatePopupView() {
        View root = createPopupById(R.layout.loading_dialog1);
        mImageView = (ImageView) root.findViewById(R.id.icon);
        /**
         * 1在Android6.0上此动画和6.0以下在显示上有区别,clip标签；
         * 2在加载动画的时候中兴V5以及其他某些机型会产生只加载第一张图余下的几张都不加载的情况；
         * 为了解决这个问题，只能弃用ProgressBar了，改用ImageView
         */
        mAnimation = new AnimationDrawable();
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_01), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_02), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_03), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_04), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_05), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_06), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_07), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_08), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_09), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_10), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_11), INTERVAL);
        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.loading_12), INTERVAL);
        mAnimation.setOneShot(false);
        mImageView.setBackground(mAnimation);
        if (mAnimation != null && !mAnimation.isRunning()) {
            mAnimation.start();
        }
        return root;

    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    protected Animator initExitAnimator() {
        return null;
    }
}
