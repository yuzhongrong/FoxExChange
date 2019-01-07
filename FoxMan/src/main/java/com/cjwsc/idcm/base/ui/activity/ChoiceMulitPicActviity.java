package com.cjwsc.idcm.base.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cjwsc.idcm.Utils.ScreenUtil;
import com.cjwsc.idcm.Utils.SetImgUtil;
import com.cjwsc.idcm.base.BaseActivity;
import com.cjwsc.idcm.base.BaseModel;
import com.cjwsc.idcm.base.BasePresenter;
import com.cjwsc.idcm.base.ui.adapter.AddImageAdapter;
import com.cjwsc.idcm.base.ui.popwindow.ChoosePicPopWindow;
import com.cjwsc.idcm.model.bean.PictureInfo;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

import static com.cjwsc.idcm.constant.IdcmConstant.CHOICEMULITEPICACTIVIY_PAGE;


/**
 * Created by ASUS1 on 2017/9/20.
 */

public abstract class  ChoiceMulitPicActviity<M extends BaseModel, P extends BasePresenter>  extends BaseActivity<M,P>{

    public List<PictureInfo> mImagsList;
    public int spanCount=4;//默认为四列
    private ChoosePicPopWindow popWindow;
    public boolean isSingleChoice=true;//设置是否为单选
    public int limitSize=5;//设置默认最多上传五张
    public int heightOffset=10;//设置默认高的间距10dp
    private static final int PIC_MULTI_REQESTCODE=8;
    private AddImageAdapter mAddAdapter;
    private RecyclerView recyclerView;


    /**继承在onInitView中调用此方法 调用前可先设置默认参数limitSize, heightOffset*/
    public void initMultiChoicePic(RecyclerView recyclerView){
        mImagsList=new ArrayList<>();
        this.recyclerView=recyclerView;
        GridLayoutManager gridManager = new GridLayoutManager(this,spanCount);
        recyclerView.setLayoutManager(gridManager);
        mAddAdapter=new AddImageAdapter(this,limitSize,mImagsList);
        recyclerView.setAdapter(mAddAdapter);
        final int distance = ScreenUtil.dp2px(heightOffset, this);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom=distance;
            }
        });
        popWindow = new ChoosePicPopWindow(this,true);
        popWindow.setOnChoicePictuerListener(new ChoosePicPopWindow.OnChoicePicterListener() {
            @Override
            public void addPicter() {
                int k = limitSize - mImagsList.size();
                galleryOp(k);//权限检查
            }
        });

    }

    /**无Recycleview 直接上传头像*/
    public void initUploadNoRecycleView(){
        mImagsList=new ArrayList<>();
        popWindow = new ChoosePicPopWindow(this,true);
        popWindow.setOnChoicePictuerListener(new ChoosePicPopWindow.OnChoicePicterListener() {
            @Override
            public void addPicter() {
                int k = limitSize - mImagsList.size();
                galleryOp(k);//权限检查
            }
        });
    }

    public void galleryOp(final int size) {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new io.reactivex.Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(aBoolean){
                            //TODO
                            //Toast.makeText(GoodsAssessActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                     /*       Intent intent = new Intent();
                            intent.setClass(GoodsAssessActivity.this, ChoiceMulitePicActiviy.class);
                            intent.putExtra("limitSize", size);
                            GoodsAssessActivity.this.startActivityForResult(intent, 8);*/

                            ARouter.getInstance().build(CHOICEMULITEPICACTIVIY_PAGE).
                                    withInt("limitSize",size)
                                    .withBoolean("isSingleChoice",isSingleChoice)
                                    .navigation(ChoiceMulitPicActviity.this,PIC_MULTI_REQESTCODE);

                        }else {
                            Toast.makeText(ChoiceMulitPicActviity.this, "您拒绝了权限请求", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            switch (requestCode){
                case SetImgUtil.CODE_CAMERA_REQUEST://调用相机

                    File file = SetImgUtil.getOutputMediaFile(this,popWindow.getFileName());
                    String absolutePath = file.getAbsolutePath();
                    PictureInfo pictureInfo = new PictureInfo();
                    pictureInfo.setPath(absolutePath);
                    if(recyclerView==null){//单选
                        mImagsList.clear();
                    }
                    mImagsList.add(pictureInfo);
                    if(recyclerView!=null){
                        //  Uri uri= FileProvider.getUriForFile(this,"com.cjwsc.weplus.fileprovider",SetImgUtil.getOutputMediaFile(this));
                        // String path = SetImgUtil.getRealFilePath(this, uri);//调用工具类中的方法 在索尼z5P 机型无效
                        mAddAdapter.notifyDataSetChanged();

                    }


                    break;
                case PIC_MULTI_REQESTCODE://多图选择
                    if(recyclerView!=null){
                        ArrayList<PictureInfo> pictures = data.getParcelableArrayListExtra("pictures");
                        if(pictures!=null){
                          //  mImagsList.clear();
                            mImagsList.addAll(pictures);
                        }
                        if(mAddAdapter==null){
                            mAddAdapter=new AddImageAdapter(this,5,mImagsList);
                        }else{
                            mAddAdapter.notifyDataSetChanged();
                        }
                   }else{ //单选头像
                        ArrayList<PictureInfo> pictures = data.getParcelableArrayListExtra("pictures");
                        if(pictures!=null){
                           mImagsList.clear();
                            mImagsList.addAll(pictures);
                        }
                    }

                    //上传钱统一压缩
                    break;
            }
        }
    }





    /**获取到图片集合 尚未压缩 */
    public List<PictureInfo> getmImagsLists(){
        return mImagsList;
    }


    List<String> imageS;
    /**压缩图片*/
    public void compressPics(CompressCallback callback,List<String> dats){
     imageS = new ArrayList<>();
        for (PictureInfo pic:mImagsList ){
            imageS.add(pic.getPath());
        }
        if(mImagsList!=null && mImagsList.size()>0){

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    List<File> files = null;
//                        files = Luban.with(ChoiceMulitPicActviity.this).load(imageS).ignoreBy(100).get();
                    if(files==null){
                        return;
                    }
                    for (File file : files) {
                        dats.add(file.getAbsolutePath());
                    }
                    // upload(imageS);
                    callback.onCompressSeccess(dats);

                }
            }.start();

        }
    }

    protected ChoosePicPopWindow getPopWindow(){
        return popWindow;
    }

    protected AddImageAdapter getAdapter(){
        return mAddAdapter;
    }




    public interface CompressCallback{

        void onCompressSeccess(List<String> paths);

        void onCompressFail(String e);


    }

}
