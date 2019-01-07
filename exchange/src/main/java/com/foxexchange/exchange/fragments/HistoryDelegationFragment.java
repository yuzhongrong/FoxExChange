package com.foxexchange.exchange.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.cjwsc.idcm.base.BaseFragment;
import com.cjwsc.idcm.base.BaseView;
import com.cjwsc.idcm.base.ui.widget.NoScroolListView;
import com.example.exchange.R;
import com.foxexchange.exchange.beans.CurrentDelegationBean;
import com.lqr.adapter.LQRAdapterForAbsListView;
import com.lqr.adapter.LQRViewHolderForAbsListView;
import com.zhl.cbdialog.CBDialogBuilder;
import java.util.ArrayList;

/**
 * created by pzw on 2018/12/20.
 */
public class HistoryDelegationFragment extends BaseFragment{
    private NoScroolListView mEntrustLv;
    private LQRAdapterForAbsListView adapter;
    private PopupWindow popupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_current_delegation;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mEntrustLv = rootView.findViewById(R.id.exchange_lv_entrust);
    }

    @Override
    protected void onEvent() {
        mEntrustLv.setAdapter(adapter = new LQRAdapterForAbsListView<CurrentDelegationBean>(getActivity(), new ArrayList<>(),R.layout.item_current_delegation) {
            @Override
            public void convert(LQRViewHolderForAbsListView helper, CurrentDelegationBean item, int position) {
                helper.getView(R.id.exchange_btn_cancel).setOnClickListener(v -> showCancelDialog());
                helper.getView(R.id.exchange_rl_entrustinfo).setOnClickListener(v -> initPopup());
            }
        });
        for(int i=0;i<16;i++) {
            adapter.addLastItem(new CurrentDelegationBean());
        }
    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }

    private void initPopup() {
        View pop = View.inflate(getActivity(), R.layout.popwindow_order_info, null);
        popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(rootView.findViewById(R.id.exchange_lv_entrust),Gravity.BOTTOM,0,0);
        pop.findViewById(R.id.exchange_tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private void  showCancelDialog(){
        new CBDialogBuilder(mContext, R.layout.dialog_common)
                .setTouchOutSideCancelable(true)
                .showCancelButton(true)
                .setTitle(getString(R.string.str_cancle_entrust))
                .setTitleTextColor(getActivity().getResources().getColor(R.color.white))
                .setMessage(getString(R.string.str_cancle_entrust_content))
                .setConfirmButtonText(getString(R.string.dialog_ok))
                .setConfirmButtonTextColor(mContext.getResources().getColor(R.color.color_0994FE))
                .setCancelButtonText(getString(R.string.dialog_cancel))
                .setCancelButtonTextColor(mContext.getResources().getColor(R.color.black))
                .setCancelable(false)
                .showIcon(false)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL)
                .create().show();
    }


}
