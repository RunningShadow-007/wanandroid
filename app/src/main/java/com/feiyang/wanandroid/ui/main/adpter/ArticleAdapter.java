package com.feiyang.wanandroid.ui.main.adpter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feiyang.wanandroid.databinding.ItemArticlesBinding;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/19 7:37 PM<br>
 * Desc: <br>
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<ArticlesData.ArticleBean> mData;

    public ArticleAdapter(@NonNull List<ArticlesData.ArticleBean> data) {
        this.mData = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticlesBinding binding = ItemArticlesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesData.ArticleBean item = getItem(position);
        holder.binding.setItem(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ArticlesData.ArticleBean getItem(int pos) {
        return mData.get(pos);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemArticlesBinding binding;

        public ViewHolder(@NonNull ItemArticlesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
