package com.cjwsc.idcm.iprovider.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cjwsc.idcm.Utils.RetrofitUtil;
import com.cjwsc.idcm.api.FoxManHttpApi;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.net.http.HttpUtils;
import com.cjwsc.idcm.net.response.HttpResponse;
import com.cjwsc.idcm.net.transformer.DefaultTransformer;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.cjwsc.idcm.iprovider.UploadImgProviderServices;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

import static com.cjwsc.idcm.constant.ProviderPath.path_UploadServices;

/**
 * Created by yuzhongrong on 2018/5/7.
 */
@Route(path = path_UploadServices)
public class UploadImgProviderImpl implements UploadImgProviderServices {
    @Override
    public Flowable<Object> requestUpload(List<String> paths) {
       return Flowable.just(paths)
               .observeOn(Schedulers.io())
               .map(new Function<List<String>, List<File>>() {
                   @Override
                   public List<File> apply(List<String> strings) throws Exception {
                       return Luban.with(BaseApplication.getContext()).ignoreBy(600).load(strings).get();
                   }
               }).flatMap(new Function<List<File>, Flowable<HttpResponse<Object>>>() {
                   @Override
                   public Flowable<HttpResponse<Object>> apply(List<File> files) throws Exception {
                       return HttpUtils.getInstance(BaseApplication.getContext())
                               .getRetrofitClient()
                               .builder(FoxManHttpApi.class)
                               .uploadImg1(RetrofitUtil.filesToMultipartBody(files,"uploadFile"));
                   }
               })
               .compose(new DefaultTransformer<Object>());
    }

    @Override
    public Flowable<List<String>> requestUpload(Map<String, String> params, List<String> paths) {
        return Flowable.just(paths)
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(List<String> strings) throws Exception {
                        return Luban.with(BaseApplication.getContext()).ignoreBy(600).load(strings).get();
                    }
                }).flatMap(new Function<List<File>, Flowable<HttpResponse<List<String>>>>() {
                    @Override
                    public Flowable<HttpResponse<List<String>>> apply(List<File> files) throws Exception {
                        return HttpUtils.getInstance(BaseApplication.getContext())
                                .getRetrofitClient()
                                .builder(FoxManHttpApi.class)
                                .uploadImg(params, RetrofitUtil.filesToMultipartBody(files,"uploadFile"));
                    }
                })
                .compose(new DefaultTransformer<List<String>>());
    }

    @Override
    public void init(Context context) {

    }
}
