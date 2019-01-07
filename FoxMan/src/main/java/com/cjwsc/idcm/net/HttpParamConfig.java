package com.cjwsc.idcm.net;

import android.content.Context;


import java.util.HashMap;
import java.util.Map;

/**
 * author: iheyh
 * created on: 2017/7/3 18:42
 * description:网络请求公共参数配置
 */

public class HttpParamConfig {
    /*客户端应用程序类型	必选，1：PC商城，2：O店APP，3：卖点APP，4：千城万店APP，5：WAP(m站)，6：农特产APP */
    public static final String CLIENTAPP = "clientApp";
    /*客户端类型	1：PC端，2：手机浏览器(m站)，3：adnroid端，4：iOS端，5：千城万店电子屏，6：未知来源*/
    public static final String CLIENTTYPE = "clientType";
    /*客户端IP地址	*/
    public static final String CUSTOMERIP = "customerIp";
    /*客户端Mac地址	*/
    public static final String CUSTOMERMAC = "customerMac";
    /*业务类型 10：商城商品(pc)，20：O2O商品列表，30：O店商品列表(O店)，40：B2B分销商品列表(卖点)，50：千城万店商品列表，60:WAP商品列表(m站)，70:农产品APP	*/
    public static final String BUSINESSTYPE = "businessType";
    //用户token
    public static final String TOKEN = "token";
    public static final String PRODUCTCODE = "productCode";
    /*页码*/
    public static final String PAGE = "page";
    /*每页显示记录数	*/
    public static final String PAGESIZE = "pageSize";


    /**
     * APP  基础参数
     *
     * @return
     */
    public static Map<String, String> getBase() {
        Map<String, String> params = new HashMap<>();
        params.put(CLIENTAPP, "8");
        params.put(CLIENTTYPE, "3");
        params.put(BUSINESSTYPE, "80");
        return params;
    }


    /**
     * APP  基础参数
     *
     * @return
     */
    public static Map<String, String> getBaseWithToken() {
        Map<String, String> params = getBase();
        //  params.put(TOKEN, LoginStatus.getToken());
        return params;
    }

    public static Map<String, String> getBaseWithUserId() {
        Map<String, String> params = getBase();
        // params.put(AppConfig.USERID, LoginStatus.getUser_id());
        return params;
    }

    public static Map<String, String> getBaseWithTokenAndUserId() {
        Map<String, String> params = getBase();
       /* if (LoginStatus.getToken() != null) {
            params.put(AppConfig.TOKEN, LoginStatus.getToken());
            params.put(AppConfig.USERID, LoginStatus.getUser_id());
        }*/
        return params;
    }

    /**
     * APP 分页基础
     *
     * @param page
     * @return
     */
    public static Map<String, String> getBasePage(int page) {
        Map<String, String> params = getBase();
        params.put(PAGE, String.valueOf(page));
        params.put(PAGESIZE, "12");
        return params;
    }

    /**
     * 需要token和userid的分页
     *
     * @return
     */
    public static Map<String, String> getBaseLoginPage(int page) {
        Map<String, String> params = getBasePage(page);
      /*  if (LoginStatus.getToken() != null) {
            params.put(AppConfig.TOKEN, LoginStatus.getToken());
            params.put(AppConfig.USERID, LoginStatus.getUser_id());
        }*/

        return params;
    }

    /**
     * 带base 和token 以及页码的 param
     *
     * @param page 页码
     * @return
     */
    public static Map<String, String> getBaseAWithTokenAndPage(int page) {
        Map<String, String> params = getBasePage(page);
        /*if (LoginStatus.getToken() != null) {
            params.put(AppConfig.TOKEN, LoginStatus.getToken());
        }*/

        return params;
    }

    /**
     * 需要mac和ip的接口
     *
     * @return
     */
    public static Map<String, String> getBaseMacPage(Context context) {
        Map<String, String> params = getBase();
        //params.put(CUSTOMERMAC, Utils.getIMEI(context));
        //params.put(CUSTOMERIP, IPUtils.getIPAddress(context));

        return params;
    }

    /**
     * 需要token和userid的接口
     *
     * @return
     */
    public static Map<String, String> getBaseUserAndToken() {
        Map<String, String> params = getBase();
        //params.put(TOKEN, LoginStatus.getToken());
        // params.put(USERID, LoginStatus.getUser_id());

        return params;
    }

    /**
     * 需要token和userid的接口
     *
     * @return
     */
    public static Map<String, String> getBaseUserAndTokenMac(Context context) {
        Map<String, String> params = getBase();
        //  params.put(AppConfig.TOKEN, LoginStatus.getToken());
        //  params.put(AppConfig.USERID, LoginStatus.getUser_id());
        // params.put(AppConfig.CUSTOMERMAC, Utils.getIMEI(context));
        return params;
    }

}
