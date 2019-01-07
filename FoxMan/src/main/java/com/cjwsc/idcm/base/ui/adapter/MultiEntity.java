package com.cjwsc.idcm.base.ui.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by yuzhongrong on 2017/10/13.
 */

public class MultiEntity implements MultiItemEntity {
    protected int multiType;


    public void setMultiType(int multiType) {
        this.multiType = multiType;
    }

    @Override
    public int getItemType() {
        return multiType;
    }
}