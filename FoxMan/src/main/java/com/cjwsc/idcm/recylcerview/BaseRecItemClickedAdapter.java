package com.cjwsc.idcm.recylcerview;

import android.content.Context;
import android.view.View;


import com.cjwsc.idcm.R;

import java.util.List;

/**
 * 为RecyclerView通用适配器添加点击事件和长按事件
 *
 * @param <T>
 */
public abstract class BaseRecItemClickedAdapter<T> extends BaseRecAdapter<T> {
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    private View.OnClickListener mClickListener;
    private View.OnLongClickListener mLongClickListener;
    private Integer mTag;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public BaseRecItemClickedAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            holder.itemView.setTag(R.id.decor_content_parent, position);
            itemSetLongClick(holder);   //暂时只设置normal条目的点击事件处理
            itemSetClick(holder);
        }
        super.onBindViewHolder(holder, position);
    }

    /**
     * 点击事件的listener传入
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 长按事件的listener传入
     *
     * @param listener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    /**
     * 实现点击事件       //这里条目点击事件需要特别处理下，
     *
     * @param holder
     */
    private void itemSetClick(final RecViewHolder holder) {
        if (this.mItemClickListener != null) {
            if (mClickListener == null) {
                mClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTag = (Integer) v.getTag(R.id.decor_content_parent);
                        mItemClickListener.onItemClick(v, mTag);
                    }
                };
            }
        }
        holder.itemView.setOnClickListener(mClickListener);
    }

    /**
     * 实现长按事件
     *
     * @param holder
     */
    private void itemSetLongClick(final RecViewHolder holder) {
        if (this.mItemLongClickListener != null) {
            if (mLongClickListener == null) {
                mLongClickListener = new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return mItemLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    }
                };
            }
            holder.itemView.setOnLongClickListener(mLongClickListener);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View itemView, int layoutPosition);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View itemView, int layoutPosition);
    }


}
