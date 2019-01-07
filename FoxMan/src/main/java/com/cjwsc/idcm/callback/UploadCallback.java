package com.cjwsc.idcm.callback;

/**
 * 上传文件回调
 * Created by xiongz on 2016/11/11.
 */
public interface UploadCallback {

    /**
     * 网络请求开始
     */
    void onStart();

    /**
     * 接口返回成功
     * @param body    返回参数
     * @param message 返回信息
     */
    void onInterSuccess(String body, String message);

    /**
     * 接口返回失败
     * @param body    返回参数
     * @param message 返回信息
     */
    void onInterError(String body, String message);

    /**
     * 网络问题返回失败
     * @param response
     */
    void onFailure(String response);
}
