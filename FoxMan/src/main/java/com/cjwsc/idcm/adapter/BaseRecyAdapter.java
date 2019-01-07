package com.cjwsc.idcm.adapter;/**
 * Created by chenyang on 2017/2/28.
 */

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @version 1.0
 * @class BaseRecyAdapter
 * @class 描述：
 * @anthor 陈杨  邮箱 ：15012850366@139.com
 * @time 2017/2/28 上午10:24
 * @change
 * @chang time
 * @class 迭代描述
 */
public abstract class BaseRecyAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public BaseRecyAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }
}
