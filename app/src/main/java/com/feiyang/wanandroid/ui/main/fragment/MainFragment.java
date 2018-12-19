package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.base.IPage;
import com.feiyang.wanandroid.databinding.FragmentMainBinding;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;

import androidx.annotation.Nullable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 1:48 PM<br>
 * Desc:主页 <br>
 */
public class MainFragment extends BaseFragment<IPage.IPageParam, FragmentMainBinding, MainViewModel> {


    private int mCurPage = 0;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
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
    protected Class<MainViewModel> getVm() {
        return MainViewModel.class;
    }

    @Override
    protected void observeData() {
        if (vm != null) {
            vm.articleList.observe(this, articleBeans -> {

            });

            vm.bannerList.observe(this, bannerData -> {

            });
        }
    }

    @Override
    protected void loadData() {
        vm.getArticleList(mCurPage);
        vm.getBannerList();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initToolbar() {

    }
}
