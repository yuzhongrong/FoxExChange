package com.cjwsc.idcm.net.factory;

import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.net.response.HttpResponse;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/12 上午10:26
 **/
public class ExGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;


    ExGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 进行解析预处理操作
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String value = responseBody.string();

        HttpResponse httpResponse = new HttpResponse();
        try {

            JSONObject response = new JSONObject(value);
             int status=response.getInt("status");
//            int error = Integer.parseInt(response.getString("code"));
            if (status != 200)//java 200 是请求成功 代表网络请求成功
            {
                httpResponse.setCode(response.getString("code"));
                httpResponse.setData(response.getString("data"));
                //继续解析判断业务逻辑
                return (T) gson.fromJson(value, httpResponse.getClass());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.e(value);

        T t= gson.fromJson(value,type);

        return t;
    }
}
