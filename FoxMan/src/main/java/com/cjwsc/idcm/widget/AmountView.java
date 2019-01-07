package com.cjwsc.idcm.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cjwsc.idcm.R;

/**
 * Created by Morning on 2017/8/17.
 * 自定义购物数量加减控件
 */

public class AmountView extends LinearLayout {

    private int mCurrentAmount = 1; //购买数量
    private int mMaxAmount = 1; //总量

    private EditText mEtAmount;
    private ImageButton mImgAdd;
    private ImageButton mImgMinus;
    private boolean mIsAutoAdd = true;
    private IAmountCountCallback mIAmountCountCallback;

    public void setCurrentAmount(int currentAmount) {
        mCurrentAmount = currentAmount;
        mEtAmount.setText("" + mCurrentAmount);
    }

    public void setMaxAmount(int maxAmount) {
        mMaxAmount = maxAmount;
    }

    public void setUnClickAddOrSub(boolean b) {
        if (b) {
            mImgAdd.setClickable(false);
            mImgMinus.setClickable(false);
        }
    }

    public boolean isAutoAdd() {
        return mIsAutoAdd;
    }

    public void setAutoAdd(boolean autoAdd) {
        mIsAutoAdd = autoAdd;
    }

    public interface IAmountCountCallback {
        void onAmountCount(int amount, boolean isAdd);
    }

    public AmountView(Context context) {
        this(context, null);
    }

    public void setListener(IAmountCountCallback callback) {
        mIAmountCountCallback = callback;
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_amout, this);
        mEtAmount = (EditText) findViewById(R.id.et_amount);
        mImgMinus = (ImageButton) findViewById(R.id.img_minus);
        mImgAdd = (ImageButton) findViewById(R.id.img_add);

        mImgAdd.setOnClickListener(view -> {
            if (mCurrentAmount < mMaxAmount) {
                if (mIsAutoAdd) {
                    mCurrentAmount++;
                    mEtAmount.setText("" + mCurrentAmount);
                }
                if (mIAmountCountCallback != null) {
                    mIAmountCountCallback.onAmountCount(mCurrentAmount, true);
                }
            }
        });

        mImgMinus.setOnClickListener(view -> {
            if (mCurrentAmount > 1) {
                mCurrentAmount--;
                mEtAmount.setText("" + mCurrentAmount);
                if (mIAmountCountCallback != null) {
                    mIAmountCountCallback.onAmountCount(mCurrentAmount, false);
                }
            }
        });

        mEtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean isAdd;
                int num = 0;
                if (!TextUtils.isEmpty(editable.toString())) {
                    num = Integer.parseInt(editable.toString());
                }

                if (num == 1) {
                    mImgMinus.setBackgroundResource(R.mipmap.ic_amount_minus_disable);
                    if (num == mMaxAmount) {
                        mImgAdd.setBackgroundResource(R.mipmap.ic_amount_add_disable);
                    } else {
                        mImgAdd.setBackgroundResource(R.mipmap.ic_amount_add_enable);
                    }
                } else if (num == mMaxAmount) {
                    mImgAdd.setBackgroundResource(R.mipmap.ic_amount_add_disable);
                    mImgMinus.setBackgroundResource(R.mipmap.ic_amount_minus_enable);
                } else {
                    mImgMinus.setBackgroundResource(R.mipmap.ic_amount_minus_enable);
                    mImgAdd.setBackgroundResource(R.mipmap.ic_amount_add_enable);
                }

                if (num == mCurrentAmount) {
                    return;
                }

                if (num > 0) {
                    if (num > mMaxAmount) {
                        num = mMaxAmount;
                    }
                    isAdd = num > mCurrentAmount;
                    mCurrentAmount = num;
                    if (mIAmountCountCallback != null) {
                        mIAmountCountCallback.onAmountCount(mCurrentAmount, isAdd);
                    }
                }
            }
        });
    }


    public int getAmountCount(){

        return mCurrentAmount;

    }
}