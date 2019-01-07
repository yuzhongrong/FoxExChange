package com.cjwsc.idcm.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuzhongrong on 2017/8/23.
 */

public class PhoneNumberUtil {


    /**
     * 验证手机号是否正确1
     *
     * @param mobiles
     * @return 合法返回true 不合法返回false
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("1[34578]\\d{9}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 验证手机号是否正确2（推荐）
     *
     * @param mobiles
     * @return 合法返回true 不合法返回false
     */
    public static boolean checkMobileNO(String mobiles) {
//        String str = "^1[3|4|5|7|8][0-9]\\d{8}$";
        String regex = "^(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$";
        Pattern ps = Pattern.compile(regex);
        Matcher ms = ps.matcher(mobiles);
        return ms.matches();
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String getSafePhone(String phone){
        StringBuilder builder = new StringBuilder();
        builder.append(phone.substring(0, 3)).append("*****").append(phone.substring(8, 11));
        return builder.toString();
    }

    public static boolean isValieMoneyPs(String moneyPS){
        String regex = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%^&*?]{8,20}$";
        Pattern moneyPs = Pattern.compile(regex);
        Matcher ms = moneyPs.matcher(moneyPS);
        return ms.matches();
    }

}
