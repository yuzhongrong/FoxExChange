package com.cjwsc.idcm.widget.edittext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by yuzhongrong on 2017/9/20.
 */

@SuppressLint("AppCompatCustomView")
public class EndEditText extends EditText {
    public EndEditText(Context context) {
        super(context);
    }

    public EndEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EndEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if(selStart==selEnd){//防止不能多选
            setSelection(getText().length());
        }

    }
}
