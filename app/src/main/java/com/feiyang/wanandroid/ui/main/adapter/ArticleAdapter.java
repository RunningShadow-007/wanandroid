package com.feiyang.wanandroid.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.feiyang.wanandroid.base.BaseItem;
import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.core.widget.BannerBox;
import com.feiyang.wanandroid.databinding.ItemArticlesBinding;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.HeaderData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import static com.feiyang.wanandroid.core.constants.Constants.TYPE_HEADER;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/19 7:37 PM<br>
 * Desc: <br>
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<? extends BaseItem> mData;

    private OnItemClickListener onItemClickListener;

    public ArticleAdapter(@NonNull List<? extends BaseItem> data) {
        this.mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        BaseItem item = getItem(position);
        return item.getItemType();
    }

    public void setOnItemClickListener(OnItemClickListener<? extends BaseItem> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            BannerBox box = new BannerBox(parent.getContext());
            box.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeadViewHolder(box);
        } else {
            ItemArticlesBinding item = ItemArticlesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseItem item = getItem(position);
        if (holder instanceof ViewHolder) {
            ArticlesData.ArticleBean data = (ArticlesData.ArticleBean) item.getData();
            ((ViewHolder) holder).binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(v, data, holder.getLayoutPosition());
            });
            ((ViewHolder) holder).binding.setItem(data);
        } else if (holder instanceof HeadViewHolder) {
            HeaderData data = (HeaderData) item.getData();
            ((HeadViewHolder) holder).itemView.play(data.getDataList());
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public BaseItem getItem(int pos) {
        return mData.get(pos);
    }

    public boolean hasHeader() {
        return getItem(0) != null && getItem(0).getItemType() == TYPE_HEADER;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemArticlesBinding binding;

        ViewHolder(@NonNull ItemArticlesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        BannerBox itemView;

        HeadViewHolder(@NonNull BannerBox itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

}
