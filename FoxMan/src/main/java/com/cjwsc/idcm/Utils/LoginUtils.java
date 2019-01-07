package com.cjwsc.idcm.Utils;

import android.content.Context;

import com.cjwsc.idcm.constant.AcacheKeys;
import com.cjwsc.idcm.model.bean.providerbean.LoginStatus;

/**
 * 作者：hxy
 * 电话：13891436532
 * 邮箱：hua.xiangyang@shuweikeji.com
 * 版本号：1.0
 * 类描述：FoxIDCW com.cjwsc.idcm.Utils ${CLASS_NAME}
 * 备注消息：
 * 修改时间：2018/3/15 16:27
 **/

public final class LoginUtils {

    public static LoginStatus getLoginBean(Context context) {
        return (LoginStatus) ACacheUtil.get(context).getAsObject(AcacheKeys.loginbean);
    }

    public static LoginStatus getLoginBean(Context context,String phone) {
        return (LoginStatus) ACacheUtil.get(context).getAsObject(phone);
    }

    public static String getAppToken(Context context) {
        LoginStatus bean = getLoginBean(context);
        return bean == null ? "" : bean.getTicket();
    }
}
