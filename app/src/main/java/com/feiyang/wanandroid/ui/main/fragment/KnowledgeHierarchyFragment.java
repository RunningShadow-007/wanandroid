package com.feiyang.wanandroid.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.base.IPage;
import com.feiyang.wanandroid.core.util.ViewModelUtils;
import com.feiyang.wanandroid.databinding.FragmentKnowledgeHierarchyBinding;
import com.feiyang.wanandroid.ui.knowledge.activity.KnowledgeActivity;
import com.feiyang.wanandroid.ui.knowledge.adapter.KnowledgeHierarchyListAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/17 4:25 PM<br>
 * Desc: <br>
 */
public class KnowledgeHierarchyFragment extends BaseFragment {
    private FragmentKnowledgeHierarchyBinding mBinding;

    private MainViewModel mVm;

    private KnowledgeHierarchyListAdapter mAdapter;

    private List<KnowledgeHierarchyData> mData = new ArrayList<>();

    public static KnowledgeHierarchyFragment newInstance() {
        return new KnowledgeHierarchyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm = ViewModelUtils.obtainViewModel(this, MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentKnowledgeHierarchyBinding.inflate(inflater, container, false);
        initViews();
        observeData();
        loadData();
        return mBinding.getRoot();
    }

    @Override
    protected void observeData() {
        mVm.hierarchyList.observe(this, knowledgeHierarchyData -> {
            if (knowledgeHierarchyData != null && knowledgeHierarchyData.size() > 0) {
                mData.clear();
                mData.addAll(knowledgeHierarchyData);
                mAdapter.notifyDataSetChanged();
            }
        });

        mVm.loading.observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (aBoolean)
                    showLoading();
                else
                    hideLoading();
            }
        });

    }

    @Override
    protected void loadData() {
        mVm.getKnowledgeHierarchyList();
    }

    @Override
    protected void initViews() {
        super.initViews();
        if (mAdapter == null) {
            mAdapter = new KnowledgeHierarchyListAdapter(mData);
            mAdapter.setOnItemClickListener((view, data, pos) -> {
                IPage.PageName page = PageName.KNOWLEDGE;
                page.pageParam = new KnowledgeActivity.Param(data);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, view.findViewById(R.id.tvHierarchyName), getString(R.string.share_view));
                startPage(page, options);
            });
        }
        mBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rv.setItemAnimator(new DefaultItemAnimator());
        mBinding.rv.setAdapter(mAdapter);
    }
}
