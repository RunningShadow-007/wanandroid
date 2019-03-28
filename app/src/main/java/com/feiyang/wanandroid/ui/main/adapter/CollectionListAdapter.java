package com.feiyang.wanandroid.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.databinding.ItemCollectionListBinding;
import com.feiyang.wanandroid.ui.main.model.bean.CollectionData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/3/28 12:04 PM<br>
 * Desc: <br>
 */
public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.ViewHolder> {
    private List<CollectionData.DatasBean> mData;

    private OnItemClickListener<CollectionData.DatasBean> onItemClickListener;

    public CollectionListAdapter(@NonNull List<CollectionData.DatasBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCollectionListBinding binding = ItemCollectionListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CollectionData.DatasBean item = mData.get(position);
        if (item == null) {
            return;
        }
        holder.binding.setItem(item);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, item, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemCollectionListBinding binding;

        ViewHolder(@NonNull ItemCollectionListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener<CollectionData.DatasBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @BindingAdapter(value = {"url"})
    public static void setImage(ImageView image, String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_round)//图片加载出来前，显示的图片
                .fallback(R.drawable.ic_launcher_round) //url为空的时候,显示的图片
                .error(R.drawable.ic_launcher_round);//图片加载失败后，显示的图片
        Glide.with(image)
             .load(url)
             .apply(options)
             .into(image);
    }
}
