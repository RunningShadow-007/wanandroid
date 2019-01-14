package com.feiyang.wanandroid.ui.knowledge.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MenuItem;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.core.util.StatusBarUtils;
import com.feiyang.wanandroid.databinding.ActivityKnowledgeBinding;
import com.feiyang.wanandroid.ui.knowledge.fragment.KnowledgeListFragment;
import com.feiyang.wanandroid.ui.knowledge.vm.KnowledgeViewModel;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static com.feiyang.wanandroid.core.util.ObjectUtils.isNonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/13 4:44 PM<br>
 * Desc: <br>
 */
public class KnowledgeActivity extends BaseActivity<KnowledgeActivity.Param, ActivityKnowledgeBinding, KnowledgeViewModel> {
    private KnowledgeHierarchyData mData;

    private List<Fragment> mFragmentList;

    private List<KnowledgeHierarchyData.ChildrenBean> mTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mParam != null) {
            mData = mParam.data;
        }
        if (mData == null || !isNonNull(mData.getChildren())) {
            return;
        }
        initToolbar();
        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        if (mFragmentList == null) {
            mFragmentList = new ArrayList<>();
        }

        mTitles = mData.getChildren();
        for (KnowledgeHierarchyData.ChildrenBean item : mTitles) {
            Fragment fragment = KnowledgeListFragment.newInstance(item.getId());
            mFragmentList.add(fragment);
        }

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mTitles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position).getName();
            }
        };

        databinding.vp.setAdapter(adapter);
        databinding.tab.setupWithViewPager(databinding.vp);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_knowledge;
    }

    @Override
    protected Class<KnowledgeViewModel> getVM() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setSupportActionBar(databinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        databinding.title.setText(mData.getName());
        StatusBarUtils.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.colorPrimary), 1f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class Param implements Parcelable {
        public KnowledgeHierarchyData data;

        public Param(KnowledgeHierarchyData data) {
            this.data = data;
        }

        protected Param(Parcel in) {
            data = in.readParcelable(KnowledgeHierarchyData.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(data, flags);
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
