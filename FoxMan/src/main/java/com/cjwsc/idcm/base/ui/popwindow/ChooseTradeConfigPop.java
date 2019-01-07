package com.cjwsc.idcm.base.ui.popwindow;
import android.animation.Animator;
import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.adapter.FinacTypeAdapter;
import com.cjwsc.idcm.adapter.KlineTradeTypeAdapter;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.model.bean.providerbean.UserAssetPropertyBean;
import com.cjwsc.idcm.recylcerview.BaseRecItemClickedAdapter;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class ChooseTradeConfigPop extends BasePopupWindow implements View.OnClickListener {
    private  List<UserAssetPropertyBean.AssetsDTO> assetsDTOList;
    private  int position;
    TextView photoAction;
    TextView cameraAction;
    TextView cancel;
    LinearLayout root;
    private Activity mContext;
    private Uri photoUri;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mXVals;
    private View mRoot;
    private FinacTypeAdapter mKlineTradeTypeAdapter;
    private ArrayList mStringList;

    public ChooseTradeConfigPop(Activity context) {
        super(context);
        mContext=context;
        initView(mRoot);
    }
    public ChooseTradeConfigPop(Activity context, int w, int h, int positons, List<UserAssetPropertyBean.AssetsDTO> assetsDTOList) {
        super(context, w, h);
        this.position=positons;
        mContext=context;
        this.assetsDTOList=assetsDTOList;
        initView(mRoot);
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

        mRoot = createPopupById(R.layout.trade_type_choose);

        return mRoot;
    }

    private void initView(View v) {
       // rxPermissions = new RxPermissions(mContext);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rc_trade_type);
        root= (LinearLayout) v.findViewById(R.id.root);
        mStringList = new ArrayList<>();
        //mStringList.add(mContext.getResources().getString(R.string.all));
        for (int i = 0; i <assetsDTOList.size() ; i++) {
            mStringList.add(assetsDTOList.get(i).getAssetsCode());
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        mKlineTradeTypeAdapter = new FinacTypeAdapter(mContext,mStringList, R.layout.item_finca_type);
        mKlineTradeTypeAdapter.setSelePosition(position);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mKlineTradeTypeAdapter);
        initEvent();
    }

    private void initEvent() {
        root.setOnClickListener(this);
        mKlineTradeTypeAdapter.setOnItemClickListener(new BaseRecItemClickedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int layoutPosition) {
                mKlineTradeTypeAdapter.setSelePosition(layoutPosition);
                mKlineTradeTypeAdapter.notifyDataSetChanged();
                listener.onSeletion(layoutPosition);
                dismiss();
            }
        });
    }

    @Override
    public View initAnimaView() {
        return mRecyclerView;
    }

    @Override
    protected Animator initExitAnimator() {
        return null;
    }


    public void onViewsClick(View view) {

    }

    @Override
    public void onClick(View v) {


    }
    private onItemSeletionLister listener;

    public interface onItemSeletionLister{
        void onSeletion(int position);
    }
    public void setOnItemSeletionListener(onItemSeletionLister listener){
        this.listener=listener;
    }

}
