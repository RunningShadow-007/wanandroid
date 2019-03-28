package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feiyang.wanandroid.base.BaseFragment;
import com.feiyang.wanandroid.databinding.FragmentSettingBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/3/26 6:08 PM<br>
 * Desc: <br>
 */
public class SettingFragment extends BaseFragment {
    private FragmentSettingBinding mBinding;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSettingBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }
}
