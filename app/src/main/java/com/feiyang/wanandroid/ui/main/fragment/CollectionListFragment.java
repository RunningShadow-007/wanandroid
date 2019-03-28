package com.feiyang.wanandroid.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.core.constants.LoadingType;
import com.feiyang.wanandroid.core.util.ViewModelUtils;
import com.feiyang.wanandroid.databinding.FragmentCollectionListBinding;
import com.feiyang.wanandroid.ui.main.adapter.CollectionListAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.feiyang.wanandroid.core.util.ObjectUtils.isNonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/3/26 6:09 PM<br>
 * Desc: <br>
 */
public class CollectionListFragment extends BaseFragment {
    private FragmentCollectionListBinding mBinding;

    private MainViewModel mVm;

    private int mCurPageNo = 0;

    private CollectionListAdapter mAdapter;

    private List<ArticlesData.ArticleBean> mData = new ArrayList<>();

    private LoadingType mLoadingType;


    public static CollectionListFragment newInstance() {
        return new CollectionListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm = ViewModelUtils.obtainViewModel(this, MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCollectionListBinding.inflate(inflater, container, false);
        observeData();
        loadData();
        initViews();
        return mBinding.getRoot();
    }

    @Override
    protected void observeData() {
        super.observeData();
        mVm.collectDatas.observe(this, collectionData -> {
            if (collectionData != null) {
                mCurPageNo = collectionData.getCurPage();
                if (isNonNull(collectionData.getDatas())) {
                    if (mCurPageNo == 0) {
                        mData.clear();
                    }
                    mData.addAll(collectionData.getDatas());
                    mAdapter.notifyDataSetChanged();
                }
            } else {
                mData.clear();
                mAdapter.notifyDataSetChanged();
            }
        });

        mVm.loading.observe(this, aBoolean -> {
            mVm.loading.observe(this, loading -> {
                if (loading != null) {
                    switch (mLoadingType) {
                        case LOADING_MORE:
                            if (!loading) {
                                mBinding.refresh.finishLoadMore();
                                mBinding.refresh.setEnableLoadMore(true);
                            }
                            break;
                        case LOADING_ORIGIN:
                            if (loading)
                                showLoading();
                            else
                                hideLoading();
                            break;

                    }
                }
            });
        });
    }

    @Override
    protected void loadData() {
        super.loadData();
        mVm.getCollectionList(mCurPageNo);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mLoadingType = LoadingType.LOADING_ORIGIN;
        mBinding.refresh.setEnableRefresh(false);
        mBinding.refresh.setOnLoadMoreListener(refreshLayout -> {
            mLoadingType = LoadingType.LOADING_MORE;
            mCurPageNo++;
            mVm.getCollectionList(mCurPageNo);
            refreshLayout.setEnableLoadMore(false);
        });

        if (mAdapter == null) {
            mAdapter = new CollectionListAdapter(mData);
            mAdapter.setOnItemClickListener((view, data, pos) -> {
                PageName pageName = PageName.WEB;
                pageName.pageParam = data;
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, view, getString(R.string.share_view));
                startPage(pageName, options);
            });
        }
        mBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rv.setItemAnimator(new DefaultItemAnimator());
        mBinding.rv.setAdapter(mAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mCurPageNo = 0;
            mVm.getCollectionList(mCurPageNo);
        }
    }
}
