package com.cjwsc.idcm.model.bean;

/**
 *
 * @project name : IDCM2.1
 * @class name :   ServiceStateBean
 * @package name : com.cjwsc.idcm.model.bean
 * @author :       MayerXu10000@gamil.com
 * @date :         2018/3/2 16:57
 * @describe :     TODO
 *
 */
public class ServiceStateBean {

    /**
     * status : true
     * statusCode : 00000
     * data : {}
     */

    private boolean status;
    private String   statusCode;
    private DataBean data;

    public boolean isStatus() { return status;}

    public void setStatus(boolean status) { this.status = status;}

    public String getStatusCode() { return statusCode;}

    public void setStatusCode(String statusCode) { this.statusCode = statusCode;}

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public static class DataBean {}
}
