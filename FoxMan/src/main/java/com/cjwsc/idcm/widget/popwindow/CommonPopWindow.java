package com.cjwsc.idcm.widget.popwindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.cjwsc.idcm.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Morning on 2017/8/17.
 */

public class CommonPopWindow extends BasePopupWindow {
    private TextView mTvTitle;
    private TextView mTvMsg;
    private Button mBtnNegative;
    private Button mBtnPositive;

    public ICommonPopCallback mICommonPopCallback;

    public void setListener(ICommonPopCallback listener) {
        mICommonPopCallback = listener;
    }

    public interface ICommonPopCallback {
        void onPositive();

        void onNegative();
    }

    public CommonPopWindow(Activity context) {
        super(context);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setMsg(String msg) {
        mTvMsg.setText(msg);
    }

    public void setNegativeButtonGone() {
        mBtnNegative.setVisibility(View.GONE);
    }

    public void setBtnPositiveText(String text) {
        mBtnPositive.setText(text);
    }


    @Override
    protected Animation initShowAnimation() {
        return AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
    }

    @Override
    protected Animation initExitAnimation() {
        return AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        View root = createPopupById(R.layout.popwindow_common);
        mTvTitle = (TextView) root.findViewById(R.id.tv_pop_title);
        mTvMsg = (TextView) root.findViewById(R.id.tv_pop_msg);
        mBtnNegative = (Button) root.findViewById(R.id.btn_pop_negative);
        mBtnPositive = (Button) root.findViewById(R.id.btn_pop_positive);

        mBtnNegative.setOnClickListener(view -> {
            dismiss();
            if (mICommonPopCallback != null) {
                mICommonPopCallback.onNegative();
            }
        });

        mBtnPositive.setOnClickListener(view -> {
            dismiss();
            if (mICommonPopCallback != null) {
                mICommonPopCallback.onPositive();
            }
        });
        return root;
    }

    public CommonPopWindow changePositiveText(String positiveText) {
        mBtnPositive.setText(positiveText);
        return this;
    }
    public CommonPopWindow changeNegativeText(String negativeText) {
        mBtnNegative.setText(negativeText);
        return this;
    }
}
