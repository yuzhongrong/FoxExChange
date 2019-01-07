package com.cjwsc.idcm.Utils;


import android.content.Context;

import com.cjwsc.idcm.callback.GetAssetsCallBack;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${zipp} on 2017/12/1.
 * 功能描述：这是读取Assets文件工具类
 */

public class AssetsUtils {
    static String final_result="";
    public static void fromAssestJson(Context context, String fileName, GetAssetsCallBack getAssetsCallBack){

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String result = "";
                try {
                    //获取输入流
                    InputStream mAssets = context.getAssets().open(fileName);
                    int available = mAssets.available();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while((len=mAssets.read(buffer))!=-1){
                        outStream.write(buffer,0,len);
                    }
                    result = new String(outStream.toString());
                    outStream.close();
                    mAssets.close();
                    e.onNext(result);
                } catch (IOException ioexcep) {
                    ioexcep.printStackTrace();
                    LogUtil.e(ioexcep.getMessage());
                    e.onError(ioexcep);
                }

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        getAssetsCallBack.success(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getAssetsCallBack.error(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
