package com.cjwsc.idcm.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by liaoyunxia on 17/9/4.
 * 时间转换工具类
 */

public class DateTimeUtil {
    public static final String FORMAT_TYPE_1 = "yyyy-MM-dd  HH:mm:ss";
    public static final String FORMAT_TYPE_2 = "yyyy.MM.dd";
    public static final String FORMAT_TYPE_3 = "MM月dd日 HH:mm";

    public static String getFormatTime(long time, String format) {

        return new SimpleDateFormat(format).format(new Date(time));

    }

    //将yyyy-mm-dd 转换成时间戳

    public static String getFormatTime2(long time, String format) {

        return new SimpleDateFormat(format).format(new Date(time * 1000));

    }

    public static String getPublicTime(long time) {
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - time) / 1000);

        if (ct <= 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    public static String getRemainDaiAndHourTime(long time) {
        int remainTime = (int) (time / 1000);
        if (remainTime <= 3600) {
            return "1小时";
        }
        if (remainTime > 3600 && remainTime < 86400) {
            return (remainTime / 3600) + "小时";
        }
        if (remainTime >= 86400 && remainTime < 2592000) { //86400 * 30
            int day = remainTime / 86400;
            int hour = 0;
            if (remainTime % 86400 > 0) {
                hour = (remainTime % 86400) / 3600;
                return day + "天" + hour + "小时";
            } else {
                return day + "天";
            }

        }
        return "";
    }

    public static String getPublicTime(long time, long serverTime) {
        //获取time距离当前的秒数
        int ct = (int) ((serverTime - time) / 1000);

        if (ct <= 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     *   03/04---->3/4 ,03/10-->3/10
     * @param origin
     * @return
     */
    public static String getCustomerDate(String origin){


        String[] split = origin.split("/");

        String month="";
        String  date1 = "";

        //处理月份
        if(split[0].startsWith("0")){
            month= split[0].replace("0","");
        }else{
            month=split[0];
        }
        //处理日期

        if(split[1].startsWith("0")){
            date1= split[1].replace("0","");
        }else{
            date1=split[1];
        }

        return month+"/"+date1;


    }




}
