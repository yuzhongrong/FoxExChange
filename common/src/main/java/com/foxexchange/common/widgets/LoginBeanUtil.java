package com.foxexchange.common.widgets;

import com.cjwsc.idcm.Utils.ACacheUtil;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.foxexchange.common.beans.ExLoginBean;

public class LoginBeanUtil {

    public static ExLoginBean getLoginBean(){

      return (ExLoginBean) ACacheUtil.get(BaseApplication.getContext()).getAsObject("loginbean");

    }


}
