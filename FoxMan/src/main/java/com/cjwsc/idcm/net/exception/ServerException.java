package com.cjwsc.idcm.net.exception;

/**
 * 作者：${User}
 * 电话：18826585609
 * 邮箱：956942189@qq.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}1848
 */

public class ServerException extends RuntimeException {
    public String code;
    public String message;

    public ServerException(String message, String code) {
        this.message=message;
        this.code = code;
    }
}
