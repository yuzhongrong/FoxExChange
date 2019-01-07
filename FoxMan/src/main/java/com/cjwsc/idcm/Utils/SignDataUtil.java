package com.cjwsc.idcm.Utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by:yuzhongrong on 2017/8/12.
 */

public class SignDataUtil {


    private final static String SIGNKEY = "40287ae447680a6b0147680a6b580000";

    /**
     * MD5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 签名
     * @param params
     * @return
     */
    public static Map<String, String> sign(Map<String, String> params) {
        params.put("apiKey", SIGNKEY);
        //对key进行字典升序排序
        Collection<String> keyset = params.keySet();
        List<String> list = new ArrayList<>(keyset);
        Collections.sort(list);

        StringBuilder sb = new StringBuilder("");
        for (String key : list) {
            String value = params.get(key);
            if (!TextUtils.isEmpty(value)) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        params.put("signature",md5(sb.toString()));//signature signature
        params.remove("apiKey");
        return  params;
    }


    public static Map<String,String> getDefaultParams(){

        Map<String, String> paramsmap = new LinkedHashMap<>();
         paramsmap.put("timestamp", "" + System.currentTimeMillis());//默认有时间戳
         paramsmap.put("businessType","20");
         paramsmap.put("clientApp","2");
         paramsmap.put("clientType","2");

        return paramsmap;
    }



}
