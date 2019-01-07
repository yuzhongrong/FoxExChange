package com.cjwsc.idcm.model.bean.mineBean;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class DataParse {
    private ArrayList<MinutesBean> datas = new ArrayList<>();
    private float baseValue;
    private float permaxmin;
    private float volmax;
    private SparseArray<String> dayLabels;
    private String code = "sz002081";
    private int decreasingColor;
    private int increasingColor;
    private String stockExchange;
    private SparseArray<String> xValuesLabel=new SparseArray<>();
    private int firstDay = 10;
    private float totalNum;
    private double totalValue;

    public void parseMinutes(List<KLineDataBean> kLineDataBeanList) {
        for (int i = 0; i < kLineDataBeanList.size(); i++) {
            totalValue+=kLineDataBeanList.get(i).getClosed();
        }
        baseValue = (float) totalValue/kLineDataBeanList.size();
        for (int i = 0; i < kLineDataBeanList.size(); i++) {
            MinutesBean minutesData = new MinutesBean();
            KLineDataBean dataBean = kLineDataBeanList.get(i);
            float opened = (float) dataBean.getOpened();
            float closed = (float) dataBean.getClosed();
            float highest = (float) dataBean.getHighest();
            float lowest = (float) dataBean.getLowest();
            float dNum = (float) dataBean.getDNum();
            float rose = (float) dataBean.getRose();
            String dealDate = dataBean.getDealDate();

            minutesData.time = dealDate.split(" ")[1];
            minutesData.cjprice = closed;
            minutesData.cjnum = dNum;
            totalNum = totalNum + dNum;
            if (i == 0) {
                minutesData.avprice = minutesData.cjprice;
                minutesData.total = minutesData.cjnum * minutesData.cjprice;
            } else {
                minutesData.total = minutesData.cjnum * minutesData.cjprice + datas.get(i - 1).total;
                minutesData.avprice = (minutesData.total) / totalNum;       //计算平均价格

            }
            minutesData.cha = minutesData.cjprice - baseValue;
            minutesData.per = (minutesData.cha / baseValue);
            double cha=minutesData.cjprice-baseValue;
            if (Math.abs(cha)>permaxmin){
                permaxmin=(float)Math.abs(cha);
            }
            volmax = Math.max(minutesData.cjnum, volmax);
            datas.add(minutesData);
        }
        if (permaxmin == 0) {
            permaxmin = baseValue * 0.02f;
        }

    }


    public float getMin() {
        return baseValue - permaxmin;
    }

    public float getMax() {
        return baseValue + permaxmin;
    }

    public float getPercentMax() {
        return permaxmin / baseValue;
    }

    public float getPercentMin() {
        return -getPercentMax();
    }


    public float getVolmax() {
        return volmax;
    }


    public ArrayList<MinutesBean> getDatas() {
        return datas;
    }

    public SparseArray<String> getXValuesLabel() {
        return xValuesLabel;
    }
}
