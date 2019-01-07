package com.cjwsc.idcm.manager;

import android.content.Context;

import com.cjwsc.idcm.callback.OnPaySuccessInterface;

/**
 * 钱包支付管理类
 * Created by zc on 2016/5/27.
 */
public class BalancePayManger {
    private Context mContext;
    public BalancePayManger(Context context) {
        mContext = context;
    }

    private OnPaySuccessInterface mOnPaySuccessInterface;//支付成功时候的回调




}
