package com.cjwsc.idcm.widget.popwindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjwsc.idcm.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Morning on 2017/8/17.
 */

public class WithdrawPaymentPopWindow extends BasePopupWindow implements View.OnClickListener {

    public interface Host {
        void onWithdrawClick(int type);
    }

    private Host mHost;

    public void setHost(Host host) {
        mHost = host;
    }

    public WithdrawPaymentPopWindow(Activity context) {
        super(context);
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
        return mLayoutContainer;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    //支付宝checkbox
    private CheckBox mCheckBoxZfb;
    //微信checkbox
    private CheckBox mCheckBoxWechat;

    private ImageView mImgClose;
    private View mLayoutContainer;
    private View mShadeView;
    //支付宝布局
    private View mChooserZfbContainer;
    //微信布局
    private View mChooserWechatContainer;
    //确认
    private TextView mTvSubmit;

    @Override
    public View onCreatePopupView() {
        View root = createPopupById(R.layout.popwindow_withdraw_channel);
        mLayoutContainer = root.findViewById(R.id.layout_container);
        mImgClose = (ImageView) root.findViewById(R.id.img_close);
        mImgClose.setOnClickListener(this);
        mShadeView = root.findViewById(R.id.view_shade);
        mShadeView.setOnClickListener(this);
        mCheckBoxWechat = (CheckBox) root.findViewById(R.id.cb_wechat);
        mCheckBoxZfb = (CheckBox) root.findViewById(R.id.cb_zfb);
        mChooserZfbContainer = root.findViewById(R.id.layout_chooser_zfb);
        mChooserWechatContainer = root.findViewById(R.id.layout_chooser_wechat);
        mChooserZfbContainer.setOnClickListener(this);
        mChooserWechatContainer.setOnClickListener(this);
        mTvSubmit = (TextView) root.findViewById(R.id.tv_submit);
        mTvSubmit.setOnClickListener(this);


        mChooserZfbContainer.setOnClickListener(this);
        mChooserWechatContainer.setOnClickListener(this);
        mCheckBoxWechat.setOnClickListener(this);
        mCheckBoxZfb.setOnClickListener(this);
        return root;
    }

    private void chooseZFB() {
        mCheckBoxWechat.setChecked(false);
        mCheckBoxZfb.setChecked(true);
        mType = TYPE_PAYMENT_ZFB;
    }

    private void chooseWechat() {
        mCheckBoxZfb.setChecked(false);
        mCheckBoxWechat.setChecked(true);
        mType = TYPE_PAYMENT_WECHAT;
    }

    private int mType;
    public static final int TYPE_PAYMENT_ZFB = 0x001;//选择支付宝
    public static final int TYPE_PAYMENT_WECHAT = 0x002;//选择微信

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.img_close) {
            dismiss();
        } else if (id  == R.id.view_shade) {
            dismiss();
        } else if (id == R.id.tv_submit) {
            if (mType != 0) {
                dismiss();
                mHost.onWithdrawClick(mType);
                setCurrentType(mType);

            }
        } else if (id == R.id.layout_chooser_zfb) {
            chooseZFB();
        } else if (id == R.id.layout_chooser_wechat) {
            chooseWechat();
        } else if (id == R.id.cb_wechat) {
            chooseWechat();
        } else if (id == R.id.cb_zfb) {
            chooseZFB();
        }
    }



    public void setCurrentType(int type){

        this.mType=type;

    }

    public int getCurrentType(){

       return mType;

    }


}
