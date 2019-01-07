package com.cjwsc.idcm.model.bean;

/**
 * Created by liaoyunxia on 17/9/11.
 */

public class AreaBean {

    /**
     * area_code : 测试内容5c16
     * region_id : 61240
     * region_name : 广州
     */

    private String area_code;//区域码
    private int region_id; //区域id
    private String region_name;//区域名称

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
