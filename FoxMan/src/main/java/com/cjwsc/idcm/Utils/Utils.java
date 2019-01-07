package com.cjwsc.idcm.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.cjwsc.idcm.Utils.LogUtil;

import com.blankj.utilcode.constant.RegexConstants;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *
 */

public class Utils {

    private static int DEF_DIV_SCALE = 2;
    private static String sResult;

    /**
     * 获取手机号码的星号形式
     *
     * @param pNumber
     * @return
     */
    public static String formatPhoneNO(String pNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return null;
        }

    }

    public static String formatCardNO(String pNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 8 && i <= 12) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return null;
        }

    }

    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    // TODO: 2016/11/26 总感觉怪怪的，有问题，需要好好看看，尤其是一些异常需要及时捕获
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取 yyyy-MM-dd 格式的日期
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 将double精确到小数点后一位
     *
     * @param value
     * @return
     */
    public static String formatDouble1(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
        return "" + decimal;
    }

    /**
     * 将double精确到小数点后两位
     *
     * @param value
     * @return
     */
    public static String formatDouble2(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return "" + decimal;
    }

    /**
     * 乘机
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    //加密规则 太过于复杂 两层加密
//    public static String pwdTwoLayerEncryption(String pwd){
//        if(TextUtils.isEmpty(pwd))return "";
//        String newpwd= pwd;
//        String three=newpwd.substring(0,3);
//        String afterthree=newpwd.substring(3);
//        return SignDataUtil.md5("2"+"294fd721c603dafd748f1"+SignDataUtil.md5("8352"+three+"6972"+afterthree+"7332")+"294fd721c603dafd748f1"+"2");
//    }

    //一层加密
//    public static String pwdOneLayerEncryption(String pwd){
//        if(TextUtils.isEmpty(pwd))return "";
//        String newpwd= pwd;
//        String three=newpwd.substring(0,3);
//        String afterthree=newpwd.substring(3);
//        return SignDataUtil.md5("8352"+three+"6972"+afterthree+"7332");
//    }

    /**
     * 应用程序是否已安装
     *
     * @param context
     * @param packageName 应用程序的包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> mPacks = pm.getInstalledPackages(0);
        for (PackageInfo info : mPacks) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 0) {
                if (packageName.equals(info.packageName)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * String 类型的 加法运算
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Utils.toNormal(v1));
        BigDecimal b2 = new BigDecimal(Utils.toNormal(v2));


        String result = String.valueOf(b1.add(b2).doubleValue());
        return result;
    }

    /**
     * String 类型的 减法运算
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Utils.toNormal(v1));
        BigDecimal b2 = new BigDecimal(Utils.toNormal(v2));

        String v = String.valueOf(b1.subtract(b2).doubleValue());
        return v;
    }

    /**
     * String 类型的 减法运算
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double sub2Double(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Utils.toNormal(v1));
        BigDecimal b2 = new BigDecimal(Utils.toNormal(v2));

        String v = String.valueOf(b1.subtract(b2).doubleValue());

        return Double.valueOf(v);
    }

    /**
     * String类型 的乘法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).doubleValue();
    }

    public static String floatToPecent(float number) {
        DecimalFormat numberFormat = new DecimalFormat("0.00%");
        String format = numberFormat.format(number);
        return format;
    }

    public static String doubleToPecent(double number) {
        DecimalFormat numberFormat = new DecimalFormat("0.00%");
        String format = numberFormat.format(number);
        return format;
    }

    public static String doubleToPecent(String number) {
        DecimalFormat numberFormat = new DecimalFormat("0.00%");
        String format = numberFormat.format(number);
        return format;
    }

    public static String toNormal(double amount) {
        BigDecimal decimalFormat = new BigDecimal(String.valueOf(amount));
        LogUtil.i("----toNormal---->", "toNormal: " + decimalFormat.toPlainString());
        return decimalFormat.toPlainString();
    }

    public static String toNormal(String amount) {
        BigDecimal decimalFormat = new BigDecimal(amount);
        return decimalFormat.toPlainString();
    }

    public static String to4Decimal(double amount) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(4);
        return nf.format(amount);
    }

    public static String to4SubString(double amount) {
        String amountResult = Utils.toNormal(amount);
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder(split[0]);
            if (split.length > 1 && split[1].length() > 3) {
                stringBuilder.append(".").append(split[1].substring(0, 4));
                return Utils.toDoubleNormal(Double.parseDouble(stringBuilder.toString()), 4);
            } else {
                return Utils.toDoubleNormal(Double.parseDouble(stringBuilder.toString()), 4);
            }
        } else {
            return Utils.toDoubleNormal(Double.parseDouble(amountResult), 4);
        }
    }

    /**
     * 截取不要保留
     *
     * @param amount
     * @return
     */
    public static String toSubStringNo(double amount, int digister) {
        String amountResult = Utils.toNormal(amount);
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder(split[0]);
            if (split.length > 1 && split[1].length() > digister - 1) {
                stringBuilder.append(".").append(split[1].substring(0, digister));
                return Utils.toDoubleNormal(Double.parseDouble(stringBuilder.toString()), digister);
            } else {
                return Utils.toDoubleNormal(amount, digister);
            }
        } else {
            return Utils.toDoubleNormal(Double.parseDouble(amountResult), digister);
        }
    }


    /**
     * 截取，不足补零(每三位一个逗号)
     *
     * @param amount
     * @return
     */
    public static String toSubStringDegist(double amount, int degist) {
        String amountResult = Utils.toNormal(amount);
        //2353453---->2353453.0
        if(!amountResult.contains("."))amountResult=amountResult+".0";
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
//            StringBuilder stringBuilder=new StringBuilder(split[0]);
            String strFront = Utils.toDoubleNormal(Double.parseDouble(split[0]), 0);
            if (split.length > 1 && split[1].length() > degist - 1) {
                stringBuilder.append(".").append(split[1].substring(0, degist));
                String strBack = stringBuilder.toString();
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + strBack;
                } else {
                    return strFront + strBack;
                }
            } else if (split.length > 1) {
                stringBuilder.append(".").append(split[1]);
                for (int i = 0; i < (degist) - split[1].length(); i++) {
                    stringBuilder.append("0");
                }
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + stringBuilder.toString();
                }
                return strFront + stringBuilder.toString();
            } else {
                return Utils.toDoubleNormal(Double.parseDouble(amountResult), degist);
            }
        } else {
            return Utils.to2Double(Double.parseDouble(amountResult));
        }
    }
    /**
     * 截取，不足补零(每三位一个逗号),当degist为0时则不要小数点
     *
     * @param amount
     * @return
     */
    public static String toNoPointSubStringDegistIfDegistIsZero(double amount, int degist) {
        String amountResult = Utils.toNormal(amount);
        if(degist == 0){
            return new BigDecimal(amount).setScale(0, BigDecimal.ROUND_DOWN).toString();
        }
        //2353453---->2353453.0
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
//            StringBuilder stringBuilder=new StringBuilder(split[0]);
            String strFront = Utils.toDoubleNormal(Double.parseDouble(split[0]), 0);
            if (split.length > 1 && split[1].length() > degist - 1) {
                stringBuilder.append(".").append(split[1].substring(0, degist));
                String strBack = stringBuilder.toString();
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + strBack;
                } else {
                    return strFront + strBack;
                }
            } else if (split.length > 1) {
                stringBuilder.append(".").append(split[1]);
                for (int i = 0; i < (degist) - split[1].length(); i++) {
                    stringBuilder.append("0");
                }
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + stringBuilder.toString();
                }
                return strFront + stringBuilder.toString();
            } else {
                return Utils.toDoubleNormal(Double.parseDouble(amountResult), degist);
            }
        } else {
            return Utils.to2Double(Double.parseDouble(amountResult));
        }
    }


    /**截取，不足补零(每三位一个逗号)，小數點后保留6位，不足6位的 保留原有位數，整數的在後面加.0
     *
     * @param amount
     * @param degist：保留小数位数
     * @param supplement:小数点不足是否补0
     * @return
     */
    public static String toSubStringDegistForChart(double amount, int degist,boolean supplement) {
        String amountResult = Utils.toNormal(amount);
        //2353453---->2353453.0
        if(!amountResult.contains("."))amountResult=amountResult+".0";
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
//            StringBuilder stringBuilder=new StringBuilder(split[0]);
            String strFront = Utils.toDoubleNormal(Double.parseDouble(split[0]), 0);
            if (split.length > 1 && split[1].length() > degist - 1) {
                stringBuilder.append(".").append(split[1].substring(0, degist));
                String strBack = stringBuilder.toString();
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + strBack;
                } else {
                    if(degist==0){//修改degist=0的情况
                        return strFront;
                    }
                    return strFront + strBack;
                }
            } else if (split.length > 1) {
                stringBuilder.append(".").append(split[1]);

                //判斷是否存在小數點 如果存在判斷小數點后位數>6只保留6位不足6保留原有位數
                if(supplement){
                    for (int i = 0; i < (degist) - split[1].length(); i++) {
                        stringBuilder.append("0");
                    }
                }
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + stringBuilder.toString();
                }
                return strFront + stringBuilder.toString();
            } else {
                return Utils.toDoubleNormal(Double.parseDouble(amountResult), degist);
            }
        } else {
            return Utils.to2Double(Double.parseDouble(amountResult));
        }
    }



    /**截取，不足补零(每三位一个逗号)，小數點后保留6位，不足6位的 保留原有位數，整數的在後面加.0
     *
     * @param amount
     * @param degist：保留小数位数
     * @param supplement:小数点不足是否补0
     * @return
     */
    public static String toSubStringDegistForChartStr(String amount, int degist,boolean supplement) {
        //    String amountResult = Utils.toNormal(amount);

        String amountResult = amount;
        //2353453---->2353453.0
        if(!amountResult.contains("."))amountResult=amountResult+".0";
        if (amountResult.contains(".")) {
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
//            StringBuilder stringBuilder=new StringBuilder(split[0]);
            String strFront = Utils.toDoubleNormal(Double.parseDouble(split[0]), 0);
            if (split.length > 1 && split[1].length() > degist - 1) {
                stringBuilder.append(".").append(split[1].substring(0, degist));
                String strBack = stringBuilder.toString();
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + strBack;
                } else {
                    return strFront + strBack;
                }
            } else if (split.length > 1) {
                stringBuilder.append(".").append(split[1]);

                //判斷是否存在小數點 如果存在判斷小數點后位數>6只保留6位不足6保留原有位數
                if(supplement){
                    for (int i = 0; i < (degist) - split[1].length(); i++) {
                        stringBuilder.append("0");
                    }
                }
                if ("00".equals(strFront)) {
                    return strFront.replaceAll("00", "0") + stringBuilder.toString();
                }
                return strFront + stringBuilder.toString();
            } else {
                return Utils.toDoubleNormal(Double.parseDouble(amountResult), degist);
            }
        } else {
            return Utils.to2Double(Double.parseDouble(amountResult));
        }
    }




    /**
     * 截取，不足补零（去掉逗号）
     * @param amount
     * @return
     */
    public static String toSubStringDegistNo(String amount,int degist){
        // String amountResult =Utils.toNormal(amount);
        String amountResult =amount;

        if (amountResult.contains(".")){
            String[] split = amountResult.split("\\.");
            StringBuilder stringBuilder=new StringBuilder();
//            StringBuilder stringBuilder=new StringBuilder(split[0]);
            String strFront = Utils.toDoubleNormal(Double.parseDouble(split[0]), 0);
            if (split.length>1&&split[1].length()>degist-1){
                stringBuilder.append(".").append(split[1].substring(0,degist));
                String strBack = stringBuilder.toString();
                if ("00".equals(strFront)){
                    return (strFront.replaceAll("00","0")+strBack).replaceAll(",","");
                }else {
                    return (strFront+ strBack).replaceAll(",","");
                }
            }else if (split.length>1){
                stringBuilder.append(".").append(split[1]);
                for (int i = 0; i < (degist)-split[1].length(); i++) {
                    stringBuilder.append("0");
                }
                if ("00".equals(strFront)){
                    return (strFront.replaceAll("00","0")+stringBuilder.toString()).replaceAll(",","");
                }
                return (strFront+stringBuilder.toString()).replaceAll(",","");
            }else {
                return (Utils.toDoubleNormal(Double.parseDouble(amountResult),degist)).replaceAll(",","");
            }
        }else {
            return (Utils.to2Double(Double.parseDouble(amountResult))).replaceAll(",","");
        }
    }



    public static String to2Decimal(double amount) {
        BigDecimal decimalFormat = new BigDecimal(amount);
        decimalFormat.setScale(2, BigDecimal.ROUND_DOWN);
        return decimalFormat.toPlainString();
    }

    public static String to2Double(double amount) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(2);
        return nf.format(amount);
    }

    public static String toDoubleNormal(double amount, int digits) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(digits);
        return nf.format(amount);
    }


    public static String to8Double(double amount) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(8);
        return nf.format(amount);
    }

    public static String getVersionName(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    public static int getPrecision(String amount) {
        int index = amount.indexOf("1") - 1;

        return index;
    }

    public static boolean isUsername(final CharSequence input) {
        return isMatch(REGEX_USERNAME, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 正则：用户名，取值范围为 字母和数组（4-16）
     */
    public static final String REGEX_USERNAME = "^[A-Za-z0-9]{4,16}$";


}
