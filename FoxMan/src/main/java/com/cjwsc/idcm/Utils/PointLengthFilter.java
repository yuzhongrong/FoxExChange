package com.cjwsc.idcm.Utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 *
 * @project name : IDCM2.1
 * @class name :   PointLengthFilter
 * @package name : com.cjwsc.idcm.Utils
 * @author :       MayerXu10000@gamil.com
 * @date :         2018/2/22 14:51
 * @describe :     TODO
 *
 */
public class PointLengthFilter implements InputFilter {

    /** 输入框小数的位数  保留二位小数*/
    private static final int DECIMAL_DIGITS = 2;

    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        // 删除等特殊字符，直接返回
        if ("".equals(source.toString())) {
            return null;
        }
        String dValue = dest.toString();
        String[] splitArray = dValue.split("\\.");
        if (splitArray.length > 1) {
            String dotValue = splitArray[1];
            int diff = dotValue.length() + 1 - DECIMAL_DIGITS;
            if (diff > 0) {
                return source.subSequence(start, end - diff);
            }
        }
        return null;
    }
}

