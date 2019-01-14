package com.feiyang.wanandroid.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.databinding.ItemProjectListBinding;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/13 11:30 AM<br>
 * Desc: <br>
 */
public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {
    private List<ArticlesData.ArticleBean>                mData;

    private OnItemClickListener<ArticlesData.ArticleBean> onItemClickListener;

    public ProjectListAdapter(@NonNull List<ArticlesData.ArticleBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater         inflater = LayoutInflater.from(parent.getContext());
        ItemProjectListBinding binding  = ItemProjectListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesData.ArticleBean item = getItem(position);
        if (item == null)
            return;
        holder.binding.setItem(item);
        holder.binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,item,holder.getLayoutPosition());
                }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ArticlesData.ArticleBean getItem(int pos) {
        return mData.size() == 0 ? null : mData.get(pos);
    }


    public void setOnItemClickListener(OnItemClickListener<ArticlesData.ArticleBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemProjectListBinding binding;

        public ViewHolder(@NonNull ItemProjectListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
