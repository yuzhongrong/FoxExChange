package com.cjwsc.idcm.base.ui.popwindow;

import android.animation.Animator;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.base.application.BaseApplication;
import com.cjwsc.idcm.base.ui.adapter.AssetsSeleteAdapter;
import com.cjwsc.idcm.model.bean.providerbean.UserAssetPropertyBean;
import com.cjwsc.idcm.recylcerview.BaseRecItemClickedAdapter;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class ChooseAssetRecordCategoryPopWindow extends BasePopupWindow implements View.OnClickListener {
    private final List<UserAssetPropertyBean.AssetsDTO> assetsDTOList;
    private int currentPositon;
    TextView cancel;
    LinearLayout innerlayout;
    private Activity mContext;
    private RecyclerView mRecyclerView;
    private AssetsSeleteAdapter mAssetsSeleteAdapter;
    private List<String> mStringList;
    private onChooseAssetCategoryItemListering onChoonseAssetCategory;
    private RelativeLayout mRelativeLayout;
    private View mRoot;
    private TextView mIcon_first;
    private TextView mIconSecond;
    private TextView mIcon_thread;

    public ChooseAssetRecordCategoryPopWindow(Activity context, int positon, List<UserAssetPropertyBean.AssetsDTO> assetsDTOList) {
        super(context);
        mContext = context;
        currentPositon = positon;
        this.assetsDTOList = assetsDTOList;
        bindView();
    }

    private void bindView() {
        /*mRecyclerView = (RecyclerView) mRoot.findViewById(R.id.rc_finalMoney_seletor);
        mRelativeLayout = (RelativeLayout) mRoot.findViewById(R.id.root);*/
        innerlayout = (LinearLayout) mRoot.findViewById(R.id.innerlayout);
        innerlayout.setOnClickListener(this);
        mRoot.findViewById(R.id.rl_all).setOnClickListener(this);
        mIcon_first = (TextView) mRoot.findViewById(R.id.tv_money_btc);
        mRoot.findViewById(R.id.rl_btc).setOnClickListener(this);
        mIconSecond = (TextView) mRoot.findViewById(R.id.tv_money_bch);
        mRoot.findViewById(R.id.rl_bch).setOnClickListener(this);
        mIcon_thread = (TextView) mRoot.findViewById(R.id.tv_money_usd);
        mRoot.findViewById(R.id.rl_usd).setOnClickListener(this);
        mRoot.findViewById(R.id.tv_money_btc).setOnClickListener(this);
        if (currentPositon == 0) {
            mRoot.findViewById(R.id.iv_isShow_all).setVisibility(View.VISIBLE);
        } else if (currentPositon == 1) {
            mRoot.findViewById(R.id.iv_isShow_bct).setVisibility(View.VISIBLE);
        } else if (currentPositon == 2) {
            mRoot.findViewById(R.id.iv_isShow_bch).setVisibility(View.VISIBLE);
        } else if (currentPositon == 3) {
            mRoot.findViewById(R.id.iv_isShow_usd).setVisibility(View.VISIBLE);
        }

//        initView(mRoot);
        mStringList = new ArrayList<>();
        //mStringList.add(mContext.getResources().getString(R.string.all));
        if (assetsDTOList != null) {
            for (int i = 0; i < assetsDTOList.size(); i++) {
                mStringList.add(assetsDTOList.get(i).getAssetsCode());
            }
            mIcon_first.setText(assetsDTOList.get(0).getAssetsCode());
            mIconSecond.setText(assetsDTOList.get(1).getAssetsCode());
            mIcon_thread.setText(assetsDTOList.get(2).getAssetsCode());
        }
    }


    @Override
    protected Animation initShowAnimation() {
        return AnimationUtils.loadAnimation(BaseApplication.getContext()
                , R.anim.slide_in_bottom);
    }

    @Override
    protected Animation initExitAnimation() {
        return AnimationUtils.loadAnimation(BaseApplication.getContext()
                , R.anim.slide_out_bottom);
    }

    @Override
    public View getClickToDismissView() {
        return mRoot.findViewById(R.id.ll_click_close);
    }

    @Override
    public View onCreatePopupView() {
        mRoot = createPopupById(R.layout.selete_money_type_popup);
        return mRoot;
    }

    private void initView(View v) {
        // rxPermissions = new RxPermissions(mContext);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mStringList = new ArrayList<>();
        //mStringList.add(BaseApplication.getContext().getResources().getString(R.string.all));
        for (int i = 0; i < assetsDTOList.size(); i++) {
            mStringList.add(assetsDTOList.get(i).getID());
        }
        mAssetsSeleteAdapter = new AssetsSeleteAdapter(mContext, mStringList, R.layout.item_selete_assetes);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAssetsSeleteAdapter);
//        mAssetsSeleteAdapter.setCurrentPosition(currentPositon);
        initEvent();
    }

    private void initEvent() {
        mAssetsSeleteAdapter.setOnItemClickListener(new BaseRecItemClickedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int layoutPosition) {
                mAssetsSeleteAdapter.setCurrentPosition(layoutPosition);
                onChoonseAssetCategory.setOnItemClickListing(layoutPosition);
            }
        });

    }

    @Override
    public View initAnimaView() {
        return innerlayout;
    }

    @Override
    protected Animator initExitAnimator() {
        return getDefaultSlideFromBottomAnimationSet();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_all) {
            onChoonseAssetCategory.setOnItemClickListing(0);
        } else if (id == R.id.rl_bch) {
            onChoonseAssetCategory.setOnItemClickListing(2);
        } else if (id == R.id.rl_btc) {
            onChoonseAssetCategory.setOnItemClickListing(1);
        } else if (id == R.id.rl_usd) {
            onChoonseAssetCategory.setOnItemClickListing(3);
        }
    }

    public interface onChooseAssetCategoryItemListering {
        void setOnItemClickListing(int position);
    }

    public void setOnChooseAssetCategoryItemClickListeing(onChooseAssetCategoryItemListering onChooseAssetCategoryItemClickListeing) {
        this.onChoonseAssetCategory = onChooseAssetCategoryItemClickListeing;
    }

}
