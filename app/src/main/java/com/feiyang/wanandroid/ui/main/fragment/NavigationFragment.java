package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.core.callback.OnItemClickListener;
import com.feiyang.wanandroid.core.util.ViewModelUtils;
import com.feiyang.wanandroid.core.widget.TopLinearSmoothScroller;
import com.feiyang.wanandroid.databinding.FragmentNavBinding;
import com.feiyang.wanandroid.ui.main.adapter.NavListAdapter;
import com.feiyang.wanandroid.ui.main.adapter.NavTitleAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.NaviData;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.feiyang.wanandroid.core.util.ObjectUtils.isNonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/18 3:55 PM<br>
 * Desc: <br>
 */
public class NavigationFragment extends BaseFragment {
    private FragmentNavBinding mBinding;

    private MainViewModel mVm;

    private NavTitleAdapter mTitleAdapter;

    private List<NaviData> mData;

    private NavListAdapter mNavListAdapter;


    public static NavigationFragment newInstance() {
        Bundle             args     = new Bundle();
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
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
        mBinding = FragmentNavBinding.inflate(inflater, container, false);
        initViews();
        observeData();
        loadData();
        return mBinding.getRoot();
    }

    @Override
    protected void loadData() {
        super.loadData();
        mVm.loading.observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (aBoolean)
                    showLoading();
                else
                    hideLoading();
            }
        });
        mVm.getNaviList();
    }

    @Override
    protected void observeData() {
        super.observeData();
        mVm.navList.observe(this, naviData -> {
            if (isNonNull(naviData)) {
                mData.clear();
                mData.addAll(naviData);
                mTitleAdapter.notifyDataSetChanged();
                mNavListAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void initViews() {
        if (mData == null) {
            mData = new ArrayList<>();
        }

        LinearLayoutManager leftManager = new LinearLayoutManager(mContext);
        LinearLayoutManager rightManager = new LinearLayoutManager(mContext) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                super.smoothScrollToPosition(recyclerView, state, position);
                TopLinearSmoothScroller smoothScroller = new TopLinearSmoothScroller(recyclerView.getContext());
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }
        };

        if (mNavListAdapter == null) {
            mNavListAdapter = new NavListAdapter(mData);
            mNavListAdapter.setOnTagClickListener(new OnItemClickListener<ArticlesData.ArticleBean>() {
                @Override
                public void onItemClick(View view, ArticlesData.ArticleBean data, int pos) {

                }
            });
        }


        if (mTitleAdapter == null) {
            mTitleAdapter = new NavTitleAdapter(mData);
            mTitleAdapter.setOnItemClickListener((view, data, pos) -> {
                mVm.clearSelected(mData);
                data.isSelected = true;
                mTitleAdapter.notifyDataSetChanged();
                mBinding.rvRight.smoothScrollToPosition(pos);
            });
        }


        mBinding.rvLeft.setAdapter(mTitleAdapter);
        mBinding.rvLeft.setLayoutManager(leftManager);

        mBinding.rvRight.setLayoutManager(rightManager);
        mBinding.rvRight.setAdapter(mNavListAdapter);


        mBinding.rvRight.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (layoutManager != null) {
                    int firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (firstVisibleItemPosition >= 0 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        mBinding.rvLeft.smoothScrollToPosition(firstVisibleItemPosition);
                        mVm.clearSelected(mData);
                        mData.get(firstVisibleItemPosition).isSelected = true;
                        mTitleAdapter.notifyDataSetChanged();
                        mBinding.rvRight.smoothScrollToPosition(firstVisibleItemPosition);
                    }
                }
            }
        });


    }
}
