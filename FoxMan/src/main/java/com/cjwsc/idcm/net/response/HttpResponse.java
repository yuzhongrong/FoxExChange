package com.cjwsc.idcm.net.response;

/**
 * 作者：yzr
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：1.0
 * 类描述： 约定服务器公共的json数据
 * 备注消息：
 * 修改时间：2016/11/23 下午5:52
 **/
public class HttpResponse<T> {

    private int error;
    private int status;
    private T data;
    private String code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "error=" + error +
                ", status=" + status +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
