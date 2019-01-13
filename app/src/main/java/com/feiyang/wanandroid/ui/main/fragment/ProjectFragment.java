package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.core.util.ViewModelUtils;
import com.feiyang.wanandroid.databinding.FragmentProjectBinding;
import com.feiyang.wanandroid.ui.main.model.bean.ProjectCategoryData;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static com.feiyang.wanandroid.core.util.ObjectUtils.isNonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/18 3:56 PM<br>
 * Desc: <br>
 */
public class ProjectFragment extends BaseFragment {
    private FragmentProjectBinding mBinding;

    private MainViewModel mVm;


    public static ProjectFragment newInstance() {
        ProjectFragment fragment = new ProjectFragment();
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
        mBinding = FragmentProjectBinding.inflate(inflater, container, false);
        observeData();
        loadData();
        return mBinding.getRoot();
    }

    @Override
    protected void loadData() {
        super.loadData();
        mVm.getProjectCateList();
    }

    @Override
    protected void observeData() {
        super.observeData();
        mVm.projectCateList.observe(this, list -> {
            if (isNonNull(list)) {
                initViewPager(list);
            }
        });
    }

    private void initViewPager(List<ProjectCategoryData> cateList) {
        List<Fragment>fragments=new ArrayList<>();
        for (ProjectCategoryData item : cateList) {
            Fragment fragment = ProjectListFragment.newInstance(item.getId());
            fragments.add(fragment);
        }
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return cateList.get(position).getName();
            }
        };
        mBinding.vp.setAdapter(adapter);
        mBinding.tab.setupWithViewPager(mBinding.vp);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVm != null) {
            mVm.dispose();
        }
    }
}
