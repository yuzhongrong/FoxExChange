package com.cjwsc.idcm.Utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.GlideApp;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by ${zipp} on 2017/12/1.
 * 功能描述：banner图片加载器
 */

public class GlideImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideApp.with(context).load((String) path).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.home_banner).into(imageView);
    }
}
