package com.cjwsc.idcm.Utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;



import java.math.BigDecimal;

/**
 * Created by ASUS1 on 2017/8/24.
 * 价格处理工具类
 */

public class PriceUtil {
    public static String getDiscount2(Double str1) {
        BigDecimal decimal = new BigDecimal(str1);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return decimal + "";
    }

    public static String getDiscount0(Double str1) {
        BigDecimal decimal = new BigDecimal(str1);
        decimal = decimal.setScale(0, BigDecimal.ROUND_HALF_UP);
        return decimal + "";
    }

    /**
     * 去掉小数点后多余的0
     * ex: X.00->X
     *
     * @param num
     * @return
     */
    public static String subZeroAndDot(double num) {
        if (num % 1 == 0) {
            return String.valueOf((int) num);
        } else {
            return String.valueOf(num);
        }
    }

    public static void setPricePoint(final EditText editText,double maxprice) {


        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                LogUtil.i("--Editable-s------>"+s.toString());
                String inputstr=s.toString();

                if(TextUtils.isEmpty(inputstr)) return;

                if(inputstr.equals(".")&&inputstr.indexOf(".")==0){//第一位输入.直接清空
                    editText.setText("");
                    return;
                }

               String inputmoney= PriceUtil.getDiscount2(Double.parseDouble(inputstr));
                double inputdouble=Double.parseDouble(inputmoney);


                if(inputdouble>maxprice){

                    editText.setText(String.valueOf(maxprice));
                    editText.setSelection(editText.getText().length());
                }

            }
        });
    }

}
