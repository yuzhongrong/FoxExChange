package com.cjwsc.idcm.iprovider;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by hpz on 2018/5/5.
 */

public interface UploadImgProviderServices extends IProvider{
    Flowable<Object> requestUpload(List<String> path);
    Flowable<List<String>> requestUpload(Map<String, String> params, List<String> path);
}
