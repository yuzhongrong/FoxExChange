package com.cjwsc.idcm.base.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caption.netmonitorlibrary.netStateLib.NetStateReceiver;
import com.cjwsc.idcm.BuildConfig;
import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.GlideUtil;
import com.cjwsc.idcm.Utils.ToastUtil;
import com.cjwsc.idcm.Utils.UIUtils;
import com.cjwsc.idcm.api.NetWorkApi;
import com.cjwsc.idcm.base.ui.pagestatemanager.PageManager;
import com.cjwsc.idcm.net.config.NetWorkConfiguration;
import com.cjwsc.idcm.net.http.HttpUtils;
import com.lqr.emoji.LQREmotionKit;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.loader.ImageLoader;
import com.lqr.imagepicker.view.CropImageView;
import com.lzy.ninegrid.NineGridView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by:hzp on 2017/8/10.
 * 这个类不让修改 请继承 谢谢！
 */

public class BaseApplication extends Application {

    private static Context context;
    private static String mActivityName;


    //smartrefresh 初始化
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                ClassicsHeader header = new ClassicsHeader(context);
                header.setEnableLastTime(false);
                header.setArrowDrawable(null);
                header.setAccentColor(context.getResources().getColor(R.color.c_bdbdbd));
                header.setDrawableMarginRightPx(-context.getResources().getDimensionPixelSize(R.dimen.dp10));
                header.setTextSizeTitle(14);
                return header;
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter footer = new ClassicsFooter(context);//color_a0a2b1
                footer.setDrawableSize(20);
                footer.setAccentColor(getContext().getResources().getColor(R.color.c_bdbdbd));
                footer.setTextSizeTitle(14);
                footer.setDrawableMarginRightPx(-context.getResources().getDimensionPixelSize(R.dimen.dp10));
                return footer;
            }
        });
    }
    //end


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //        Buy收集
        String packageName = context.getPackageName();
        String processName = getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, "041bffdaa4", false);
        // 网络库初始化
        initOkHttpUtils();
        //无敌LOG
        initPrettyFormatStrategy();
        //初始化路由
        initARouter();
        initNineGridView();
        initNetListener();
        ToastUtil.init(this);
        UIUtils.init(this);


        PageManager.initInApp(context);//暂时不用

        //加入litepal数据库
        LitePal.initialize(this);
        //初始化表情
        LQREmotionKit.init(this, (context, path, imageView) ->GlideUtil.loadImageView(context,path,imageView)
                // Glide.with(context).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView)

        );

        //初始化仿微信控件ImagePicker
        initImagePicker();


        /**解决7.0-8.0兼容问题**/
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();



    }


    private void initNetListener() {
        /*开启网络广播监听*/
        NetStateReceiver.registerNetworkStateReceiver(this);
    }


    /**
     * 初始化无敌log
     */
    private void initPrettyFormatStrategy() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("FoxExchange")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    protected void initOkHttpUtils() {
        /**
         *  网络配置
         */
        InputStream cert_stream = getContext().getResources().openRawResource(R.raw.idcm);
        NetWorkConfiguration configuration = new NetWorkConfiguration(this)
                .baseUrl(NetWorkApi.baseUrl)
                .isCache(true)
                .isDiskCache(true)
                .isMemoryCache(true)
                .certificates(cert_stream);
        HttpUtils.setConFiguration(configuration);


        if (getItercepors() != null) HttpUtils.setInterceptoers(getItercepors());
    }


    public List<Interceptor> getItercepors() {
        return null;
    }


    //初始化
    private void initNineGridView() {
        //初始化NineGridView的图片加载器
        NineGridView.setImageLoader(new NineGridView.ImageLoader() {
            @Override
            public void onDisplayImage(Context context, ImageView imageView, String url) {
                GlideUtil.loadImageView(context, url, imageView);
            }

            @Override
            public Bitmap getCacheImage(String url) {
                return null;
            }
        });
    }


    protected void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        //初始化无敌log打印
    }

    @Override
    public void onLowMemory() {
        if (this != null) {
            NetStateReceiver.unRegisterNetworkStateReceiver(this);
            android.os.Process.killProcess(android.os.Process.myPid());
//            exitApp();
        }

        super.onLowMemory();
    }


    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                //  Glide.with(getContext()).load(Uri.parse("file://" + path).toString()).centerCrop().into(imageView);
                GlideUtil.loadImageView(getContext(),"file://" + path,imageView);
            }

            @Override
            public void clearMemoryCache() {

            }
        });   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(6);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }


    public static Context getContext() {
        return context;
    }

    public static String getCurrencyActivity() {
        return mActivityName;
    }

    public static void setCurrencyActivity(String activityName) {
        mActivityName = activityName;
    }


}
