package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;

import com.feiyang.wanandroid.base.BaseFragment;

import androidx.annotation.Nullable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/17 4:25 PM<br>
 * Desc: <br>
 */
public class KnowledgeHierarchyFragment extends BaseFragment {

    public static KnowledgeHierarchyFragment newInstance() {

        Bundle args = new Bundle();

        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected Class getVm() {
        return null;
    }

}
