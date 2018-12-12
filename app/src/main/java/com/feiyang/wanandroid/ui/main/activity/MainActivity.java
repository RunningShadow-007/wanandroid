package com.feiyang.wanandroid.ui.main.activity;

import android.os.Bundle;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.databinding.ActivityMainBinding;
import com.feiyang.wanandroid.ui.login.vm.LoginViewModel;

import androidx.databinding.DataBindingUtil;


public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;

    private LoginViewModel mVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mVm = obtainViewModel(this, LoginViewModel.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVm != null) {
            mVm.dispose();
        }
    }
}
