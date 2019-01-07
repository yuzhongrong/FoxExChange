package com.cjwsc.idcm.base.ui.popwindow;
import android.animation.Animator;
import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.SetImgUtil;
import com.cjwsc.idcm.base.application.BaseApplication;

import razerdp.basepopup.BasePopupWindow;

public class ChoosePicPopWindow extends BasePopupWindow implements View.OnClickListener {
    TextView photoAction;
    TextView cameraAction;
    TextView cancel;
    LinearLayout innerlayout;
    RelativeLayout root;
    private Activity mContext;
    private Uri photoUri;



    private boolean isMuliteUpload;
    public ChoosePicPopWindow(Activity context) {
        super(context);
        mContext=context;
    }

    public ChoosePicPopWindow(Activity context, boolean isMuliteUpload) {
        super(context);
        mContext=context;
        this.isMuliteUpload=isMuliteUpload;
    }

    @Override
    protected Animation initShowAnimation() {
        return AnimationUtils.loadAnimation(BaseApplication.getContext()
        ,R.anim.slide_in_bottom);
    }

    @Override
    protected Animation initExitAnimation() {
        return AnimationUtils.loadAnimation(BaseApplication.getContext()
                , R.anim.slide_out_bottom);
    }

    @Override
    public View getClickToDismissView() {
        return cancel;
    }

    @Override
    public View onCreatePopupView() {

       View root= createPopupById(R.layout.popwindow_choose);

        initView(root);

        return root;
    }

    private void initView(View v) {
       // rxPermissions = new RxPermissions(mContext);
        photoAction= (TextView) v.findViewById(R.id.photo_action);
        cameraAction= (TextView) v.findViewById(R.id.camera_action);
        cancel=(TextView) v.findViewById(R.id.cancel);
        innerlayout= (LinearLayout) v.findViewById(R.id.innerlayout);
        root= (RelativeLayout) v.findViewById(R.id.root);

        initEvent();
    }

    private void initEvent() {

        root.setOnClickListener(this);
        photoAction.setOnClickListener(this);
        cameraAction.setOnClickListener(this);
        cancel.setOnClickListener(this);


    }

    @Override
    public View initAnimaView() {
        return innerlayout;
    }

    @Override
    protected Animator initExitAnimator() {
        return null;
    }


    public void onViewsClick(View view) {

    }

    @Override
    public void onClick(View v) {

        dismiss();
        if(v.getId()==R.id.photo_action) {
          //  SetImgUtil.galleryOp(mContext);
            if(listener!=null){
                listener.addPicter();
            }

        }else if (v.getId()==R.id.camera_action){
            fileTopname= System.currentTimeMillis()+"";
           //SetImgUtil.cameraOp(mContext);
            SetImgUtil.cameraOpMulti(mContext,fileTopname);

        }
    }
    private OnChoicePicterListener listener;

    public interface OnChoicePicterListener{
        void addPicter();
    }
    public void setOnChoicePictuerListener(OnChoicePicterListener listener){
        this.listener=listener;
    }

    private String fileTopname;
    public String getFileName(){
        return fileTopname;
    }
}
