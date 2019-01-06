package com.feiyang.wanandroid.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.databinding.ItemRightBinding;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.NaviData;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/5 1:26 PM<br>
 * Desc: <br>
 */
public class NavListAdapter extends RecyclerView.Adapter<NavListAdapter.ViewHolder> {
    private List<NaviData> mData;

    private OnItemClickListener<ArticlesData.ArticleBean> onTagClickListener;

    public NavListAdapter(@NonNull List<NaviData> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRightBinding binding = ItemRightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context  context = holder.binding.getRoot().getContext();
        NaviData data    = mData.get(position);
        if (data == null)
            return;
        holder.binding.tvCate.setText(data.getName());
        FlexboxLayoutManager manager = new FlexboxLayoutManager(context);
        manager.setFlexDirection(FlexDirection.ROW);
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setJustifyContent(JustifyContent.FLEX_START);
        holder.binding.rv.setLayoutManager(manager);
        NavTagAdapter adapter = new NavTagAdapter(data.getArticles());
        adapter.setOnItemClickListener((view, data1, pos) -> {
            if (onTagClickListener != null) {
                onTagClickListener.onItemClick(view, data1, pos);
            }
        });
        holder.binding.rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnTagClickListener(OnItemClickListener<ArticlesData.ArticleBean> onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRightBinding binding;

        public ViewHolder(@NonNull ItemRightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
