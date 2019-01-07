package com.cjwsc.idcm.Utils;

import android.Manifest;

import com.cjwsc.idcm.base.BaseActivity;
import com.cjwsc.idcm.base.BaseFragment;
import com.cjwsc.idcm.base.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * Created by Long
 * on 2016/12/8.
 */

public class PermissionUtil {


    public interface RequestPermission {
        void onRequestPermissionSuccess();
        void onRequestPermissionFailed();
    }
//
    public static <T> LifecycleTransformer<T> bindToLifecycle(BaseView view) {
        if (view instanceof BaseActivity) {
            return ((BaseActivity) view).<T>bindToLifecycle();
        } else if (view instanceof BaseFragment) {
            return ((BaseFragment) view).<T>bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }

    }

    /**
     * 一次性请求所有需要的权限
     * @param requestPermission
     * @param rxPermissions
     * @param view
     */
    public static void requestAllPermission(final RequestPermission requestPermission, RxPermissions rxPermissions, BaseView view) {
       /* rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .compose(PermissionUtil.<Boolean>bindToLifecycle(view))
                .subscribe(aBoolean -> {
                    if(aBoolean) {
                        requestPermission.onRequestPermissionSuccess();
                    } else {
                        requestPermission.onRequestPermissionFailed();
                    }
                });*/
    }

    /**
     * 申请相机权限
     * @param requestPermission
     * @param rxPermissions
     * @param view
     */
    public static void requestCameraPermission(final RequestPermission requestPermission, RxPermissions rxPermissions, BaseView view) {
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.CAMERA);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            /*rxPermissions
                    .request(Manifest.permission.CAMERA)
                    .compose(PermissionUtil.<Boolean>bindToLifecycle(view))//使用RXlifecycle,使subscription和activity一起销毁
                    .subscribe(aBoolean -> {
                        if(aBoolean) {
                            requestPermission.onRequestPermissionSuccess();
                        } else {
                            requestPermission.onRequestPermissionFailed();
                        }
                    });*/
        }
    }

    /**
     * 单独申请外部存储的操作
     * @param requestPermission
     * @param rxPermissions
     * @param view
     */
    public static void requestWriteExternalStorage(final RequestPermission requestPermission, RxPermissions rxPermissions, BaseView view) {
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    .compose(PermissionUtil.<Boolean>bindToLifecycle(view))//使用RXlifecycle,使subscription和activity一起销毁
                    .subscribe(aBoolean -> {
                        if(aBoolean) {
                            requestPermission.onRequestPermissionSuccess();
                        } else {
                            requestPermission.onRequestPermissionFailed();
                        }
                    });
        }
    }
}
