package com.cjwsc.idcm.widget.piechart;

/**
 * Created by Idtk on 2016/6/22.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 * 描述 : 扇形图数据类
 * name、color、value、percentage、angle、width、textSize、isText
 */
public class PieChartData extends ChartData implements IPieData{
    private float value;
    private float percentage;
    private float angle = 0;
    private float currentAngle;
//    private int textColor = Color.WHITE;

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public float getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }

//    public int getTextColor() {
//        return textColor;
//    }

//    public void setTextColor(int textColor) {
//        this.textColor = textColor;
//    }


}
