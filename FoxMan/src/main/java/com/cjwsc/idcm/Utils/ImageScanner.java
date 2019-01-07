package com.cjwsc.idcm.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

/**
 * 描述:扫描手机图片
 * author: Zhujun
 * Date: 2016/6/29 13:57
 */
public class ImageScanner {
    private Context mContext;

    public Handler mHandler;

    public ImageScanner(Context context) {
        this.mContext = context;
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ImageScanner.this.callBack.scanComplete((Cursor) msg.obj);
            }
        };
    }

    public ScanCompleteCallBack callBack;

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
     */
    public void scanImages(final ScanCompleteCallBack callback) {
        this.callBack = callback;

        new Thread((new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = mContext.getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                //  Cursor mCursor = mContentResolver.query(mImageUri, null, null, null, null);

                //利用Handler通知调用线程
                Message msg = mHandler.obtainMessage();
                msg.obj = mCursor;
                mHandler.sendMessage(msg);
            }
        })).start();
    }


    public interface ScanCompleteCallBack {
        void scanComplete(Cursor cursor);
    }

    /**
     * 获取指定文件夹下的图片信息
     *
     * @param path
     */
    public void scanImageFromFile(final String path, final ScanCompleteCallBack callback) {
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callback.scanComplete((Cursor) msg.obj);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentResolver mContentResolver = mContext.getContentResolver();
                Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.DATE_MODIFIED}, MediaStore.Images.Media.MIME_TYPE + "=? AND " + MediaStore.Images.Media.DATA + " like ? ", new String[]{"image/jpeg", "%" + path + "%"},
                        MediaStore.Images.Media.DATE_MODIFIED + " DESC"); // 降序排列

                Message msg = mHandler.obtainMessage();
                msg.obj = mCursor;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    public void dishandler() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

}
