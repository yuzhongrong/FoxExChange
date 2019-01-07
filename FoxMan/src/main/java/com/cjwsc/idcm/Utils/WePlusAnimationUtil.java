package com.cjwsc.idcm.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.cjwsc.idcm.base.ui.view.revealview.CircularRevealLayout;

/**
 * Created by liaoyunxia on 17/8/8.
 * 我们+ 动画工具类
 */

public class WePlusAnimationUtil {

    public static final int RECT_LEFT = 0;
    public static final int RECT_TOP = 1;
    public static final int RECT_RIGHT = 2;
    public static final int RECT_BOTTOM = 3;
    /**
     * 展示揭露动画
     *
     * @param view
     * @param drawable
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showRevealAnimal(View view, Drawable drawable,
                                        int centerX, int centerY,
                                        float startRadius, float endRadius) {
        if (view instanceof ImageView) {
            if (drawable != null) {
                ((ImageView) view).setImageDrawable(drawable);
            }

        }
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.start();
    }

    /**
     * 展示普通动画
     *
     * @param view
     * @param drawable
     * @param animalId
     */
    public static void showPublicAnimal(View view, Drawable drawable, int animalId, Animation.AnimationListener animationListener) {
        if (view instanceof ImageView) {
            if (drawable != null) {
                ((ImageView) view).setImageDrawable(drawable);
            }
        }
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), animalId);
        if (animationListener != null) {
            animation.setAnimationListener(animationListener);
        }
        view.startAnimation(animation);
    }

    public static Animator createCircularRevealAnimal(final View view, Drawable drawable,
                                                      final int centerX, final int centerY,
                                                      final float startRadius, final float endRadius,
                                                      AnimatorListenerAdapter animatorListener) {

        if (view instanceof ImageView) {
            if (drawable != null) {
                ((ImageView) view).setImageDrawable(drawable);

            }

        }
        Animator animator = CircularRevealLayout.Builder.on(view)
                .centerX(centerX)
                .centerY(centerY)
                .startRadius(startRadius)
                .endRadius(endRadius)
                .create();
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();

        return animator;
    }

}
