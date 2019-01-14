package com.feiyang.wanandroid.ui.knowledge.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.base.IPage;
import com.feiyang.wanandroid.core.constants.LoadingType;
import com.feiyang.wanandroid.core.util.ViewModelUtils;
import com.feiyang.wanandroid.databinding.FragmentKnowledgeListBinding;
import com.feiyang.wanandroid.ui.knowledge.vm.KnowledgeViewModel;
import com.feiyang.wanandroid.ui.main.adapter.ArticleAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.feiyang.wanandroid.core.util.ObjectUtils.isNonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/13 5:16 PM<br>
 * Desc: <br>
 */
public class KnowledgeListFragment extends BaseFragment<KnowledgeListFragment.Param> {
    private KnowledgeViewModel mVm;

    private FragmentKnowledgeListBinding mBinding;

    private int mCid, mCurPageNo = 0;

    private LoadingType mLoadingType = LoadingType.LOADING_ORIGIN;

    private List<ArticlesData.ArticleBean> mData = new ArrayList<>();

    private ArticleAdapter mAdapter;

    public static KnowledgeListFragment newInstance(int cid) {
        Bundle args  = new Bundle();
        Param  param = new Param(cid);
        args.putParcelable(IPage.PAGE_PARAM, param);
        KnowledgeListFragment fragment = new KnowledgeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (param != null) {
            mCid = param.cid;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVm = ViewModelUtils.obtainViewModel(this, KnowledgeViewModel.class);
        mBinding = FragmentKnowledgeListBinding.inflate(inflater, container, false);
        initViews();
        observeData();
        loadData();
        return mBinding.getRoot();
    }

    @Override
    protected void observeData() {
        mVm.hierarchyArticleList.observe(this, data -> {
            if (isNonNull(data.getDatas())) {
                mCurPageNo = data.getCurPage();
                mBinding.swiper.setEnableLoadMore(mCurPageNo < data.getPageCount());
                if (mLoadingType == LoadingType.LOADING_MORE) {
                    if (isNonNull(data.getDatas())) {
                        mData.addAll(data.getDatas());
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (isNonNull(data.getDatas())) {
                        mData.clear();
                        mData.addAll(data.getDatas());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        mVm.loading.observe(this, aBoolean -> {
            if (aBoolean != null) {
                switch (mLoadingType) {
                    case LOADING_MORE:
                        if (!aBoolean)
                            mBinding.swiper.finishLoadMore();
                        break;
                    case LOADING_ORIGIN:
                        if (aBoolean) {
                            showLoading();
                        } else {
                            hideLoading();
                        }
                        break;
                    case LOADING_REFRESH:
                        if (!aBoolean)
                            mBinding.swiper.finishRefresh();
                        break;
                }
            }
        });

        mVm.isLoadFailed.observe(this, aVoid -> {
            mCurPageNo--;
            if (mCurPageNo < 0) {
                mCurPageNo = 0;
            }
            if (mLoadingType == LoadingType.LOADING_MORE)
                mBinding.swiper.setEnableLoadMore(true);
        });


    }

    @Override
    protected void loadData() {
        mVm.getKnowledgeArticleList(mCurPageNo, mCid);
    }

    @Override
    protected void initViews() {
        mBinding.swiper.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mCurPageNo++;
                mLoadingType = LoadingType.LOADING_MORE;
                refreshLayout.setEnableLoadMore(false);
                loadData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mCurPageNo = 1;
                mLoadingType = LoadingType.LOADING_REFRESH;
                loadData();
            }
        });
        if (mData == null) {
            mData = new ArrayList<>();
        }
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
        }

        mBinding.rv.setAdapter(mAdapter);
        mBinding.rv.setItemAnimator(new DefaultItemAnimator());
        mBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
    }

    static class Param implements Parcelable {
        public int cid;

        public Param(int cid) {
            this.cid = cid;
        }

        protected Param(Parcel in) {
            cid = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(cid);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Param> CREATOR = new Creator<Param>() {
            @Override
            public Param createFromParcel(Parcel in) {
                return new Param(in);
            }

            @Override
            public Param[] newArray(int size) {
                return new Param[size];
            }
        };
    }
}
