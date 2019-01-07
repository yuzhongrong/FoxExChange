package com.cjwsc.idcm.iprovider;


import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.EmptyBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2018/2/7.
 * 功能描述：
 */

public interface UserOptinalProvider extends IProvider {
    Flowable<List<String>> GetUserTransactionList();
    Flowable<EmptyBean> AddOrDelete(String configId, int collectionType);
}
