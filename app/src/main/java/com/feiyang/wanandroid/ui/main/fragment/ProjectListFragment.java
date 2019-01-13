package com.feiyang.wanandroid.ui.main.fragment;

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
import com.feiyang.wanandroid.databinding.FragmentProjectListBinding;
import com.feiyang.wanandroid.ui.main.adapter.ProjectListAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;
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
 * Date:2019/1/11 4:14 PM<br>
 * Desc: 存储项目列表<br>
 */
public class ProjectListFragment extends BaseFragment<ProjectListFragment.Param> {
    private int mCid, mCurPageNo = 1;

    private FragmentProjectListBinding mBinding;

    private MainViewModel mVm;

    private List<ArticlesData.ArticleBean> mData = new ArrayList<>();

    private ProjectListAdapter mAdapter;

    private LoadingType mLoadingType = LoadingType.LOADING_ORIGIN;

    public static ProjectListFragment newInstance(int cid) {
        Bundle args  = new Bundle();
        Param  param = new Param();
        param.cid = cid;
        args.putParcelable(IPage.PAGE_PARAM, param);
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (param != null) {
            mCid = param.cid;
        }
        mVm = ViewModelUtils.obtainViewModel(this, MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentProjectListBinding.inflate(inflater, container, false);
        initViews();
        observeData();
        loadData();
        return mBinding.getRoot();
    }

    @Override
    protected void observeData() {
        super.observeData();
        mVm.projectArticles.observe(this, data -> {
            if (isNonNull(data)) {
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
        mVm.isLoadFailed.observe(this, aVoid -> {
            mCurPageNo--;
            if (mCurPageNo < 0) {
                mCurPageNo = 0;
            }
        });

        mVm.loading.observe(this, aBoolean -> {
            if (aBoolean != null) {
                switch (mLoadingType) {
                    case LOADING_MORE:
                        if (!aBoolean)
                            mBinding.swiper.setEnableLoadMore(true);
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
    }

    @Override
    protected void loadData() {
        super.loadData();
        mVm.getProjectArticles(mCurPageNo, mCid);
    }

    @Override
    protected void initViews() {
        super.initViews();
        if (mAdapter == null) {
            mAdapter = new ProjectListAdapter(mData);
        }

        mBinding.rv.setAdapter(mAdapter);
        mBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rv.setItemAnimator(new DefaultItemAnimator());

        mBinding.swiper.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mLoadingType = LoadingType.LOADING_MORE;
                mCurPageNo++;
                mVm.getProjectArticles(mCurPageNo, mCid);
                refreshLayout.setEnableLoadMore(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mLoadingType = LoadingType.LOADING_REFRESH;
                mCurPageNo = 1;
                mVm.getProjectArticles(mCurPageNo, mCid);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVm != null) {
            mVm.dispose();
        }
    }


    static class Param implements Parcelable {
        public int cid;

        public Param() {

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
