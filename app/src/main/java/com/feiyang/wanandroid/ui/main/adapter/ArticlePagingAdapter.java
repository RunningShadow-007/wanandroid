package com.feiyang.wanandroid.ui.main.adapter;

import android.view.ViewGroup;

import com.feiyang.wanandroid.base.BaseItem;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/30 5:16 PM<br>
 * Desc: <br>
 */
public class ArticlePagingAdapter extends PagedListAdapter<BaseItem, RecyclerView.ViewHolder> {
    private List<? extends BaseItem> mData;


    public ArticlePagingAdapter() {
        super(new Comparator());
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public BaseItem getItem(int pos) {
        return mData.get(pos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    static class Comparator extends DiffUtil.ItemCallback<BaseItem> {


        @Override
        public boolean areItemsTheSame(@NonNull BaseItem oldItem, @NonNull BaseItem newItem) {
            if (oldItem instanceof ArticlesData.ArticleBean && newItem instanceof ArticlesData.ArticleBean) {
                ArticlesData.ArticleBean o = (ArticlesData.ArticleBean) oldItem;
                ArticlesData.ArticleBean n = (ArticlesData.ArticleBean) newItem;
                return o.getId() == n.getId();
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull BaseItem oldItem, @NonNull BaseItem newItem) {
            if (oldItem instanceof ArticlesData.ArticleBean && newItem instanceof ArticlesData.ArticleBean) {
                ArticlesData.ArticleBean o = (ArticlesData.ArticleBean) oldItem;
                ArticlesData.ArticleBean n = (ArticlesData.ArticleBean) newItem;
                return o.getDesc().equals(n.getDesc());
            }
            return false;
        }
    }
}
