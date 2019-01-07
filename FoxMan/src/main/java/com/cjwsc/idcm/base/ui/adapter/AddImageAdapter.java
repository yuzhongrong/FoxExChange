package com.cjwsc.idcm.base.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cjwsc.idcm.R;
import com.cjwsc.idcm.model.bean.PictureInfo;

import java.util.List;

/**
 * Created by ASUS1 on 2017/9/18.
 */

public class AddImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int limit;
    private  LayoutInflater mInflater;
    public List<PictureInfo> datas;
    public Context mContext;
    public AddImageAdapter(Context context, int limit, List<PictureInfo> datas) {
        this.limit=limit;
        this.datas=datas;
        this.mContext=context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_add_image, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder holder1 = (ItemViewHolder) holder;
        holder1.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datas==null || datas.size()==0){

                } else if(datas.size()<limit && position==datas.size()){
                    if(mOnMyItemClickListener!=null){
                        mOnMyItemClickListener.addPicture();
                    }
                }
                if(datas==null || datas.size()==0 || (datas.size()<limit&& position==datas.size()-1)){
                    if(mOnMyItemClickListener!=null){
                        mOnMyItemClickListener.addPicture();
                    }
                }else{
                    //打开图片
                }
            }
        });

        holder1.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datas.size()>0){
                    mOnMyItemClickListener.delete(position);
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        holder1.mTvSize.setText(datas.size()+"/"+limit);
        if(datas==null || datas.size()==0){
            Glide.with(mContext).load(R.mipmap.icon_upload).into(holder1.imageView);
            holder1.mTvSize.setVisibility(View.VISIBLE);
            holder1.deleteImage.setVisibility(View.INVISIBLE);

        }else if(datas.size()<limit){
            if(position==datas.size()){//最后一个
                Glide.with(mContext).load(R.mipmap.icon_upload).into(holder1.imageView);
                holder1.mTvSize.setVisibility(View.VISIBLE);
                holder1.deleteImage.setVisibility(View.INVISIBLE);
            }else{
                Glide.with(mContext).load(datas.get(position).getPath()).into(holder1.imageView);
                holder1.deleteImage.setVisibility(View.VISIBLE);
                holder1.mTvSize.setVisibility(View.GONE);
            }
        }else if(datas.size()>=limit){
            Glide.with(mContext).load(datas.get(position).getPath()).into(holder1.imageView);
            holder1.mTvSize.setVisibility(View.GONE);
            holder1.deleteImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if(datas==null || datas.size()==0){
            return 1;
        }else if(datas.size()<limit){
            return datas.size()+1;
        }else if(datas.size()>=limit){
            return limit;
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public  ImageView imageView;
        public  ImageView deleteImage;
        public TextView mTvSize;

        public ItemViewHolder(View itemView) {
            super(itemView);
           imageView= (ImageView) itemView.findViewById(R.id.iv_small_image);
            deleteImage= (ImageView) itemView.findViewById(R.id.iv_delete);
            mTvSize= (TextView) itemView.findViewById(R.id.tv_size);
        }
    }

    public interface OnMyItemClickListener{
        void delete(int position);
        void addPicture();
    }
    private  OnMyItemClickListener mOnMyItemClickListener;

    public void setOnMyItemClickListener(OnMyItemClickListener l){
        mOnMyItemClickListener=l;
    }
}
