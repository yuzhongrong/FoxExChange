/**
 * @项目名称:友德医网络医院(Android医生端)
 * @(#)LoginBean.java Created on 2015-9-11
 * Copyright © 2015 深圳友德医科技有限公司  版权所有
 */
package com.cjwsc.idcm.widget.password;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.cjwsc.idcm.R;


/**
 * The class <code>Class PasswordsEditText</code>.
 *
 * @author Tonghu Lei
 * @version 1.0
 */
@SuppressLint("AppCompatCustomView")
public class PasswordsEditText extends EditText implements
        TextWatcher, OnFocusChangeListener {

    // 删除按钮的引用
    private Drawable mClearDrawable;
    private boolean showPassword = false;
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (source.equals(" ")) {
                return "";
            } else {
                return null;
            }
        }
    };

    public PasswordsEditText(Context context) {
        this(context, null);
    }

    public PasswordsEditText(Context context, AttributeSet attrs) {
        // 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public PasswordsEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 晃动动画
     *
     * @param counts
     *            1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    private void init() {
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        /*mClearDrawable = getCompoundDrawables()[2];
		if (mClearDrawable == null) {
			mClearDrawable = getResources().getDrawable(
					R.drawable.show_password);
		}
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
				mClearDrawable.getIntrinsicHeight());*/
        setDrawableRight(true);
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        setFilters(new InputFilter[]{filter});
    }

    private void setDrawableRight(boolean showPassword) {
        mClearDrawable = getCompoundDrawables()[2];
        if (!showPassword) {//密码显示图标
            if (mClearDrawable == null) {
                mClearDrawable = getResources().getDrawable(
                        R.drawable.show_password);
            }
            mClearDrawable = getResources().getDrawable(
                    R.drawable.show_password);
        } else {//密码不显示图标
//			if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(
                    R.drawable.hide_password);
//			}
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                mClearDrawable.getIntrinsicHeight());

    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
     * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean touchable = event.getX() > (getWidth()
                        - getPaddingRight() - mClearDrawable
                        .getIntrinsicWidth())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
//					this.setText("");
                    this.cancelLongPress();//避免彈出 長按出現的時間
                    if (showPassword) {//當前顯示密碼，需隱藏
                        changeEditInputType(false);
                        showPassword = false;
                        setDrawableRight(true);
                    } else {
                        changeEditInputType(true);
                        showPassword = true;
                        setDrawableRight(false);
                    }
                    setCompoundDrawables(getCompoundDrawables()[0],
                            getCompoundDrawables()[1], mClearDrawable, getCompoundDrawables()[3]);
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void changeEditInputType(boolean showPassword) {
        if (showPassword) {
            setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        setSelection(this.length());
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
//			listener.hideSideBar();
//			setClearIconVisible(getText().length() > 0);
            this.setBackgroundDrawable(null);
        } else {
//			listener.showSideBar();
//			setClearIconVisible(false);
            this.setBackgroundDrawable(null);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        setClearIconVisible(s.length() > 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }

    public void setFocusListener(FocusListener listener) {
    }

    /**
     * The listener interface for receiving focus events.
     * The class that is interested in processing a focus
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addFocusListener<code> method. When
     * the focus event occurs, that object's appropriate
     * method is invoked.
     *
     */
    public interface FocusListener {
        void showSideBar();

        void hideSideBar();
    }
}
