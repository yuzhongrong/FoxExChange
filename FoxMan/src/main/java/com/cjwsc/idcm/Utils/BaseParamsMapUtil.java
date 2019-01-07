package com.cjwsc.idcm.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：yzr
 * <p/>
 * 邮箱:956942189@qq.com
 * <p/>
 * 日期：2016/2/25
 * <p/>
 * 描述信息：请求网络参数封装类
 * <p/>
 * 备注信息:
 */
public class BaseParamsMapUtil {

    /**
     * 公共的参数集合
     *
     * @return
     */
    public static Map<String, String> getParamsMap() {
        Map<String, String> paramsmap = new LinkedHashMap<>();
        paramsmap.put("ClientType", "1");
        return paramsmap;
    }

    public static Map<String,String> getTokenParamsMap(){
        Map<String, String> paramsmap = new LinkedHashMap<>();
//        String language = Locale.getDefault().getLanguage();
        paramsmap.put("ClientType", "1");
        paramsmap.put("LanguageCode", "zh-CN");
        return paramsmap;
    }



    public static Map<String, String> getDefaultParamsForTimeStamp(){
        Map<String, String> paramsmap = new LinkedHashMap<>();
        paramsmap.put("timestamp", "" + System.currentTimeMillis());//默认有时间戳 java 是传timestamp cms传time
        paramsmap.put("businessType","20");
        paramsmap.put("clientApp","2");
        paramsmap.put("clientType","2");
        return paramsmap;
    }


}
