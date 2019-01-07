package com.cjwsc.idcm.Utils.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.base.application.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ${zipp} on 2017/12/28.
 * 功能描述：
 */

public class SaveImgUtil {
    //保存文件到指定路径

    /*
     * 保存文件，文件名为当前日期
     */
    public static boolean saveBitmap(Context context,Bitmap bitmap, String bitName){
        String fileName ;
        File file ;
        if(Build.BRAND .equals("Xiaomi") ){ // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/"+bitName ;
        }else{  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/"+bitName ;
        }
        file = new File(fileName);

        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out))
            {
                out.flush();
                out.close();
// 插入图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);
                return true;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;

        }
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));

        return true;

    }


    //保存文件到指定路径
    public static Uri saveImageToPath(Context context,String pathname, Bitmap bmp) {
        // 首先保存图片
        String storePath="";
        if(!TextUtils.isEmpty(pathname)){
            storePath = getExternalDirectoryOrCache(context, pathname) + File.separator;

        }else{
            storePath = getExternalDirectoryOrCache(context, Environment.DIRECTORY_PICTURES) + File.separator;

        }
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return uri;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 获取SD卡缓存目录
     * @param context 上下文
     * @param type 文件夹类型 如果为空则返回 /storage/emulated/0/Android/data/app_package_name/cache
     *             否则返回对应类型的文件夹如Environment.DIRECTORY_PICTURES 对应的文件夹为 .../data/app_package_name/files/Pictures
     * {@link Environment#DIRECTORY_MUSIC},
     * {@link Environment#DIRECTORY_PODCASTS},
     * {@link Environment#DIRECTORY_RINGTONES},
     * {@link Environment#DIRECTORY_ALARMS},
     * {@link Environment#DIRECTORY_NOTIFICATIONS},
     * {@link Environment#DIRECTORY_PICTURES}, or
     * {@link Environment#DIRECTORY_MOVIES}.or 自定义文件夹名称
     * @return 缓存目录文件夹 或 null（无SD卡或SD卡挂载失败）
     */
    public static File getExternalDirectoryOrCache(Context context, String type) {
        File appCacheDir = null;
        if( Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (TextUtils.isEmpty(type)){
                appCacheDir = context.getExternalCacheDir();
            }else {
                appCacheDir = context.getExternalFilesDir(type);
            }

            if (appCacheDir == null){// 有些手机需要通过自定义目录
                appCacheDir = new File(Environment.getExternalStorageDirectory(),"Android/data/"+context.getPackageName()+"/cache/"+type);
            }

            if (appCacheDir == null){
                LogUtil.e("getExternalDirectory","getExternalDirectory fail ,the reason is sdCard unknown exception !");
            }else {
                if (!appCacheDir.exists()&&!appCacheDir.mkdirs()){
                    LogUtil.e("getExternalDirectory","getExternalDirectory fail ,the reason is make directory fail !");
                }
            }
        }else {
            LogUtil.e("getExternalDirectory","getExternalDirectory fail ,the reason is sdCard nonexistence or sdCard mount fail !");
        }
        return appCacheDir;
    }
}
