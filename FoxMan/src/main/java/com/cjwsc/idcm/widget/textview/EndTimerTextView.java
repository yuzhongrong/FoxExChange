package com.cjwsc.idcm.widget.textview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.cjwsc.idcm.R;

import java.util.concurrent.TimeUnit;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yuzhongrong on 2018/5/10.
 * 实现倒计时+动态设置颜色
 */

public abstract class EndTimerTextView extends android.support.v7.widget.AppCompatTextView {

    private long vartime;
    private Disposable disposable;

    public EndTimerTextView(Context context) {
        super(context);
    }

    public EndTimerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化一个定时器
    }

    public void setVarTime(long text, EndCallBack endtime) {
        this.vartime = text;
        this.endCallBack = endtime;
        disposable = startTimer(text);
    }

    private Disposable startTimer(long count) {

        return Flowable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                .take(count)
                .map(aLong -> count - (aLong + 1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {

                    setText( getCustomerText()+"("+aLong + "s"+")");
                    //再去做0秒的时候的处理
                    if (aLong == 30) {//30秒设置自身为红色
                   //     post(() -> setTextColor(getResources().getColor(R.color.color_ffffff)));
                    } else if (aLong == 0) {
                        endCallBack.endTimer();
                    }
                 //   setText(aLong + "s");


                });
    }

    //自己有个接口时间到的时候回调
    private EndCallBack endCallBack;

    public interface EndCallBack {
        void endTimer();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (disposable != null) disposable.dispose();
        super.onDetachedFromWindow();
    }

    public abstract String getCustomerText();

}
