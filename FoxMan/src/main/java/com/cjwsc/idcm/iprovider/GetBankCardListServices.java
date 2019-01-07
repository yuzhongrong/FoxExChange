package com.cjwsc.idcm.iprovider;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.mineBean.BankCardListBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2017/12/27.
 * 功能描述：
 */

public interface GetBankCardListServices extends IProvider {
    Flowable<List<BankCardListBean.DataBean>> getBankCardList();
}
