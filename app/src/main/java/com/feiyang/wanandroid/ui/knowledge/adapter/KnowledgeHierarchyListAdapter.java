package com.feiyang.wanandroid.ui.knowledge.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.databinding.ItemKnowledgeHierarchyBinding;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/28 4:45 PM<br>
 * Desc: <br>
 */
public class KnowledgeHierarchyListAdapter extends RecyclerView.Adapter<KnowledgeHierarchyListAdapter.ViewHolder> {
    private List<KnowledgeHierarchyData> mData;
    private OnItemClickListener<KnowledgeHierarchyData>onItemClickListener;

    public KnowledgeHierarchyListAdapter(@NonNull List<KnowledgeHierarchyData> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKnowledgeHierarchyBinding binding = ItemKnowledgeHierarchyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KnowledgeHierarchyData item = getItem(position);
        if (item == null) {
            return;
        }

        holder.binding.tvHierarchyName.setText(item.getName());

        StringBuilder builder = new StringBuilder();
        if (item.getChildren() != null && item.getChildren().size() > 0) {
            for (KnowledgeHierarchyData.ChildrenBean child : item.getChildren()) {
                if (child.getVisible() == 1) {
                    builder.append(child.getName())
                           .append("    ");
                }
            }
        }
        holder.binding.tvHierarchyItems.setText(builder.toString());

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

    public KnowledgeHierarchyData getItem(int pos) {
        return mData.size() == 0 ? null : mData.get(pos);
    }

    public void setOnItemClickListener(OnItemClickListener<KnowledgeHierarchyData> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemKnowledgeHierarchyBinding binding;

        public ViewHolder(@NonNull ItemKnowledgeHierarchyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
