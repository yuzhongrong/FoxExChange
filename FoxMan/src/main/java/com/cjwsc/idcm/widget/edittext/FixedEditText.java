package com.cjwsc.idcm.widget.edittext;

/**left fix text  **/
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class FixedEditText extends EditText {
    private String fixedText;
    private View.OnClickListener mListener;
    private int leftPadding;
    private int rightPadding;


    public FixedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLeftFixedText(String text) {
        fixedText = text;
        leftPadding = getPaddingLeft();
        int left = (int) getPaint().measureText(fixedText) + leftPadding;
        setPadding(left, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    public void setRightFixedText(String text) {
        fixedText = text;
        rightPadding = getPaddingRight();
//        int right = (int) getPaint().measureText(fixedText) + rightPadding;

        int right=getWidth()-(int) getPaint().measureText(fixedText);
        setPadding(right, getPaddingTop(), getWidth(), getPaddingBottom());
        invalidate();
    }

    public void setDrawableClick(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(fixedText)) {
            canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
//            通过下面的代码，可以查看出文字的基线，以及view的中线
//            Paint p = new Paint();
//            p.setStrokeWidth(1);
//            p.setColor(Color.parseColor("#ff0000"));
//            canvas.drawLine(0, getBaseline(), getMeasuredWidth(), getBaseline(), p);
//            canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mListener != null && getCompoundDrawables()[2] != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int i = getMeasuredWidth() - getCompoundDrawables()[2].getIntrinsicWidth();
                    if (event.getX() > i) {
                        mListener.onClick(this);
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }


}
