package com.foxexchange.market.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cjwsc.idcm.Utils.ACacheUtil;
import bitchat.android.com.market.R;

/**
 * created by pzw on 2018/12/17.
 */
public class MarketItemSelectLayout extends RelativeLayout implements View.OnClickListener {
    public OnMarketSortClickListenner onMarketSortClickListenner;

    private ImageView ivNameUp;
    private ImageView ivNameDown;
    private ImageView ivPriceUp;
    private ImageView ivPriceDown;
    private ImageView ivUppercentUp;
    private ImageView ivUppercentDown;
    private int tabIndex = 0;

    public MarketItemSelectLayout(Context context) {
        this(context,null);
    }

    public MarketItemSelectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        ACacheUtil.get(getContext()).clear();
        /*
         * 获取到自定义控件需要的的布局
         */
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.relativelayout_maket_itemselct, null);
        addView(relativeLayout);
        relativeLayout.findViewById(R.id.market_rl_name).setOnClickListener(this);
        relativeLayout.findViewById(R.id.market_rl_newprice).setOnClickListener(this);
        relativeLayout.findViewById(R.id.market_rl_uppercent).setOnClickListener(this);
        ivNameUp = relativeLayout.findViewById(R.id.iv_name_up);
        ivNameDown = relativeLayout.findViewById(R.id.iv_name_down);
        ivPriceUp = relativeLayout.findViewById(R.id.iv_price_up);
        ivPriceDown = relativeLayout.findViewById(R.id.iv_price_down);
        ivUppercentUp = relativeLayout.findViewById(R.id.iv_uppercent_up);
        ivUppercentDown = relativeLayout.findViewById(R.id.iv_uppercent_down);
    }

    @Override
    public void onClick(View v) {
        Resources res = this.getResources();
        int i = v.getId();
        if (i == R.id.market_rl_name) {
            if(ivNameUp.getDrawable().getConstantState().equals(res.getDrawable(R.mipmap.icon_arrow_up_select).getConstantState())) {
                setIconBeWhite();
                ivNameDown.setImageResource(R.mipmap.icon_arrow_down_select);
                onMarketSortClickListenner.clicked(0,"down");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"0down");
            }else  if (ivNameDown.getDrawable().getConstantState().equals(res.getDrawable(R.mipmap.icon_arrow_down_select).getConstantState())) {
                setIconBeWhite();
                ivNameUp.setImageResource(R.mipmap.icon_arrow_up_select);
                onMarketSortClickListenner.clicked(0,"up");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"0up");
            }else {
                setIconBeWhite();
                ivNameUp.setImageResource(R.mipmap.icon_arrow_up_select);
                onMarketSortClickListenner.clicked(0,"up");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"0up");
            }
        }else if(i == R.id.market_rl_newprice) {
            if(ivPriceUp.getDrawable().getConstantState().equals(res.getDrawable(R.mipmap.icon_arrow_up_select).getConstantState())) {
                setIconBeWhite();
                ivPriceDown.setImageResource(R.mipmap.icon_arrow_down_select);
                onMarketSortClickListenner.clicked(1,"down");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"1down");
            }else  if (ivPriceDown.getDrawable().getConstantState().equals(res.getDrawable(R.mipmap.icon_arrow_down_select).getConstantState())) {
                setIconBeWhite();
                ivPriceUp.setImageResource(R.mipmap.icon_arrow_up_select);
                onMarketSortClickListenner.clicked(1,"up");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"1up");
            }else {
                setIconBeWhite();
                ivPriceUp.setImageResource(R.mipmap.icon_arrow_up_select);
                onMarketSortClickListenner.clicked(1,"up");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"1up");
            }
        }else if(i == R.id.market_rl_uppercent) {
            if(ivUppercentUp.getDrawable().getConstantState().equals(res.getDrawable(R.mipmap.icon_arrow_up_select).getConstantState())) {
                setIconBeWhite();
                ivUppercentDown.setImageResource(R.mipmap.icon_arrow_down_select);
                onMarketSortClickListenner.clicked(3,"down");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"2down");
            }else  if (ivUppercentDown.getDrawable().getConstantState().equals(res.getDrawable(R.mipmap.icon_arrow_down_select).getConstantState())) {
                setIconBeWhite();
                ivUppercentUp.setImageResource(R.mipmap.icon_arrow_up_select);
                onMarketSortClickListenner.clicked(3,"up");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"2up");
            }else {
                setIconBeWhite();
                ivUppercentUp.setImageResource(R.mipmap.icon_arrow_up_select);
                onMarketSortClickListenner.clicked(3,"up");
                ACacheUtil.get(getContext()).put("tab" + tabIndex,"2up");
            }
        }
    }


    /**
     * 接口回调,0,1,2 分别代表第一二个三个按钮,第二个参数"up"和"down"代表排序状态
     */
    public interface OnMarketSortClickListenner {
        void clicked(int positon,String sortType);
    }

    /**
     * 设置接口回调
     */
    public void setOnMarketSortClickListenner(OnMarketSortClickListenner onMarketSortClickListenner) {
        this.onMarketSortClickListenner = onMarketSortClickListenner;
    }

    //全部变成白色
    private void setIconBeWhite() {
        Resources res = this.getResources();
        Bitmap bmpUp = BitmapFactory.decodeResource(
                res, R.mipmap.icon_arrow_up);
        Bitmap bmpDown = BitmapFactory.decodeResource(
                res, R.mipmap.icon_arrow_down);
        ivNameUp.setImageBitmap(bmpUp);
        ivNameDown.setImageBitmap(bmpDown);
        ivNameUp.setImageBitmap(bmpUp);
        ivNameDown.setImageBitmap(bmpDown);
        ivPriceUp.setImageBitmap(bmpUp);
        ivPriceDown.setImageBitmap(bmpDown);
        ivPriceUp.setImageBitmap(bmpUp);
        ivPriceDown.setImageBitmap(bmpDown);
        ivUppercentUp.setImageBitmap(bmpUp);
        ivUppercentDown.setImageBitmap(bmpDown);
        ivUppercentUp.setImageBitmap(bmpUp);
        ivUppercentDown.setImageBitmap(bmpDown);
    }

    //设置不同tab的选项
    public void setTabSelectIcon(int tabIndex) {
        this.tabIndex = tabIndex;
        String iconSelect = ACacheUtil.get(getContext()).getAsString("tab" + tabIndex);
        //如果为空默认显示第三个，如果有之值则回显之前的
        setIconBeWhite();
        if(iconSelect == null) {
            ivUppercentUp.setImageResource(R.mipmap.icon_arrow_up_select);
        }else {
            if(iconSelect.equals("0up")) {
                ivNameUp.setImageResource(R.mipmap.icon_arrow_up_select);
            }else if(iconSelect.equals("0down")) {
                ivNameDown.setImageResource(R.mipmap.icon_arrow_down_select);
            }else if(iconSelect.equals("1up")) {
                ivPriceUp.setImageResource(R.mipmap.icon_arrow_up_select);
            }else if(iconSelect.equals("1down")) {
                ivPriceDown.setImageResource(R.mipmap.icon_arrow_down_select);
            }else if(iconSelect.equals("2up")) {
                ivUppercentUp.setImageResource(R.mipmap.icon_arrow_up_select);
            }else if(iconSelect.equals("2down")) {
                ivUppercentDown.setImageResource(R.mipmap.icon_arrow_down_select);
            }
        }
    }
}
