package com.cjwsc.idcm.Utils.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;

/**
 * Created by yuzhongrong on 2018/2/1.
 */

public class AnimUtils {


    /**
     * 播放View动画
     *
     * @param animation
     * @param isReverse
     */
    public static void playViewAnimation(View view, Animation animation, boolean isReverse) {
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.1f);
        controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);
        if(view instanceof RecyclerView){
            RecyclerView view1=(RecyclerView)view;
            view1.setLayoutAnimation(controller);
            view1.getAdapter().notifyDataSetChanged();
            view1.scheduleLayoutAnimation();

        }else{
            view.setAnimation(animation);
        }

    }



}
