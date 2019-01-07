package com.cjwsc.idcm.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.cjwsc.idcm.Utils.LogUtil;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ImageUtils;
import com.cjwsc.idcm.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import io.reactivex.functions.Consumer;


/**
 *
 * @author yiyang
 */
public class TakePhotoUtils {
    @LayoutRes private static int sRes;
    private static PermissionDenyCallBack sCallBack;

    /**
     * 传进来的必须要有三个id
     * R.id.photo_photograph      拍照
     * R.id.photo_albums          图库
     * R.id.photo_cancel    取消
     * @param res
     */
    public static void init(@LayoutRes int res, PermissionDenyCallBack callBack){
        sRes = res;
        sCallBack = callBack;
    }
    public static void showDialog(final FragmentActivity activity, final CallBack callBack){
        if(sRes == 0){
            throw new IllegalArgumentException("没有初始化，需先调用init方法");
        }
        View view = activity.getLayoutInflater().inflate(sRes, null);
        View photograph =  view.findViewById(R.id.photo_photograph);
        View albums =  view.findViewById(R.id.photo_albums);
        View cancel =  view.findViewById(R.id.photo_cancel);


        final Dialog dialog = new Dialog(activity, 0);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setWindowAnimations(android.R.style.Animation_InputMethod);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);

        new RxPermissions(activity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){
                            dialog.show();
                        }else {
                            if(sCallBack!=null)
                                sCallBack.onDeny();
                        }
                    }
                });



        photograph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                capture(activity, callBack);
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                album(activity, callBack);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
    private static void capture(FragmentActivity activity, CallBack callBack){
        ActivityResultFragment.requestCapture(activity, callBack);
    }
    private static void album(FragmentActivity activity, CallBack callBack){
        ActivityResultFragment.requestAlbum(activity, callBack);
    }

    public interface CallBack {
        void onActivityResult(String path);
    }
    public interface PermissionDenyCallBack {
        void onDeny();
    }

    /**
     * 用来处理发送intent并处理回调
     * @author yiyang
     */
    public static class ActivityResultFragment extends Fragment {
        public static final String TAG = "ActivityResultFragment";
        private CallBack callBack;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        public static final int REQUEST_CAPTURE = 556;
        public static final int REQUEST_ALBUM = 557;

        public static void requestCapture(FragmentActivity activity, CallBack callBack) {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String dir = getExternalDirectoryOrCache(activity, Environment.DIRECTORY_PICTURES) +
                    File.separator;
            if(!new File(dir).exists())
                new File(dir).mkdirs();
            String tempImgPath = dir+ UUID.randomUUID().toString() + ".jpg";
            File imgFile = new File(tempImgPath);
            Uri imgUri = null;

            //判断当前手机版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                imgUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".FileProvider", imgFile);
            } else {
                imgUri = Uri.fromFile(imgFile);
            }
            openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
            newInstance(activity,tempImgPath).startActivityForResult(openCameraIntent, REQUEST_CAPTURE, callBack);
        }

        public static void requestAlbum(FragmentActivity activity, CallBack callBack) {
            Intent openAlbumIntent = new Intent(Intent.ACTION_PICK);
            openAlbumIntent.setType("image/*");
            newInstance(activity).startActivityForResult(openAlbumIntent, REQUEST_ALBUM, callBack);
        }

        private static ActivityResultFragment newInstance(FragmentActivity activity) {
            return newInstance(activity, null);
        }
        private static ActivityResultFragment newInstance(FragmentActivity activity, String path) {
            ActivityResultFragment CallBackFragment = findCallBackFragment(activity);
            boolean isNewInstance = CallBackFragment == null;
            if (isNewInstance) {
                CallBackFragment = new ActivityResultFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(CallBackFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            if(path != null)
                CallBackFragment.mPath = path;
            return CallBackFragment;
        }

        public String mPath;
        private static ActivityResultFragment findCallBackFragment(FragmentActivity activity) {
            return (ActivityResultFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        }

        public void startActivityForResult(Intent intent, int requestCode, CallBack callBack) {
            this.callBack = callBack;
            startActivityForResult(intent, requestCode);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode != Activity.RESULT_OK)
                return;
            if (this.callBack == null)
                return;
            if (REQUEST_ALBUM == requestCode) {
                if(data == null)
                    return;
                Uri uri = geturi(getActivity().getApplicationContext(), data);
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().managedQuery(uri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);// 图片在的路径
                this.callBack.onActivityResult(getGifFirFramePath(getContext(), path));
            } else if (REQUEST_CAPTURE == requestCode) {
//                Uri uri = geturi(getActivity().getApplicationContext(), data);
//                String[] proj = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getActivity().managedQuery(uri, proj, null, null, null);
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor.moveToFirst();
//                String path = cursor.getString(column_index);// 图片在的路径
//                this.callBack.onActivityResult(path);
                if(mPath != null && new File(mPath).exists()){
                    this.callBack.onActivityResult(mPath);
                }
            }
        }

        /**
         * 解决小米手机上获取图片路径为null的情况
         * @param intent
         * @return
         */
        public Uri geturi(Context context, Intent intent) {
            Uri uri = intent.getData();
            String type = intent.getType();
            if (uri.getScheme().equals("file") && (type.contains("image/"))) {
                String path = uri.getEncodedPath();
                if (path != null) {
                    path = Uri.decode(path);
                    ContentResolver cr = context.getContentResolver();
                    StringBuffer buff = new StringBuffer();
                    buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                            .append("'" + path + "'").append(")");
                    Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{MediaStore.Images.ImageColumns._ID},
                            buff.toString(), null, null);
                    int index = 0;
                    for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                        index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                        // set _id value
                        index = cur.getInt(index);
                    }
                    if (index == 0) {
                        // do nothing
                    } else {
                        Uri uri_temp = Uri
                                .parse("content://media/external/images/media/"
                                        + index);
                        if (uri_temp != null) {
                            uri = uri_temp;
                        }
                    }
                }
            }
            return uri;
        }
    }

    public static String getGifFirFramePath(Context context, String path){
        if(path == null)return null;
        if(!path.endsWith(".gif")){
            return path;
        }
        String dir = getExternalDirectoryOrCache(context, Environment.DIRECTORY_PICTURES) +
                File.separator;
        if(!new File(dir).exists())
            new File(dir).mkdirs();
        String tempImgPath = dir+ UUID.randomUUID().toString() + ".jpg";
        boolean save = ImageUtils.save(loadGifFirstBitmap(path), tempImgPath, Bitmap.CompressFormat.JPEG);
        return save?tempImgPath:path;
    }
    public static Bitmap loadGifFirstBitmap(String path) {
        Bitmap bitmap = null;
        try {
            Movie movie = Movie.decodeStream(new FileInputStream(path));
            //Bitmap.Config.ARGB_8888 这里是核心，如果出现图片显示不正确，就换编码试试
            bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            movie.draw(canvas, 0, 0);
            canvas.save();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return bitmap;
        }
    }
    /**
     * 获取SD卡缓存目录
     * @param context 上下文
     * @param type 文件夹类型 如果为空则返回 /storage/emulated/0/Android/data/app_package_name/cache
     *             否则返回对应类型的文件夹如Environment.DIRECTORY_PICTURES 对应的文件夹为 .../data/app_package_name/files/Pictures
     * {@link android.os.Environment#DIRECTORY_MUSIC},
     * {@link android.os.Environment#DIRECTORY_PODCASTS},
     * {@link android.os.Environment#DIRECTORY_RINGTONES},
     * {@link android.os.Environment#DIRECTORY_ALARMS},
     * {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
     * {@link android.os.Environment#DIRECTORY_PICTURES}, or
     * {@link android.os.Environment#DIRECTORY_MOVIES}.or 自定义文件夹名称
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
