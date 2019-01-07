package com.cjwsc.idcm.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.cjwsc.idcm.Utils.LogUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/11/26.
 */

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    public static boolean isBlank(String str) {

        return (str == null || str.trim().length() == 0);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    public static double StringToDouble(String s) {

        try {
            double d = Double.parseDouble(s);
            return d;

        } catch (Exception e) {
            e.getMessage();
        }
        return 0;

    }

    public static long StringToLong(String s) {
        try {

            long l = Long.parseLong(s.substring(0, s.length() - 4));

            return l;

        } catch (Exception e) {

        }
        return 0;
    }


    public static int StringToIntValue(String s) {

        try {
            return Integer.parseInt(s.replace(",", ""));
        } catch (Exception e) {
            return -1;
        }

    }

    public static String formatusd(double value) {
        try {
            //if (value < 0) value *= -1;
            return String.format("%,.0f", value);

        } catch (Exception e) {
            return "";
        }
    }

    public static String DoubleToStr(double d) {

        try {

            BigDecimal b = new BigDecimal(d);
            double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            return f1 + "";

        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    //StringToDouble StringToInt  DoubleToString DoubleToInt

/*
    public static String doubleToStr(String string) {
        try {
            DecimalFormat f = new DecimalFormat("#,###");
            return f.format(string);
        } catch (Exception e) {

        }
        return "";
    }
*/

    public static String formatting(Double value) {
        try {
            DecimalFormat f = new DecimalFormat("#,###");
            return f.format(value);
        } catch (Exception e) {

        }
        return "";
    }

    public static int StringTOInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            LogUtil.e("=====", e.getMessage());
        }

        return -1;
    }

    public static String formatRate(double value) {
        try {
            //if (value < 0) value *= -1;
            return String.format("%,.2f", value * 100) + "%";
        } catch (Exception e) {
            return "";
        }
    }

    public static String format(double value) {
        try {
            //if (value < 0) value *= -1;
            return String.format("%,.0f", value);

        } catch (Exception e) {
            return "";
        }
    }

    public static String format(Integer value) {
        try {
            //if (value < 0) value *= -1;
            return String.format("%,d", value);
        } catch (Exception e) {
            return value.toString();
        }
    }

    public static String formats(long l) {
        try {
            double d = (double) l;
            return String.format("%.1f", d / 1000);
        } catch (Exception e) {
            return "";
        }
    }

    public static String format(int value) {
        try {
            //if (value < 0) value *= -1;
            return String.format("%,d", value);
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatAbs(double value) {
        try {
            if (value < 0) value *= -1;
            return String.format("%,.0f", value);

        } catch (Exception e) {
            return "";
        }
    }

    public static String formatAbs(Integer value) {
        try {
            if (value < 0) value *= -1;
            return String.format("%,d", value);
        } catch (Exception e) {
            return value.toString();
        }
    }


    public static String formatAbs(int value) {
        try {
            if (value < 0) value *= -1;
            return String.format("%,d", value);
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isBigOrSmall(double value) {
        try {
            if (value < 0) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static double removeRow(double value) {
        try {
            String string = Double.toString(value);
            if (string.contains("-"))
                return Double.parseDouble(string.replaceAll("[-]", ""));
            else return value;
        } catch (Exception e) {
            LogUtil.d("====", e.getMessage());
        }
        return value;
    }

    public static String replaceAlls(String value) {
        try {
            if (!value.contains(",")) return value;
            else return value.replaceAll(",", "");
        } catch (Exception e) {
            return value;
        }
    }

    public static String getHashrate(long value) {


        return null;
    }

    public static String Number2Hangle(int lngNumber) {
        boolean UseDecimal = false;
        String Sign = "";
        int i = 0;
        int Level = 0;

        String[] NumberChar = new String[]{"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        String[] LevelChar = new String[]{"", "십", "백", "천"};
        String[] DecimalChar = new String[]{"", "만", "억 ", "조 ", "경 "};

        String strValue = lngNumber + "";
        String NumToKorea = Sign;
        UseDecimal = false;

        for (i = 0; i < strValue.length(); i++) {
            Level = strValue.length() - i;
            //Console.WriteLine("Level : " + Level);

            // 0 이 아니면 숫자에 대한 한글이 붙어야 한다.
            if (strValue.substring(i, i + 1).equals("0") == false) {
                UseDecimal = true;

                // 천단위 숫자이면
                if (((Level - 1) % 4) == 0) {
                    NumToKorea = NumToKorea + NumberChar[Integer.parseInt(strValue.substring(i, i + 1))] + DecimalChar[(Level - 1) / 4];
                    UseDecimal = false;
                } else // 천단위가 아니라면
                {
                    if (strValue.substring(i, i + 1).equals("1")) {
                        NumToKorea = NumToKorea + LevelChar[(Level - 1) % 4];
                    } else {
                        NumToKorea = NumToKorea + NumberChar[Integer.parseInt(strValue.substring(i, i + 1))] + LevelChar[(Level - 1) % 4];
                    }
                }
            } else // 0 이라면
            {
                // 만단위라면 만단위 한글이 붙어야 한다.
                if ((Level % 4 == 0) && UseDecimal) {
                    NumToKorea = NumToKorea + DecimalChar[Level / 4];
                    UseDecimal = false;
                }
            }
        }
        return NumToKorea;
    }

    public static String division(int a, int b) {
        String result = "";
        float num = (float) a / b;

        DecimalFormat df = new DecimalFormat("0.0");

        result = df.format(num);

        return result;

    }

    public static String formatDouble(double d) {
        try {

            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatDoubleFour(double d) {
        try {

            DecimalFormat df = new DecimalFormat("0.0000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatDouble(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.0000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatDoubleString(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatProgress(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.00000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatdouble(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.0000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatDDDouble(double d) {
        try {

            DecimalFormat df = new DecimalFormat("0.000000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatdoublebb(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.0000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatdoublaa(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.00000000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatdoublecc(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatdoub(String str) {
        try {
            double d = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatddouble(double d) {
        try {
            DecimalFormat df = new DecimalFormat("0.00000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatIntDouble(double d) {
        try {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatRateDouble(double d) {
        try {
            DecimalFormat df = new DecimalFormat("0.0000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatEnableDouble(double d) {
        try {
            DecimalFormat df = new DecimalFormat("0.00000");
            return df.format(d);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatUnit(String str) {
        try {
            return str.substring(str.length() - 4, str.length());
        } catch (Exception e) {
            return null;
        }
    }




    /*public static String DoubleToInt(String string) {
        try {
            StringBuffer newPrice = new StringBuffer();
            newPrice.append(string);
            int i = newPrice.length();
            if (i > 3) {
                for (int j = i - 3; j > 0; j = j - 3) {
                    newPrice.insert(j, ",");
                }
                return newPrice.toString();
            } else
                return string;

        } catch (Exception e) {

        }
        return string;
    }*/

    public static String[] SizeSuffixes = {"H/s", "KH/s", "MH/s", "GH/s", "TH/s", "PH/s", "EH/s", "ZH/s", "YH/s"};

    public static String SizeHash(long value) {
        try {
            int i = (String.valueOf(value).length() - 1) / 3;
            String unit = SizeSuffixes[i];
            if (i == 0) {
                return value + unit;
            } else if (i == 1) {
                return value / 1000 + unit;
            } else if (i == 2) {
                return value / 1000000 + unit;
            } else if (i == 3) {
                return value / 1000000000 + unit;
            } else if (i == 4) {
            }
            long a = Long.MAX_VALUE;
        } catch (Exception e) {

        }
        return null;
    }

    public static long comparisonUnit(long l, String unit) {

        try {

            if (TextUtils.isEmpty(unit)) return 0;
            if ("KH/s".equals(unit)) {
                return l / 1000;
            } else if ("MH/s".equals(unit)) {
                return l;
            } else if ("GH/s".equals(unit)) {
                return l * 1000;
            } else if ("TH/s".equals(unit)) {
                return l * 1000000;
            } else if ("PH/s".equals(unit)) {
                return l * 1000000000;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 更严格的判断
     *
     * @param
     * @return
     */
    public static boolean isMobileNum(String telNum) {
        Pattern p = Pattern.compile("^[1][3578][0-9]{9}$");
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static final String REGEX_NAME = "^[0-9a-zA-Z]{4,16}$";

    public static final String REGEX_PASS = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    public static String formatTosepara(String data) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(data);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public static boolean isUserName(String name) {
        return Pattern.matches(REGEX_NAME, name);
    }

    public static boolean isPass(String pass) {
        return Pattern.matches(REGEX_PASS, pass);
    }

    public static String getCommaFour(BigDecimal value) {
        //return getFormat(",###,###,###,###,###,###.##", value);
        return String.format(Locale.ENGLISH, getFormat("##0.0000", value),value);
    }

    public static String getCommaFormatTwo(BigDecimal value) {
        return getFormat(",###,###,###,###,###,###,###,###,###,###,###,##0.00", value);
    }

    public static String getCommaFormatFourth(BigDecimal value) {
        return getFormat(",###,###,###,###,###,###,###,###,###,###,###,##0.0000", value);
    }

    public static String getFormat(String style, BigDecimal value) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(style);// 将格式应用于格式化器
        return df.format(value.doubleValue());
    }

    public static String getCommaFormatFour(BigDecimal value) {
        return getFormatFour(",###.####", value);
    }

    public static String getCommaFormatEight(BigDecimal value) {
        return getFormatFour(",###,###,###,###.########", value);
    }

    public static String getFormatFour(String style, BigDecimal value) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(style);// 将格式应用于格式化器
        return df.format(value.doubleValue());
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String formatChangeOne(double value) {//保留1位小数：四舍五入
        return String.format(Locale.ENGLISH, "%,.1f", value);
    }

    //String strValue = String.format(Locale.ENGLISH, "%,.2f", historyList.get(0).getMoney());
    public static String formatChangeTwo(double value) {//保留两位小数：四舍五入
        return String.format(Locale.ENGLISH, "%,.2f", value);
    }

    public static String formatChangeFour(double value) {
        return String.format(Locale.ENGLISH, "%,.4f", value);
    }

    public static String formatChangEight(double value) {
        return String.format(Locale.ENGLISH, "%,.8f", value);
    }


    public static String formatChangeInt(Integer value) {
        try {
            //if (value < 0) value *= -1;
            return String.format(Locale.ENGLISH, "%,d", value);
        } catch (Exception e) {
            return value.toString();
        }
    }

    public static String formatAbsTwo(double value) {
        try {
            //if (value < 0) value *= -1;
            return String.format("%,.2f", value * 100) + "%";

        } catch (Exception e) {
            return "";
        }
    }

    public static String formatAbsFour(double value) {
        try {
            if (value < 0) value *= -1;
            return String.format("%,.4f", value);

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 截取，不足补零
     *
     * @param amount
     * @return
     */
    public static String toSubStringDegist(double amount, int degist) {
        String amountResult = toNormal(String.valueOf(amount));
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder(split[0]);
            if (split.length > 1 && split[1].length() > degist - 1) {
                stringBuilder.append(".").append(split[1].substring(0, degist));
                return stringBuilder.toString();
            } else if (split.length > 1) {
                stringBuilder.append(".").append(split[1]);
                for (int i = 0; i < (degist) - split[1].length(); i++) {
                    stringBuilder.append("0");
                }
                return toNormal(stringBuilder.toString());
            } else {
                return amountResult;
            }
        } else {
            return amountResult;
        }
    }


    public static String toNormal(String amount) {
        BigDecimal decimalFormat = new BigDecimal(amount);
        return decimalFormat.toPlainString();
    }

    public StringBuffer change(double d) {
        StringBuffer str = new StringBuffer(String.valueOf(d));        //将d转化为String类型
        for (int i = str.indexOf(".") - 3; i > 0; i = i - 3) {                                       //找到小数点的位置，从小数点从右往左遍历
            str.insert(i, ',');                                                                         //每隔三位插入一个逗号
        }
        return str;
    }

    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static String formatD(double d) {
        try {
            final DecimalFormat formater = new DecimalFormat("0.000000");
//            formater.setMaximumFractionDigits(5);
//            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.DOWN);
            String s = formater.format(d);
            return s;
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatDouFour(double value) {
        //Locale
        return String.format(Locale.ENGLISH, "%,.4f", (Math.floor(value * (double) 10000)) / (double) 10000);
    }


    public static String formatDouTwo(double value) {
        //Locale
        return String.format(Locale.ENGLISH, "%,.2f", (Math.floor(value * (double) 100)) / (double) 100);
    }

    public static String formatDouEight(double value) {
        //Locale
        return String.format(Locale.ENGLISH, "%,.6f", (Math.floor(value * (double) 1000000)) / (double) 1000000);
    }
    //return String.format(Locale.ENGLISH, "%,.4f", value);

    //登录密码8～20位，且字母、数字、符号等任意2种以上组合
    public static boolean checkPasswordVaild(String psw){
//        Pattern  p = Pattern.compile("/(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%^&*?]{8,20}$/");//建立模式对象
        Pattern  p = Pattern.compile("^(?![a-zA-Z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,20}$");//建立模式对象
        boolean  matches = p.matcher(psw)
                            .matches();
        return matches;
    }


    /**
     * 处理获取字段为null 问题
     */
    public static String handlerNull(String str){

        if(TextUtils.isEmpty(str)){
            return "- - - -";
        }
        return str;


    }

}
