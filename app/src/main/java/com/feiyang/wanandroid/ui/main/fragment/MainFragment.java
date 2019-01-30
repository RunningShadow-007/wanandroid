package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.base.BaseItem;
import com.feiyang.wanandroid.core.constants.LoadingType;
import com.feiyang.wanandroid.core.util.ViewModelUtils;
import com.feiyang.wanandroid.databinding.FragmentMainBinding;
import com.feiyang.wanandroid.ui.main.adapter.ArticleAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.HeaderData;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 1:48 PM<br>
 * Desc:主页 <br>
 */
public class MainFragment extends BaseFragment {

    private int mCurPage = 0;

    private MainViewModel mVm;

    private FragmentMainBinding mBinding;

    private ArticleAdapter mAdapter;

    private List<BaseItem> mData = new ArrayList<>();

    private LoadingType mLoadingType = LoadingType.LOADING_ORIGIN;

    private BaseItem mHeaderData = null;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm = ViewModelUtils.obtainViewModel(this, MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater, container, false);
        initViews();
        observeData();
        loadData();
        return mBinding.getRoot();
    }


    @Override
    protected void observeData() {
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
                    case LOADING_REFRESH:
                        if (!loading) {
                            mBinding.refresh.finishRefresh();
                        }
                        break;
                }
            }
        });

        mVm.isLoadFailed.observe(this, aVoid -> {
            mCurPage--;
            if (mCurPage < 0) {
                mCurPage = 0;
            }
        });

        mVm.bannerList.observe(this, bannerData -> {
            mHeaderData = new HeaderData(bannerData);
            mData.add(0, mHeaderData);
            mAdapter.notifyItemInserted(0);
        });

        mVm.articleList.observe(this, articleBeans -> {
            if (articleBeans != null && articleBeans.size() > 0) {
                if (mCurPage > 0) {
                    int oldSize = mData.size();
                    mData.addAll(articleBeans);
                    mAdapter.notifyItemRangeInserted(oldSize, articleBeans.size());
                } else {
                    mData.clear();
                    if (mHeaderData != null) {
                        mData.add(0, mHeaderData);
                    } else {
                        mVm.getBannerList();
                    }
                    mData.addAll(articleBeans);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        mVm.collectData.observe(this, data -> {
            if (data != null) {
                int index = mData.indexOf(data);
                if (index != -1) {
                    data.setCollect(true);
                    mAdapter.notifyItemChanged(index);
                }
            }
        });

        mVm.uncollectData.observe(this,data->{
            if (data != null) {
                int index = mData.indexOf(data);
                if (index != -1) {
                    data.setCollect(false);
                    mAdapter.notifyItemChanged(index);
                }
            }
        });

        mVm.toast.observe(this, this::showToast);
    }

    @Override
    protected void loadData() {
        mVm.getArticleList(mCurPage);
    }

    @Override
    protected void initViews() {
        mLoadingType = LoadingType.LOADING_ORIGIN;
        if (mAdapter == null) {
            mAdapter = new ArticleAdapter(mData);
            mAdapter.setOnItemClickListener((view, data, pos) -> {
                if (data instanceof ArticlesData.ArticleBean) {
                    ArticlesData.ArticleBean ab       = (ArticlesData.ArticleBean) data;
                    PageName                 pageName = PageName.WEB;
                    pageName.pageParam = ab;
                    startPage(pageName);
                }
            });
            mAdapter.setOnCollectListener((view, data, pos) -> {
                if (data instanceof ArticlesData.ArticleBean) {
                    ArticlesData.ArticleBean ab = (ArticlesData.ArticleBean) data;
                    if (ab.isCollect())
                        mVm.uncollect(ab, false);
                    else
                        mVm.collectArticle(ab);
                }
            });
        }

        mBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rv.setItemAnimator(new DefaultItemAnimator());
        mBinding.rv.setAdapter(mAdapter);

        mBinding.refresh.setRefreshHeader(new ClassicsHeader(mContext));
        mBinding.refresh.setRefreshFooter(new ClassicsFooter(mContext));

        mBinding.refresh.setOnRefreshListener(refreshLayout -> {
            mLoadingType = LoadingType.LOADING_REFRESH;
            mCurPage = 0;
            mVm.getArticleList(mCurPage);
        });

        mBinding.refresh.setOnLoadMoreListener(refreshLayout -> {
            mLoadingType = LoadingType.LOADING_MORE;
            mCurPage++;
            mVm.getArticleList(mCurPage);
            mBinding.refresh.setEnableLoadMore(false);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVm.dispose();
    }
}
