package com.cjwsc.idcm.net;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ${zipp} on 2017/12/24.
 * 功能描述： 多文件上传
 */

public class RetrofitPostUtils {
//    private static final String MULTIPART_FORM_DATA = "multipart/form-data";  // 指明要上传的文件格式
//    private static final String MULTIPART_Stream = "application/octet-stream";  // 指明要上传的文件格式
    private static final String MULTIPART_PNG = "image/png";  // 指明要上传的文件格式

    public static List<MultipartBody.Part> files2Parts(String CertNum, String CertType, String FirstName, String LastName, String FullName, String CertStartTime, String CertEndTime, String CountryCode, HashMap<String, String> params, MediaType imageType) {
        if (imageType == null) {
//            imageType = MediaType.parse(MULTIPART_FORM_DATA);
            imageType = MediaType.parse(MULTIPART_PNG);
        }
        Set<Map.Entry<String, String>> entries = params.entrySet();
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (Iterator<Map.Entry<String, String>> it = entries.iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            File file = new File(entry.getValue());
            // 根据类型及File对象创建RequestBody（okhttp的类）
            RequestBody requestBody = RequestBody.create(imageType, file);
            // 将RequestBody封装成MultipartBody.Part类型（同样是okhttp的）
            String name = file.getName();
            String key = entry.getKey();
            MultipartBody.Part part = MultipartBody.Part.
                    createFormData(key, name, requestBody);
            // 添加进集合
            parts.add(part);
        }

        MultipartBody.Part part = MultipartBody.Part.createFormData("CertNum", CertNum);
        parts.add(part);
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("CertType", CertType);
        parts.add(part2);
        MultipartBody.Part part3 = MultipartBody.Part.createFormData("FirstName", FirstName);
        parts.add(part3);

        if (!TextUtils.isEmpty(FullName)){
            MultipartBody.Part part4 = MultipartBody.Part.createFormData("FullName", FullName);
            parts.add(part4);
        }


        MultipartBody.Part part5 = MultipartBody.Part.createFormData("CertStartTime", CertStartTime);
        parts.add(part5);

        MultipartBody.Part part6 = MultipartBody.Part.createFormData("CertEndTime", CertEndTime);
        parts.add(part6);

        MultipartBody.Part part7 = MultipartBody.Part.createFormData("CountryCode", CountryCode);
        parts.add(part7);
        MultipartBody.Part part8 = MultipartBody.Part.createFormData("ClientType", "1");
        parts.add(part8);
        MultipartBody.Part part9 = MultipartBody.Part.createFormData("LastName", LastName);
        parts.add(part9);

        return parts;
    }

}
