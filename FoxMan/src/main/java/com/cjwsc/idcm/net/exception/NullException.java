package com.cjwsc.idcm.net.exception;

/**
 * Created by admin-2 on 2018/4/12.
 */

public class NullException extends NullPointerException {

    public String code;
    public String message;

    public NullException(String message, String code) {
        this.message=message;
        this.code = code;
    }
}
