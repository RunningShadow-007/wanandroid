package com.feiyang.wanandroid.ui.main.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.core.util.SizeUtils;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/5 1:32 PM<br>
 * Desc: <br>
 */
public class NavTagAdapter extends RecyclerView.Adapter<NavTagAdapter.ViewHolder> {
    private List<ArticlesData.ArticleBean> mData;

    private int[] colors = {R.color.blue, R.color.deep_red, R.color.light_tab_bac,
            R.color.card_color, R.color.yellow, R.color.blackish_green};

    private OnItemClickListener<ArticlesData.ArticleBean> onItemClickListener;

    public NavTagAdapter(@NonNull List<ArticlesData.ArticleBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView                          textView = new TextView(parent.getContext());

        FlexboxLayoutManager.LayoutParams params   = new FlexboxLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, SizeUtils.dp2px(35));
        int                               margin   = SizeUtils.dp2px(5);
        params.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(params);

        textView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.colorBackground));

        int padding = SizeUtils.dp2px(5);
        textView.setPadding(padding, padding, padding, padding);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(13);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesData.ArticleBean item    = mData.get(position);
        Context                  context = holder.textView.getContext();
        if (item != null) {
            holder.textView.setText(item.getTitle());
            holder.textView.setTextColor(ContextCompat.getColor(context, colors[position % colors.length]));
            holder.textView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, item, holder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener<ArticlesData.ArticleBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull TextView itemView) {
            super(itemView);
            this.textView = itemView;
        }
    }
}
