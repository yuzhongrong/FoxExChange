package com.cjwsc.idcm.net.exception;

/**
 * 作者：${User}
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}1904
 */

public class ResponseThrowable extends Exception {
    public String ErrorCode;           //如果code==1000,说明是解析数据异常了
    public String ErrorMsg;
    public Throwable throwable;
    public int state;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ResponseThrowable(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public ResponseThrowable(Throwable throwable, String code) {
        super(throwable);
        this.throwable = throwable;
        this.ErrorCode = code;
    }

    @Override
    public String toString() {
        return "ResponseThrowable{" +
                "ErrorCode='" + ErrorCode + '\'' +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", throwable=" + throwable +
                ", state=" + state +
                '}';
    }
}
