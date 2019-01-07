package com.cjwsc.idcm.Utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cjwsc.idcm.R;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;

/**
 *
 * @project name : IDCM2.1
 * @class name :   EditTextUtils
 * @package name : com.cjwsc.idcm.Utils
 * @author :       MayerXu10000@gamil.com
 * @date :         2018/1/29 14:07
 * @describe :     EditText 点击右侧叉图清楚输入栏，输入改变按钮颜色
 *
 */
public class EditTextUtils {
    /**
     * 点击右侧叉图清楚输入栏，输入改变按钮颜色
     * @param context
     * @param et
     * @param cleanImg
     * @param btn
     */
    public static void clearButtonListenerWithBtn(Context context,
                                                  EditText et,
                                                  View cleanImg,
                                                  View btn)
    {
        //获取输入栏的文本
        Editable etTextContent = et.getText();
        if (TextUtils.isEmpty(etTextContent)) {
            cleanImg.setVisibility(View.INVISIBLE);
            btn.setBackground(context.getResources()
                                     .getDrawable(R.drawable.shap_round_sololid_2e3a4b));
        } else {
            cleanImg.setVisibility(View.VISIBLE);
            btn.setBackground(context.getResources()
                                     .getDrawable(R.drawable.shap_round_sololid_2e7fd0));
        }

        //点击输入栏右侧图清楚文本
        cleanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
                et.requestFocus();
            }
        });

        //对et的输入进行监听
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    cleanImg.setVisibility(View.INVISIBLE);
                    btn.setBackground(context.getResources()
                                             .getDrawable(R.drawable.shap_round_sololid_2e3a4b));
                } else {
                    cleanImg.setVisibility(View.VISIBLE);
                    btn.setBackground(context.getResources()
                                             .getDrawable(R.drawable.shap_round_sololid_2e7fd0));
                }
            }
        });

        //Edit没有获取焦点移除叉号
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanImg.setVisibility(View.INVISIBLE);
                } else {
                    if (!TextUtils.isEmpty(((EditText) view).getText())) {
                        cleanImg.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 点击右侧叉图清除输入栏
     * @param et
     * @param cleanImg
     */
    public static void clearButtonListener(EditText et, View cleanImg) {
        //获取输入栏的文本
        Editable etTextContent = et.getText();
        if (TextUtils.isEmpty(etTextContent)) {
            cleanImg.setVisibility(View.INVISIBLE);
        } else {
            cleanImg.setVisibility(View.VISIBLE);
        }

        //点击输入栏右侧图清楚文本
        cleanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
                et.requestFocus();
            }
        });

        //对et的输入进行监听
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    cleanImg.setVisibility(View.INVISIBLE);
                } else {
                    cleanImg.setVisibility(View.VISIBLE);
                }
            }
        });
        //Edit没有获取焦点移除叉号
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    cleanImg.setVisibility(View.INVISIBLE);
                } else {
                    if (!TextUtils.isEmpty(((EditText) view).getText())) {
                        cleanImg.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 联动改变Button颜色
     * @param context
     * @param mLoginTel edittext
     * @param btn       Button
     */
    public static void combineButtonOne(Context context, EditText mLoginTel, final View btn)
    {
        Observable<CharSequence> ObservableEmail    = RxTextView.textChanges(mLoginTel);
        Observable<CharSequence> ObservablePassword = RxTextView.textChanges(mLoginTel);
        Observable.combineLatest(ObservableEmail,
                                 ObservablePassword,
                                 new BiFunction<CharSequence, CharSequence, Boolean>() {
                                     @Override
                                     public Boolean apply(CharSequence charSequence,
                                                          CharSequence charSequence2)
                                             throws Exception
                                     {
                                         if (!TextUtils.isEmpty(charSequence.toString())) {
                                             return true;
                                         }
                                         return false;
                                     }
                                 })
                  .subscribe(new Observer<Boolean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Boolean aBoolean) {
                          if (aBoolean) {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e7fd0));
                          } else {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e3a4b));
                          }
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }

    /**
     * 联动改变Button颜色
     * @param context
     * @param mLoginTel       edittext
     * @param btn             Button
     * @param res_check       颜色
     * @param unCheck         颜色
     */
    public static void combineButtonOne(Context context, EditText mLoginTel, final View btn, int res_check, int unCheck)
    {
        Observable<CharSequence> ObservableEmail    = RxTextView.textChanges(mLoginTel);
        Observable<CharSequence> ObservablePassword = RxTextView.textChanges(mLoginTel);
        Observable.combineLatest(ObservableEmail,
                                 ObservablePassword,
                                 new BiFunction<CharSequence, CharSequence, Boolean>() {
                                     @Override
                                     public Boolean apply(CharSequence charSequence,
                                                          CharSequence charSequence2)
                                             throws Exception
                                     {
                                         if (!TextUtils.isEmpty(charSequence.toString())) {
                                             return true;
                                         }
                                         return false;
                                     }
                                 })
                  .subscribe(new Observer<Boolean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Boolean aBoolean) {
                          if (aBoolean) {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(res_check));
                          } else {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(unCheck));
                          }
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }


    /**
     * 联动改变Button颜色 2个EditText
     * @param context
     * @param mLoginTel edittext
     * @param mPsEdTv   edittext
     * @param btn       Button
     */
    public static void combineButton(Context context,
                                     EditText mLoginTel,
                                     EditText mPsEdTv,
                                     final View btn)
    {
        Observable<CharSequence> ObservableEmail    = RxTextView.textChanges(mLoginTel);
        Observable<CharSequence> ObservablePassword = RxTextView.textChanges(mPsEdTv);
        Observable.combineLatest(ObservableEmail,
                                 ObservablePassword,
                                 new BiFunction<CharSequence, CharSequence, Boolean>() {
                                     @Override
                                     public Boolean apply(CharSequence charSequence,
                                                          CharSequence charSequence2)
                                             throws Exception
                                     {
                                         if (!TextUtils.isEmpty(charSequence.toString()) && !TextUtils.isEmpty(
                                                 charSequence2.toString()))
                                         { return true; }
                                         return false;
                                     }
                                 })
                  .subscribe(new Observer<Boolean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Boolean aBoolean) {
                          if (aBoolean) {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e7fd0));
                              btn.setEnabled(true);
                          } else {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e3a4b));
                              btn.setEnabled(false);
                          }
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }


    /**
     * 联动改变Button颜色 2个EditText
     * @param context
     * @param mLoginTel edittext
     * @param mPsEdTv   edittext
     * @param btn       Button
     */
    public static void combineButtonThree(Context context,
                                          EditText mLoginTel,
                                          EditText mPsEdTv,
                                          EditText mPsEdTv2,
                                          final View btn)
    {
        Observable<CharSequence> ObservableEmail     = RxTextView.textChanges(mLoginTel);
        Observable<CharSequence> ObservablePassword  = RxTextView.textChanges(mPsEdTv);
        Observable<CharSequence> ObservablePassword2 = RxTextView.textChanges(mPsEdTv2);
        Observable.combineLatest(ObservableEmail,
                                 ObservablePassword,
                                 ObservablePassword2,
                                 new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
                                     @Override
                                     public Boolean apply(CharSequence charSequence,
                                                          CharSequence charSequence2,
                                                          CharSequence charSequence3)
                                             throws Exception
                                     {
                                         if (!TextUtils.isEmpty(charSequence.toString()) && !TextUtils.isEmpty(
                                                 charSequence2.toString()) && !TextUtils.isEmpty(
                                                 charSequence3.toString()))
                                         { return true; }
                                         return false;
                                     }
                                 })
                  .subscribe(new Observer<Boolean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Boolean aBoolean) {
                          if (aBoolean) {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e7fd0));
                          } else {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e3a4b));
                          }
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }

    public static void combineButtonFour(Context context,
                                         EditText et1,
                                         EditText et2,
                                         EditText et3,
                                         EditText et4,
                                         final View btn)
    {
        Observable<CharSequence> Observable1 = RxTextView.textChanges(et1);
        Observable<CharSequence> Observable2 = RxTextView.textChanges(et2);
        Observable<CharSequence> Observable3 = RxTextView.textChanges(et3);
        Observable<CharSequence> Observable4 = RxTextView.textChanges(et4);
        Observable.combineLatest(Observable1,
                                 Observable2,
                                 Observable3,
                                 Observable4,
                                 new Function4<CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
                                     @Override
                                     public Boolean apply(CharSequence charSequence,
                                                          CharSequence charSequence2,
                                                          CharSequence charSequence3,
                                                          CharSequence charSequence4)
                                             throws Exception
                                     {

                                         if (!TextUtils.isEmpty(charSequence.toString()) && !TextUtils.isEmpty(
                                                 charSequence2.toString()) && !TextUtils.isEmpty(
                                                 charSequence3.toString()) && !TextUtils.isEmpty(
                                                 charSequence4.toString()))
                                         { return true; }
                                         return false;
                                     }
                                 })
                  .subscribe(new Observer<Boolean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Boolean aBoolean) {
                          if (aBoolean) {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e7fd0));
                          } else {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e3a4b));
                          }
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }

    /**
     * 四个输入栏和一个复选框都选中才变色
     * @param context
     * @param et1
     * @param et2
     * @param et3
     * @param et4
     * @param cb_user
     * @param btn
     */
    public static void combineButtonFive(Context context,
                                         EditText et1,
                                         EditText et2,
                                         EditText et3,
                                         EditText et4,
                                         CheckBox cb_user,
                                         View btn){

        Observable<CharSequence> Observable1 = RxTextView.textChanges(et1);
        Observable<CharSequence> Observable2 = RxTextView.textChanges(et2);
        Observable<CharSequence> Observable3 = RxTextView.textChanges(et3);
        Observable<CharSequence> Observable4 = RxTextView.textChanges(et4);
                Observable<Boolean> checkBox = RxCompoundButton.checkedChanges(cb_user);

        Observable.combineLatest(Observable1,
                                 Observable2,
                                 Observable3,
                                 Observable4,
                                 checkBox,
                                 new Function5<CharSequence, CharSequence, CharSequence, CharSequence,Boolean, Boolean>() {
                                     @Override
                                     public Boolean apply(CharSequence charSequence1,
                                                          CharSequence charSequence2,
                                                          CharSequence charSequence3,
                                                          CharSequence charSequence4,
                                                          Boolean aBoolean)
                                             throws Exception
                                     {
                                         if (!TextUtils.isEmpty(charSequence1.toString()) && !TextUtils.isEmpty(
                                                 charSequence2.toString()) && !TextUtils.isEmpty(
                                                 charSequence3.toString()) && !TextUtils.isEmpty(
                                                 charSequence4.toString()) && aBoolean)
                                         { return true; }
                                         return false;
                                     }

                                 })
                  .subscribe(new Observer<Boolean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Boolean aBoolean) {
                          if (aBoolean) {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e7fd0));
                          } else {
                              btn.setBackground(context.getResources()
                                                       .getDrawable(com.cjwsc.idcm.R.drawable.shap_round_sololid_2e3a4b));
                          }
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }
}
