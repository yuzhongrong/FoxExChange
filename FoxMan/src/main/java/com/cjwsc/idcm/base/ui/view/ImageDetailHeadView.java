package com.cjwsc.idcm.base.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.LogUtil;


/**
 * 图文详情顶部控件
 * Created by xz on 2016/5/5.
 */
public class ImageDetailHeadView extends LinearLayout implements View.OnClickListener {

    // 图文详情
    private TextView tvImgDetail;
    // 规格参数
    private TextView tvGoodsParams;
    // 售后服务
    private TextView tvAfterSales;
    // 监听器
    private OnItemClickListener mClickListener;

    /**
     * @param context
     */
    public ImageDetailHeadView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attributeSet
     */
    public ImageDetailHeadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_image_detail_head, this, true);
        tvImgDetail = (TextView) view.findViewById(R.id.tv_product_details_buy_img_detail);
        tvImgDetail.setOnClickListener(this);
        tvGoodsParams = (TextView) view.findViewById(R.id.tv_product_details_buy_params);
        tvGoodsParams.setOnClickListener(this);
        tvAfterSales = (TextView) view.findViewById(R.id.tv_product_details_buy_after_sale);
        tvAfterSales.setOnClickListener(this);
//        tvImgDetail.performClick();
        tvImgDetail.setTextColor(getResources().getColor(R.color.color_e88272));
        tvGoodsParams.setTextColor(getResources().getColor(R.color.color_333333));
        tvAfterSales.setTextColor(getResources().getColor(R.color.color_333333));
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_product_details_buy_img_detail) {
            tvImgDetail.setTextColor(getResources().getColor(R.color.color_e88272));
            tvGoodsParams.setTextColor(getResources().getColor(R.color.color_333333));
            tvAfterSales.setTextColor(getResources().getColor(R.color.color_333333));
            LogUtil.e("控件" + 0);
            mClickListener.onItemClick(0);


            //规格参数
        } else if (i == R.id.tv_product_details_buy_params) {
            tvImgDetail.setTextColor(getResources().getColor(R.color.color_333333));
            tvGoodsParams.setTextColor(getResources().getColor(R.color.color_e88272));
            tvAfterSales.setTextColor(getResources().getColor(R.color.color_333333));
            LogUtil.e("控件" + 1);
            mClickListener.onItemClick(1);


            //售后服务
        } else if (i == R.id.tv_product_details_buy_after_sale) {
            tvImgDetail.setTextColor(getResources().getColor(R.color.color_333333));
            tvGoodsParams.setTextColor(getResources().getColor(R.color.color_333333));
            tvAfterSales.setTextColor(getResources().getColor(R.color.color_e88272));
            LogUtil.e("控件" + 2);
            mClickListener.onItemClick(2);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int itemNum);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mClickListener = onItemClickListener;
    }

}