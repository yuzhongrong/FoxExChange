package com.cjwsc.idcm.base.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cjwsc.idcm.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * Created by hzp.
 */
public class AVLoadingDialog extends Dialog {

    private Context mContext;
    private AVLoadingIndicatorView avi;
    private TextView mTextView;

    /**
     * LoadingDialog
     *
     * @param context 上下文
     */
    public AVLoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        mContext = context;
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_avloading, null);
        avi = (AVLoadingIndicatorView) view.findViewById(R.id.dialog_avloading);
        mTextView = (TextView) view.findViewById(R.id.tv_dialog);
        // Random rand = new Random();
        // int randNum = rand.nextInt(INDICATORS.length);
        // avi.setIndicator(INDICATORS[randNum]);
        // avi.setIndicator("LineSpinFadeLoader");
        this.setCanceledOnTouchOutside(false);
        this.setContentView(view);
    }

    /**
     * 设置加载文本
     */
    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setTextVisible(Boolean isVisible) {
        if (isVisible) {
            mTextView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void show() {
        avi.show();
        super.show();
    }


    @Override
    public void cancel() {
        avi.hide();
        super.cancel();
    }


    private static final String[] INDICATORS = new String[]{

            "BallPulseIndicator",

            "BallGridPulseIndicator",

            "BallClipRotateIndicator",

            "BallClipRotatePulseIndicator",

            "SquareSpinIndicator",

            "BallClipRotateMultipleIndicator",

            "BallPulseRiseIndicator",

            "BallRotateIndicator",

            "CubeTransitionIndicator",

            "BallZigZagIndicator",

            "BallZigZagDeflectIndicator",

            "BallTrianglePathIndicator",

            "BallScaleIndicator",

            "LineScaleIndicator",

            "LineScalePartyIndicator",

            "BallScaleMultipleIndicator",

            "BallPulseSyncIndicator",

            "BallBeatIndicator",

            "LineScalePulseOutIndicator",

            "LineScalePulseOutRapidIndicator",

            "BallScaleRippleIndicator",

            "BallScaleRippleMultipleIndicator",

            "BallSpinFadeLoaderIndicator",

            "LineSpinFadeLoaderIndicator",

            "TriangleSkewSpinIndicator",

            "PacmanIndicator",

            "BallGridBeatIndicator",

            "SemiCircleSpinIndicator",

            "com.wang.avi.sample.MyCustomIndicator"

    };
}

