package com.cjwsc.idcm.recylcerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 这是RecyclerView的通用适配器
 *
 * @param <T>
 */
public abstract class BaseRecAdapter<T> extends RecyclerView.Adapter<RecViewHolder> {
    private final int mLayoutId;
    protected final Context mContext;
    public List<T> mDatas;
    //HeaderView, FooterView
    protected View mHeaderView;
    protected View mFooterView;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    /**
     * 获取mDatas
     *
     * @return
     */
    public List<T> getDatas() {
        return mDatas;
    }

    /**
     * 更改mDatas
     *
     * @param datas
     */
    public void setDatas(List<T> datas) {
        this.mDatas = datas;
    }

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public BaseRecAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new RecViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new RecViewHolder(mFooterView);
        }
        return new RecViewHolder(LayoutInflater.from(this.mContext).inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (mHeaderView==null){
                convert(holder, this.mDatas.get(position), position);
            }else {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                convert(holder, this.mDatas.get(position-1), position-1);
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return mDatas == null ? 0 : mDatas.size();
        }else if(mHeaderView == null && mFooterView != null){
            return (mDatas == null ? 0 : mDatas.size()) + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return (mDatas == null ? 0 : mDatas.size()) + 1;
        }else {
            return (mDatas == null ? 0 : mDatas.size()) + 2;
        }
    }

    protected abstract void convert(RecViewHolder holder, T bean, int position);

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public void removeHeaderView(){
        /*mDatas.remove(0);
        notifyDataSetChanged();*/
        mHeaderView=null;
        notifyDataSetChanged();
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0&&mHeaderView!=null) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1&&mFooterView!=null) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }
}
