package com.cjwsc.idcm.Utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.cjwsc.idcm.R;

import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: iheyh
 * created on: 2017/7/6 14:18
 * description:glide图片缓存工具类
 */
@GlideModule
public final class GlideUtil extends AppGlideModule {
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide_cache", 100 * 1024 * 1024));
    }

    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */

    //默认加载
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).transition(DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }
    //默认加载
    public static void loadImageView(Context mContext, @DrawableRes int res, ImageView mImageView) {
        GlideApp.with(mContext).load(res).into(mImageView);
    }
    //默认加载
    public static void loadImageView(Context mContext, @DrawableRes Bitmap res, ImageView mImageView) {
        GlideApp.with(mContext).load(res).into(mImageView);
    }
    //自定义缓存策略
    public static void loadImageView(Context mContext, String path, ImageView mImageView,DiskCacheStrategy strategy) {
        GlideApp.with(mContext).load(path).diskCacheStrategy(strategy).into(mImageView);
    }

    public static void loadImageViewCenterCrop(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).centerCrop().dontAnimate().into(mImageView);
    }


    public static void loadImageViewWithTransform(Context mContext, String path, BitmapTransformation transformation, ImageView mImageView) {
        GlideApp.with(mContext).load(path).transforms(transformation).into(mImageView);
    }


    public static void loadImageViewWithTransformForPlaceholder(Context mContext, String path, BitmapTransformation transformation,int placeholderid, ImageView mImageView) {
        GlideApp.with(mContext).load(path).placeholder(placeholderid).transforms(transformation).into(mImageView);
    }




    //包含转换器的加载,如圆角
    public static void loadImageViewWithTransform(Context mContext, Integer resid, RoundedCornersTransformation transformation, ImageView mImageView) {
        GlideApp.with(mContext).load(resid).centerCrop().transforms(transformation).into(mImageView);
    }

    //包含转换器的加载,如圆角
    public static void loadImageViewWithTransform(Context mContext, int path, BlurTransformation transformation, ImageView mImageView) {
        GlideApp.with(mContext).load(path).transforms(transformation).into(mImageView);
    }


    //加载圆形图片
    public static void loadCircleImage(Context context, String url, ImageView imageview) {
        GlideApp.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.mipmap.ic_mine_default_avatar)
                .transforms(new CircleCrop())
                .into(imageview);
    }

    //加载指定大小
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        GlideApp.with(mContext).load(path).override(width, height).into(mImageView);

    }

    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        GlideApp.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).dontAnimate().into(mImageView);
    }


    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, Bitmap bm, ImageView mImageView, int lodingImage, int errorImageView) {
        GlideApp.with(mContext).load(bm).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }


    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, byte[] bytearray, ImageView mImageView, int lodingImage, int errorImageView) {
        GlideApp.with(mContext).load(bytearray).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        GlideApp.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    //设置下载优先级
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载动画
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
//        GlideApp.with(mContext).load(path).animate(anim).into(mImageView);
    }




    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).load(path).centerCrop().into(mImageView);
    }

    //设置动态GIF加载方式
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).asGif().load(path).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(mImageView);
    }

    //设置静态GIF加载方式
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        GlideApp.with(mContext).asBitmap().load(path).into(mImageView);
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImageViewListener(Context mContext, String path, ImageView mImageView, RequestListener<Drawable> requstlistener) {
        GlideApp.with(mContext).load(path).listener(requstlistener).into(mImageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<Drawable> simpleTarget) {
        GlideApp.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(simpleTarget);
    }
    //长宽比超过ratio时自动使用centercrop
    public static void loadImageViewContentWithSizeFixRatio(Context mContext, String path, float ratio, int width, int height, ImageView imageView) {
        GlideApp.with(mContext).load(path).override(width, height).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean
                    isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        int intrinsicWidth = resource.getIntrinsicWidth();
                        int intrinsicHeight = resource.getIntrinsicHeight();
                        if(intrinsicWidth/intrinsicHeight>ratio){
                            GlideApp.with(mContext).load(path).override(width, (int) (height*.6+.5f)).centerCrop().into(imageView);
                        }else if(intrinsicHeight/intrinsicWidth>ratio){
                            GlideApp.with(mContext).load(path).override((int) (width*.6+.5f), (int) (height*1.2+.5f)).centerCrop().into(imageView);
                        }else {
                            GlideUtil.loadImageViewSize(mContext, path, width, height, imageView);
                        }
                    }
                });
                return true;
            }
        }).into(imageView);

    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();

    }

    /**
     * The view to cancel loads and free resources for.
     * @param view
     */
//    public static void clearView(View view) {
//        Glide.clear(view);
//    }

    /*
    *转换器类型
    * Mask,
    NinePatchMask,
    CropTop,
    CropCenter,
    CropBottom,
    CropSquare,
    CropCircle,okhttp3
    ColorFilter,
    Grayscale,
    RoundedCorners,
    Blur,
    Toon,
    Sepia,
    Contrast,
    Invert,
    Pixel,
    Sketch,
    Swirl,
    Brightness,
    Kuawahara,
    Vignette
    * */
}
