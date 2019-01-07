package com.cjwsc.idcm.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.callback.UploadCallback;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * 设置图片（头像）工具类（相册、拍照、裁剪、上传头像）
 * <p/>
 * 说明：这里是通过传递Bitmap的方式做回调相关操作，
 * 然后把最后裁剪获取的Bitmap对象存在本地某个路径下
 * 在通过这个路径把裁剪后的图片上传。
 * <p/>
 * Created by xz on 2016/3/22.
 */
public class SetImgUtil {


    private static final String TAG = "SetImgUtil";

    private static RxPermissions rxPermissions;
    /**
     * 请求识别码 *
     */
    public static final int CODE_GALLERY_REQUEST = 0xa0; //图库
    public static final int CODE_CAMERA_REQUEST = 0xa1; //相机
    public static final int CODE_CROP_REQUEST = 0xa2; //裁剪
    public static final int CODE_SELECT_SIMPLER = 8; //选择一张
    private static Bitmap sSmallBitmap;


    /**
     * 调用图库
     *
     * @param mActivity
     */
    public static void galleryOp(Activity mActivity) {

        if (rxPermissions == null) rxPermissions = new RxPermissions(mActivity);


        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now

                        LogUtil.i("--------granted------>" + granted);

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        mActivity.startActivityForResult(intent, SetImgUtil.CODE_GALLERY_REQUEST);

                    } else {
                        LogUtil.i("--------granted------>" + granted);
                        // Oups permission denied
                        Toast.makeText(mActivity, "没权限打开相册", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });


    }

    /**
     * 调用相机
     *
     * @param mActivity
     */
    public static void cameraOp(final Activity mActivity) {

        RxPermissions rxPermissions = new RxPermissions(mActivity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (isBadPhone()) {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getOutputMediaFile(mActivity)));
                            } else {
                                Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity));
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            }


                            //
                            //  Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity));
                            //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getOutputMediaFile(mActivity)));

                            // intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                            mActivity.startActivityForResult(intent, SetImgUtil.CODE_CAMERA_REQUEST);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //Toast.makeText(mActivity, mActivity.getString(R.string.permission_creamera), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (isBadPhone()) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getOutputMediaFile(mActivity)));
            } else {
                Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }


            //
            //  Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity));
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getOutputMediaFile(mActivity)));

            // intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            mActivity.startActivityForResult(intent, SetImgUtil.CODE_CAMERA_REQUEST);
        }



    }

    public static final String imaggName = "apply_return.jpg";

    /**
     * 调用相机 非上传图片
     *
     * @param mActivity
     */
    public static void cameraOpMulti(final Activity mActivity, final String name) {

        RxPermissions rxPermissions = new RxPermissions(mActivity);


        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity, name));
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            mActivity.startActivityForResult(intent, SetImgUtil.CODE_CAMERA_REQUEST);
                        } else {
                            //ToastUtil.show(mActivity.getResources().getString(R.string.Unlicensed));
                        }
                    }
                });

    }

    /**
     * 裁剪指定路径的文件
     */
    public static void cropFromChoicePicture(Activity mActivity, Intent data, int width, int height, int aspectX, int aspectY, String path) {
        if (data != null) {
            File file = new File(path);
            if (file.exists()) {
                Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", file);
                //cropPhotoNew(mActivity, uri, width, height, aspectX, aspectY);
                cropPhoto(mActivity, uri, width, height, aspectX, aspectY);
            }
        } else {
            Toast.makeText(mActivity, "调用图库失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 图库回调操作
     *
     * @param mActivity
     * @param data      OnActivityResult()返回的Intent数据
     */
    public static void OnResultFromGallery(Activity mActivity, Intent data, int width, int height, int aspectX, int aspectY) {
        if (data != null) {
            Uri uri = data.getData();
            String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
            cropPhoto(mActivity, uri, width, height, aspectX, aspectY);
        } else {
            //Toast.makeText(mActivity, "调用图库失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 相机回调操作
     */
    public static void OnResultFromCamera(Activity mActivity, int width, int height, int aspectX, int aspectY) {
        Uri uri = null;
        if (isBadPhone()) {
            uri = Uri.fromFile(getOutputMediaFile(mActivity));
        } else {
            uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity));
        }
        String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
        cropPhoto(mActivity, uri, width, height, aspectX, aspectY);
    }
    public static String getPhotoPath(Activity mActivity,String time){
        Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity, time));
        String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
        return path;
    }

    public static void OnResultFromCamera(Activity mActivity, int width, int height, int aspectX, int aspectY, String time) {
        // Uri uri = Uri.fromFile(getOutputMediaFile(mActivity));
        // Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity, time));
        Uri uri = null;
        if (isBadPhone()) {
            uri = Uri.fromFile(getOutputMediaFile(mActivity));
        } else {
            uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity, time));
        }
        String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
        //   cropPhoto(mActivity, uri, width, height, aspectX, aspectY);
        cropPhoto(mActivity, uri, width, height, aspectX, aspectY);

    }


    /**
     * 相机回调操作（需判断取消）
     */
    public static void OnResultFromCamera(Activity mActivity, Intent data, int width, int height, int aspectX, int aspectY) {
        if (data == null) { //确认
            Uri uri = Uri.fromFile(getOutputMediaFile(mActivity));
            String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
            cropPhoto(mActivity, uri, width, height, aspectX, aspectY);
        } else { //取消

        }
    }

    /**
     * 裁剪回调操作
     */
    public static Bitmap onResultFromCrop(Intent data) {
        if (data != null) {
            return data.getParcelableExtra("data");
        }
        return null;
    }

    /**
     * 调用系统的裁剪
     *
     * @param mActivity activity
     * @param uri       图片uri
     * @param width     裁剪宽度
     * @param height    裁剪高度
     * @param aspectX   裁剪比例
     * @param aspectY   裁剪比例
     */
    private static void cropPhoto(Activity mActivity, Uri uri, int width, int height, int aspectX, int aspectY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("return-data", true);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        mActivity.startActivityForResult(intent, CODE_CROP_REQUEST);
    }

    /**
     * 裁剪 图片
     */
    private static void cropPhotoNew(Activity mActivity, Uri uri, int width, int height, int aspectX, int aspectY) {
        try {


            Uri tempCropUri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity, "temp"));
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", aspectX);
            intent.putExtra("aspectY", aspectY);
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", height);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempCropUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            mActivity.startActivityForResult(intent, CODE_CROP_REQUEST);
        } catch (ActivityNotFoundException e) {
            String errorMessage = "Your device doesn't support the crop action!";
            Toast.makeText(mActivity, errorMessage, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  List<ResolveInfo> resInfoList = mActivity.getPackageManager().queryIntentActivities(mActivity, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            activity.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.grantUriPermission(packageName, outUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }*/
    }


    /**
     * 获取本地头像图片文件（相机使用）
     *
     * @return
     */
    public static File getOutputMediaFile(Activity mActivity) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(mActivity, "没有 sd 卡", Toast.LENGTH_SHORT).show();
            return null;
        }
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "HEAD_DIR_UTIL");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String string = mediaStorageDir.getPath() + File.separator + "HEAD_DIR_UTIL.jpg";
        File mediaFile = new File(string);
        return mediaFile;
    }

    /**
     * 获取本地头像图片文件（相机使用） 测试 先随便放置一个文件夹
     *
     * @return
     */
    public static File getOutputMediaFile(Activity mActivity, String name) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(mActivity, "没有 sd 卡", Toast.LENGTH_SHORT).show();
            return null;
        }
