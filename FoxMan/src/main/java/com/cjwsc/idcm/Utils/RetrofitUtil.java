package com.cjwsc.idcm.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by yuzhongrong on 2017/9/24.
 */

public class RetrofitUtil {

    public static MultipartBody filesToMultipartBody(List<File> files,String key) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            String mediatype;
            if(file.getName().endsWith(".jpeg")||file.getName().endsWith(".jpg")){
                mediatype="image/jpeg";
            }else if(file.getName().endsWith(".png")||file.getName().endsWith(".png")){

                mediatype="image/png";

            }else{
                mediatype="";
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse(mediatype), file);
            builder.addFormDataPart(key+i, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }


    public static MultipartBody onefilesToMultipartBody(List<File> files,String key) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            String mediatype;
            if(file.getName().endsWith(".jpeg")||file.getName().endsWith(".jpg")){
                mediatype="image/jpeg";
            }else if(file.getName().endsWith(".png")||file.getName().endsWith(".png")){

                mediatype="image/png";

            }else{
                mediatype="";
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse(mediatype), file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    public static Map<String, RequestBody> files2Map(List<File> files) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        for (File file : files) {
            paramsMap.put("uploadFile\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }
        return paramsMap;
    }
}
