package com.feiyang.wanandroid.ui.login.activity;

import android.os.Bundle;
import android.view.animation.BounceInterpolator;

import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.databinding.ActivityWelcomeBinding;

import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWelcomeBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        if (!App.getApp().isFirstLaunch()) {
            startPage(PageName.MAIN);
            finish();
        } else {
            ViewCompat.animate(mBinding.ivLogo)
                      .alphaBy(-0.5f)
                      .alphaBy(0.5f)
                      .setDuration(4000)
                      .setInterpolator(new BounceInterpolator())
                      .start();
        }
    }

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected Class getVM() {
        return null;
    }

}