/*
        File file = new File(Environment.getExternalStorageDirectory(), name + imaggName);
        if(file.exists()){
            return  file;//测试
        }*/

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "HEAD_DIR_UTIL");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String string = mediaStorageDir.getPath() + File.separator + name + "HEAD_DIR_UTIL.jpg";
        File mediaFile = new File(string);
        return mediaFile;
    }


    /**
     * 保存图片到本地
     *
     * @param bitmap   保存图片
     * @param dir      文件夹名
     * @param path     文件路径
     * @param fileName 文件名
     * @param callback 保存图片的回调函数
     */
    public static void saveImageToNative(final Bitmap bitmap, final String dir, final String path,
                                         final String fileName, final SaveImgCallback callback) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<File> e) throws Exception {
                File appDir = new File(path, dir);
                if (!appDir.exists()) {
                    // 目录不存在 则创建
                    appDir.mkdirs();
                }
                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 保存bitmap至本地
                    fos.flush();
                    fos.close();
                    callback.saveSuccess();
                    e.onNext(file);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    callback.saveFailed();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull File file) {

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 保存图片到本地成功的后续操作
     */
    public interface SaveImgCallback {
        void saveSuccess();

        void saveFailed();


    }

    /**
     * 从相册得到的 url 转换为SD卡中图片路径
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    // ======================= chennl add ==========================
    public static final int REQUEST_ID_CARD_FRONT_GALLERY = 0xa3;//身份证正面相册选择
    public static final int REQUEST_ID_CARD_FRONT_CAMERA = 0xa4;//身份证相机拍照
    public static final int REQUEST_ID_CARD_FRONT_CROP = 0xa5;//身份证正面裁剪
    public static final int REQUEST_ID_CARD_BACK_GALLERY = 0xa6;//身份证背面相册选择
    public static final int REQUEST_ID_CARD_BACK_CAMERA = 0xa7;//身份证背面拍照
    public static final int REQUEST_ID_CARD_BACK_CROP = 0xa8;//身份证背面裁剪

    /**
     * 调用图库
     *
     * @param activity
     * @param requestCode
     */
    public static void galleryOp(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 调用相机
     *
     * @param mActivity
     * @param requestCode
     */
    public static void cameraOp(Activity mActivity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getOutputMediaFile(mActivity)));
        mActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * 图库回调操作
     *
     * @param mActivity
     * @param data      OnActivityResult()返回的Intent数据
     */
    public static String OnResultFromGallery(Activity mActivity, Intent data, String reqeustCode) {
        if (data != null) {
            Uri uri = data.getData();
            String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            saveImage(bitmap, path);
            return path;
            //cropPhoto(mActivity, uri, reqeustCode);
        } else {
            Toast.makeText(mActivity, "调用图库失败", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 相机回调操作
     */
    public static void OnResultFromCamera(Activity mActivity, int reqeustCode) {
        Uri uri = Uri.fromFile(getOutputMediaFile(mActivity));
        String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
        cropPhoto(mActivity, uri, reqeustCode);
    }

    public static Bitmap onResultPathCamera(Activity mActivity, String time) {
        ContentResolver resolver3 = mActivity.getContentResolver();
        Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", getOutputMediaFile(mActivity, time));
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver3, uri);
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
            if (bitmap != null) {
                sSmallBitmap = SetImgUtil.zoomBitmap(bitmap, bitmap.getWidth() / SetImgUtil.compress(bitmap.getWidth(), bitmap.getHeight()),
                        bitmap.getHeight() / SetImgUtil.compress(bitmap.getWidth(), bitmap.getHeight()));
                //释放原始图片占用的内存，防止out of memory异常发生
                bitmap.recycle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
        //将保存在本地的图片取出并缩小后显示在界面上
        return sSmallBitmap;
    }

    public static Bitmap onResultPathGraly(Activity mActivity, String time, Intent data) {
        if (data != null) {
            File fiel = new File(time);
            if (fiel.exists()) {
                Uri uri = FileProvider.getUriForFile(mActivity, "com.swsd.idcm.fileprovider", fiel);
                ContentResolver resolver3 = mActivity.getContentResolver();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver3, uri);
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
                    if (bitmap != null) {
                        sSmallBitmap = SetImgUtil.zoomBitmap(bitmap, bitmap.getWidth() / SetImgUtil.compress(bitmap.getWidth(), bitmap.getHeight()),
                                bitmap.getHeight() / SetImgUtil.compress(bitmap.getWidth(), bitmap.getHeight()));
                        //释放原始图片占用的内存，防止out of memory异常发生
                        bitmap.recycle();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        String path = getRealFilePath(mActivity, uri);//即为当前图片的路径，可在不用裁剪的时候使用
        //将保存在本地的图片取出并缩小后显示在界面上
        return sSmallBitmap;
    }

    /**
     * 调用系统的裁剪
     */
    private static void cropPhoto(Activity mActivity, Uri uri, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        mActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * 压缩图片
     *
     * @param photo
     * @param spath
     */
    public static void saveImage(Bitmap photo, String spath) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断图片是否需要缩放及其缩放比例 500
     */
    public static float compress(int s1, int s2) {
        if (s1 < s2) {
            s1 = s2;
        }
        float i = (float) s1 / 500;
        if (i > 1) {
            return i;
        } else {
            return 1;
        }
    }


    /**
     * Resize the bitmap
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, float width, float height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float scaleWidth = (width / w);
        float scaleHeight = (height / h);
        //缩放图片
        matrix.postScale(scaleWidth, scaleHeight);
        //创建新的图片
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return compressImage(newbmp);
    }

    /**
     * 图片压缩
     *
     * @param image
     * @return
     */
    private static Bitmap compressImage(Bitmap image) {
        if (image == null) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream isBm = new ByteArrayInputStream(bytes);
            Bitmap bitmap = BitmapFactory.decodeStream(isBm);
            return bitmap;
        } catch (OutOfMemoryError e) {
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
    // =========================== end ============================


    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    public static Bitmap returnBitmap(String url) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(url)) {
            URL fileUrl = null;
            try {
                fileUrl = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                if (fileUrl != null) {
                    HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    /**
     * 上传文件封装http://portal_web.jd.wmjtc.com/imager/upload.action
     */
    private static final String MULTIPART_FORM_DATA = "application/octet-stream";  // 指明要上传的文件格式
    //上传头像
    //   public static String loadurl = UPLOAD_HOST + "kissy_upload_json.php";//上传图片

    //本地调试
    public static String loadurl = "http://192.168.10.191:8080/portal-web/imager/upload.action";


    public static String realyloadurl = getRealyUrl(loadurl);//最终的上传地址 需要构造出来

    public static void okloadFile(final Context context, String uploadUrl,
                                  String partName, File file, final UploadCallback callback) {
        //  DebugLog.e(DebugLog.TAG, "文件上传 " + uploadUrl);

        OkHttpClient client = getOkHttpClient();

        // 根据文件格式封装文件
        RequestBody fileBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        // 初始化请求体对象，设置Content-Type以及文件数据流
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(partName, file.getName(), fileBody);
        RequestBody requestBody = builder.build();

        // 发起异步网络请求
        okhttp3.Request request = new okhttp3.Request.Builder().url(uploadUrl).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                try {
                    final String body = response.body().string();
                    final String message = response.message();
                    // DebugLog.e(DebugLog.TAG, "文件上传 响应：" + body);

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onInterSuccess(body, message);
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                final String response = e.getMessage();
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFailure(response);
                        }
                    }
                });
            }
        });
    }


    @NonNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }


    public static String getRealyUrl(String baseurl) {

        //params先加密
        Map<String, String> params = MineParamsUtils.getUploadMap();

        String url = baseurl + "?";
        try {
            String str;
            String value;
            if (params != null && params.keySet() != null) {

                StringBuilder s = new StringBuilder();
                s.append("接口:").append(url).append("\n").append("get入参明细拆分（方便查看）：").append("\n");

                for (String key : params.keySet()) {
                    value = params.get(key);
                    if (value == null) {
                        value = "";
                    }
                    s.append(key).append(" = ").append(value).append("\n");
                    str = URLEncoder.encode(value, "UTF-8");
                    url += ((key + "=" + str) + "&");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = url.substring(0, url.length() - 1);


        return result;


    }

    //判断三星A7
    private static boolean isBadPhone() {
        String brand = android.os.Build.BRAND;// 手机品牌
        String code = android.os.Build.MODEL; // 手机型号
        LogUtil.e("手机品牌" + brand + ",手机型号" + code);
        return TextUtils.equals("samsung", brand) && TextUtils.equals("SM-A7000", code);
    }

//=======================以下是分享页所做的图片压缩处理,有待优化

    /**
     * 图片的缩放方法
     *
     * @param bitmap  ：源图片资源
     * @param maxSize ：图片允许最大空间  单位:KB
     * @return
     */
    public static Bitmap getZoomImage(Bitmap bitmap, double maxSize) {
        if (null == bitmap) {
            return null;
        }
        if (bitmap.isRecycled()) {
            return null;
        }

        // 单位：从 Byte 换算成 KB
        double currentSize = bitmapToByteArray(bitmap, false).length / 1024;
        // 判断bitmap占用空间是否大于允许最大空间,如果大于则压缩,小于则不压缩
        while (currentSize > maxSize) {
            // 计算bitmap的大小是maxSize的多少倍
            double multiple = currentSize / maxSize;
            // 开始压缩：将宽带和高度压缩掉对应的平方根倍
            // 1.保持新的宽度和高度，与bitmap原来的宽高比率一致
            // 2.压缩后达到了最大大小对应的新bitmap，显示效果最好
            bitmap = getZoomImage(bitmap, bitmap.getWidth() / Math.sqrt(multiple), bitmap.getHeight() / Math.sqrt(multiple));
            currentSize = bitmapToByteArray(bitmap, false).length / 1024;
        }
        return bitmap;
    }


    /**
     * 图片的缩放方法
     *
     * @param orgBitmap ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap getZoomImage(Bitmap orgBitmap, double newWidth, double newHeight) {
        if (null == orgBitmap) {
            return null;
        }
        if (orgBitmap.isRecycled()) {
            return null;
        }
        if (newWidth <= 0 || newHeight <= 0) {
            return null;
        }

        // 获取图片的宽和高
        float width = orgBitmap.getWidth();
        float height = orgBitmap.getHeight();
        // 创建操作图片的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(orgBitmap, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    /**
     * bitmap转换成byte数组
     *
     * @param bitmap
     * @param needRecycle
     * @return
     */
    public static byte[] bitmapToByteArray(Bitmap bitmap, boolean needRecycle) {
        if (null == bitmap) {
            return null;
        }
        if (bitmap.isRecycled()) {
            return null;
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bitmap.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {

        }
        return result;
    }

}
