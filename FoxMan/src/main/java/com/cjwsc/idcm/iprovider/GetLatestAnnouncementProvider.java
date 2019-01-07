package com.cjwsc.idcm.iprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.cjwsc.idcm.model.bean.providerbean.NoticeMessageBean;

import io.reactivex.Flowable;

/**
 * Created by ${zipp} on 2018/1/5.
 * 功能描述：
 */

public interface GetLatestAnnouncementProvider
        extends IProvider {
    Flowable<NoticeMessageBean> getLatestAnnouncement(Context context);
}
