package com.feiyang.wanandroid.ui.main.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.core.util.SizeUtils;
import com.feiyang.wanandroid.ui.main.model.bean.NaviData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/5 1:08 PM<br>
 * Desc: <br>
 */
public class NavTitleAdapter extends RecyclerView.Adapter<NavTitleAdapter.ViewHolder> {
    private List<NaviData> mData;

    public NavTitleAdapter(@NonNull List<NaviData> data) {
        this.mData = data;
    }

    private OnItemClickListener<NaviData> onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView                     textView = new TextView(parent.getContext());
        ViewGroup.MarginLayoutParams params   = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(50));
        params.topMargin = SizeUtils.dp2px(1);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setBackground(ContextCompat.getDrawable(parent.getContext(), R.drawable.selector_item_left));
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NaviData data    = mData.get(position);
        Context  context = holder.textView.getContext();
        if (data == null)
            return;
        int colorSelected   = ContextCompat.getColor(context, R.color.white);
        int colorUnselected = ContextCompat.getColor(context, R.color.common_touch_bg);

        holder.textView.setBackgroundColor(data.isSelected ? colorSelected : colorUnselected);
        holder.textView.setText(data.getName());
        holder.textView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                if (data.isSelected)
                    return;
                onItemClickListener.onItemClick(holder.textView, data, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener<NaviData> onItemClickListener) {
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
