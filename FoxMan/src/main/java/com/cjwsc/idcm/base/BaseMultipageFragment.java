package com.cjwsc.idcm.base;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by yuzhongrong on 2017/9/3.
 */

public abstract class BaseMultipageFragment<M extends BaseModel,P extends BasePresenter> extends BaseFragment<M,P> {

   protected SmartRefreshLayout refreshLayout;
    protected int count;//总页数
    protected int currentpage=1;//默认1
    @Override
    protected void onEvent() {

//       if(refreshLayout!=null){
//
//           refreshLayout.set.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
//               @Override
//               public void onRefresh(RefreshLayout refreshlayout) {
//                   currentpage = 1;
//                   onMultipageUpdate(currentpage);
//               }
//
//               @Override
//               public void onLoadmore(RefreshLayout refreshlayout) {
//
//                   onMultipageUpdate(++currentpage);
//
//               }
//           });
//       }
    }


    public abstract void onMultipageUpdate(int currentpage);

}
