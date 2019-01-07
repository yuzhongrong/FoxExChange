package com.cjwsc.idcm.widget.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.InputType;
import com.cjwsc.idcm.Utils.LogUtil;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.cjwsc.idcm.R;

import java.lang.reflect.Method;

/**
 * Created by zhaopan on 2016/10/28.
 * e-mail: kangqiao610@gmail.com
 */

public class CustomKeyboardManager implements OnFocusChangeListener {
    private static final String TAG = "CustomKeyboardManager";

    private Context mContext;
    private ViewGroup mRootView;
    private FrameLayout mKeyboardViewContainer;
    private KeyboardView mKeyboardView;
    private int mKeyboardHeight;
    private FrameLayout.LayoutParams mKeyboardViewLayoutParams;
    private View mShowUnderView;
    private View etFocusScavenger;
    private CustomBaseKeyboard.CustomKeyStyle defaultCustomKeyStyle = new CustomBaseKeyboard.SimpleCustomKeyStyle();

    public CustomKeyboardManager(Activity activity) {
        mContext = activity;
        mRootView = (ViewGroup) (activity.getWindow().getDecorView().findViewById(android.R.id.content));

        mKeyboardViewContainer = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.view_custome_keyboard_view, null);
        mKeyboardView = (KeyboardView) mKeyboardViewContainer.findViewById(R.id.keyboard_view);
        etFocusScavenger = mKeyboardViewContainer.findViewById(R.id.et_focus_scavenger);
        hideSystemSoftKeyboard((EditText) etFocusScavenger);

        mKeyboardViewLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mKeyboardViewLayoutParams.gravity = Gravity.BOTTOM;
    }

    public void attachTo(EditText editText, CustomBaseKeyboard keyboard) {
        hideSystemSoftKeyboard(editText);
        editText.setTag(R.id.edittext_bind_keyboard, keyboard);
        if (null == keyboard.getCustomKeyStyle()) keyboard.setCustomKeyStyle(defaultCustomKeyStyle);
        editText.setOnFocusChangeListener(this);
    }

    private CustomBaseKeyboard getKeyboard(View view) {
        Object tag = view.getTag(R.id.edittext_bind_keyboard);
        if (null != tag && tag instanceof CustomBaseKeyboard) {
            return (CustomBaseKeyboard) tag;
        }
        return null;
    }

    private void refreshKeyboard(CustomBaseKeyboard keyboard) {
        mKeyboardView.setKeyboard(keyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(keyboard);
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mKeyboardView.measure(width, height);
        mKeyboardHeight = mKeyboardView.getMeasuredHeight();
    }

    public KeyboardView getKeyboardView() {
        return mKeyboardView;
    }

    /**
     * 替换焦点清理者, 即当控件失去焦点后, 焦点交由view来继续处理.
     *
     * @param view
     */
    public void requestFocus(View view) {
        etFocusScavenger = view;
    }

    public void setShowUnderView(View view) {
        mShowUnderView = view;
    }

    /**
     * 计算屏幕向上移动距离
     *
     * @param view 响应输入焦点的控件
     * @return 移动偏移量
     */
    private int getMoveHeight(View view) {
        Rect rect = new Rect();
        mRootView.getWindowVisibleDisplayFrame(rect); //获取当前显示区域的宽高

        int[] vLocation = new int[2];
        view.getLocationOnScreen(vLocation); //计算输入框在屏幕中的位置
        int keyboardTop = vLocation[1] + view.getHeight() + view.getPaddingBottom() + view.getPaddingTop();
        if (keyboardTop - mKeyboardHeight < 0) { //如果输入框到屏幕顶部已经不能放下键盘的高度, 则不需要移动了.
            return 0;
        }
        if (null != mShowUnderView) { //如果有基线View. 则计算基线View到屏幕的距离
            int[] underVLocation = new int[2];
            mShowUnderView.getLocationOnScreen(underVLocation);
            keyboardTop = underVLocation[1] + mShowUnderView.getHeight() + mShowUnderView.getPaddingBottom() + mShowUnderView.getPaddingTop();
        }
        //输入框或基线View的到屏幕的距离 + 键盘高度 如果 超出了屏幕的承载范围, 就需要移动.
        int moveHeight = keyboardTop + mKeyboardHeight - rect.bottom;
        return moveHeight > 0 ? moveHeight : 0;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof EditText) {
            EditText attachEditText = (EditText) v;
            if (hasFocus) {
                showSoftKeyboard(attachEditText);
            } else {
                hideSoftKeyboard(attachEditText);
            }
        }
    }

    public void showSoftKeyboard(EditText view) {
        CustomBaseKeyboard keyboard = getKeyboard(view); //获取输入框所绑定的键盘BaseKeyboard
        if (null == keyboard) {
            LogUtil.e(TAG, "The EditText no bind CustomBaseKeyboard!");
            return;
        }
        keyboard.setCurEditText(view);
        keyboard.setNextFocusView(etFocusScavenger); //为键盘设置下一个焦点响应控件.
        refreshKeyboard(keyboard); //设置键盘keyboard到KeyboardView中.

        //将键盘布局加入到根布局中.
        mRootView.addView(mKeyboardViewContainer, mKeyboardViewLayoutParams);
        //设置加载动画.
        mKeyboardViewContainer.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.down_to_up));

        int moveHeight = getMoveHeight(view);
        if (moveHeight > 0) {
            mRootView.getChildAt(0).scrollBy(0, moveHeight); //移动屏幕
        } else {
            moveHeight = 0;
        }

        view.setTag(R.id.keyboard_view_move_height, moveHeight);
    }

    public void hideSoftKeyboard(EditText view) {
        int moveHeight = 0;
        Object tag = view.getTag(R.id.keyboard_view_move_height);
        if (null != tag) moveHeight = (int) tag;
        if (moveHeight > 0) { //复原屏幕
            mRootView.getChildAt(0).scrollBy(0, -1 * moveHeight);
            view.setTag(R.id.keyboard_view_move_height, 0);
        }

        mRootView.removeView(mKeyboardViewContainer); //将键盘从根布局中移除.

        mKeyboardViewContainer.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.up_to_hide));
    }

    /**
     * 隐藏系统键盘
     *
     * @param editText
     */
    public static void hideSystemSoftKeyboard(EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
    }
}