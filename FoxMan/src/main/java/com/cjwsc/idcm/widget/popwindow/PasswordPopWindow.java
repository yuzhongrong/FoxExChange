package com.cjwsc.idcm.widget.popwindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.widget.password.OnPasswordInputFinish;
import com.cjwsc.idcm.widget.password.PasswordView;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Morning on 2017/8/17.
 */

public class PasswordPopWindow extends BasePopupWindow {
    private OnPasswordInputFinish mOnPasswordInputFinish;

    private PasswordView pwdView;
    public PasswordPopWindow(Activity context, OnPasswordInputFinish onPasswordInputFinish) {
        super(context);
        mOnPasswordInputFinish = onPasswordInputFinish;
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
        View root = createPopupById(R.layout.pop_enter_password);

        pwdView = (PasswordView) root.findViewById(R.id.pwd_view);

        //添加密码输入完成的响应
        pwdView.setOnFinishInput(new OnPasswordInputFinish() {
            @Override
            public void inputFinish(String password) {
                if (mOnPasswordInputFinish != null) {
                    mOnPasswordInputFinish.inputFinish(password);
                }
            }
        });

        // 监听X关闭按钮
        pwdView.getImgCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 监听键盘上方的返回
        pwdView.getVirtualKeyboardView().getLayoutBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return root;
    }
}
