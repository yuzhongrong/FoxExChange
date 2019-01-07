package com.cjwsc.idcm.Utils;

import android.widget.TextView;

import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.base.BaseActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by yuzhongrong on 2018/2/6.
 */

public class RxTimerUtil {

    private static Disposable mDisposable;

    /**
     * milliseconds毫秒后执行next操作
     *    *
     *    * @param milliseconds
     *    * @param next
     *    
     */
    public static void timer(long milliseconds,TimeUnit unit, final IRxNext next) {
        Observable.timer(milliseconds, unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }


    /**
     * 每隔milliseconds毫秒后执行next操作
     *    *
     *    * @param milliseconds
     *    * @param next
     *    
     */
    public static void interval(long milliseconds, final IRxNext next) {
        Observable.interval(milliseconds, TimeUnit.MINUTES)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }

    /**封装获取短信验证码倒计时60秒*/

    public static void startTime(BaseActivity activity,final TextView tvGetCode) {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .take(count + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tvGetCode.setEnabled(false);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long num) {

                tvGetCode.setText("剩余" + num + "秒");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //回复原来初始状态
                tvGetCode.setEnabled(true);
                tvGetCode.setText("获取验证码");
            }
        });

    }



    /**
     *    * 取消订阅
     *    
     */
    public static void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            LogUtil.e("====定时器取消======", "取消");
        }
    }


    public interface IRxNext {
        void doNext(long number);
    }

}
