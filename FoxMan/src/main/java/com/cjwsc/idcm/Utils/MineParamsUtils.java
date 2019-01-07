package com.cjwsc.idcm.Utils;

import java.util.Map;

/**
 * Created by yuzhongrong on 2017/8/22.
 */

public class MineParamsUtils extends SignDataUtil {

    public static Map<String, String> getUploadMap(){
        Map<String, String> params =getDefaultParams();
//        params.put("token","xxx");
//        params.put("pic_type","user_image");
        return sign(params);


    }


}
