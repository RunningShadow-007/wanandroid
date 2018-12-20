package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.base.IPage;
import com.feiyang.wanandroid.ui.main.adpter.ArticleAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 1:48 PM<br>
 * Desc:主页 <br>
 */
public class MainFragment extends BaseFragment<IPage.IPageParam> {

    private int mCurPage = 0;

    private ArticleAdapter mAdapter;

    private List<ArticlesData.ArticleBean> mData = new ArrayList<>();

    private boolean isRefresh=false;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_main;
    }



    @Override
    protected void observeData() {
//            vm.articleList.observe(this, articleBeans -> {
//                dataBinding.refresh.setEnableLoadMore(true);
//
//                if (articleBeans != null && articleBeans.size() > 0) {
//                    if (mCurPage == 0) {//下拉刷新操作
//                        mData.clear();
//                        mData.addAll(articleBeans);
//                        mAdapter.notifyDataSetChanged();
//                    } else {//加载更多操作
//                        int oldSize = mData.size();
//                        mData.addAll(articleBeans);
//                        mAdapter.notifyItemRangeInserted(oldSize, articleBeans.size());
//                    }
//                }
//            });
//
//            vm.bannerList.observe(this, bannerData -> {
//
//                if (bannerData != null && !bannerData.isEmpty()) {
//                    dataBinding.banner.play(bannerData);
//                }
//            });
//
//            vm.loading.observe(this, aBoolean -> {
//                if (aBoolean != null) {
//                    if (isRefresh){
//                        if (!aBoolean) {
//                            if (mCurPage == 0) {
//                                dataBinding.refresh.finishRefresh();
//                            } else {
//                                dataBinding.refresh.finishLoadMore();
//                            }
//                        }
//                    }else {
//                        if (aBoolean){
//                            showLoading();
//                        }else {
//                            hideLoading();
//                        }
//                    }
//
//                }
//            });
//
//            vm.isLoadFailed.observe(this, aVoid -> {
//                mCurPage--;
//                dataBinding.refresh.setEnableLoadMore(true);
//            });
//
//            vm.pageCount.observe(this, page -> {
//                if (mCurPage == page) {
//                    Objects.requireNonNull(dataBinding.refresh.getRefreshFooter(), "Refresh footer is null").setNoMoreData(true);
//                }
//            });

    }

    @Override
    protected void loadData() {
//        vm.getArticleList(mCurPage);
//        vm.getBannerList();
    }

    @Override
    protected void initViews() {
        if (mAdapter == null) {
            mAdapter = new ArticleAdapter(mData);
        }
//        dataBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
//        dataBinding.rv.setItemAnimator(new DefaultItemAnimator());
//        dataBinding.rv.setAdapter(mAdapter);
//        dataBinding.rv.setNestedScrollingEnabled(true);
//        //        dataBinding.scroll.setNestedScrollingEnabled(true);
//
//        dataBinding.refresh.setRefreshHeader(new ClassicsHeader(mContext));
//        dataBinding.refresh.setRefreshFooter(new ClassicsFooter(mContext));
//
//        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> {
//            isRefresh=true;
//            mCurPage++;
//            vm.getArticleList(mCurPage);
//            dataBinding.refresh.setEnableLoadMore(false);
//        });
//        dataBinding.refresh.setOnRefreshListener(refreshLayout -> {
//            isRefresh=true;
//            mCurPage = 0;
//            vm.getArticleList(mCurPage);
//        });


    }


}
