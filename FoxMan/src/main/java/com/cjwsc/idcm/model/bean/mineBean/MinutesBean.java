package com.cjwsc.idcm.model.bean.mineBean;


public class MinutesBean {
    public String time;     //时间
    public float cjprice;   //价格
    public float cjnum;     //数目
    public float avprice = Float.NaN;       //平均价格
    public float per;       //波动百分比
    public float cha;      //与基准线偏差
    public float total;     //总共的总额
    public int color = 0xff000000;
}
