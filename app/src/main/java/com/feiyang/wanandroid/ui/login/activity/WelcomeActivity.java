package com.feiyang.wanandroid.ui.login.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.OvershootInterpolator;

import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.databinding.ActivityWelcomeBinding;

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
            ObjectAnimator logoAlpha=ObjectAnimator.ofFloat(mBinding.ivLogo,"alpha",0.0f,0.5f,1.0f);
            ObjectAnimator tvAlpha=ObjectAnimator.ofFloat(mBinding.tvMsg,"alpha",0.0f,0.5f,1.0f);
            ObjectAnimator logoScaleX=ObjectAnimator.ofFloat(mBinding.ivLogo,"scaleX",0.0f,0.5f,1.0f,1.5f,1.0f);
            ObjectAnimator tvScaleX=ObjectAnimator.ofFloat(mBinding.tvMsg,"scaleX",0.0f,0.5f,1.0f,1.5f,1.0f);
            ObjectAnimator logoScaleY=ObjectAnimator.ofFloat(mBinding.ivLogo,"scaleY",0.0f,0.5f,1.0f,1.5f,1.0f);
            ObjectAnimator tvScaleY=ObjectAnimator.ofFloat(mBinding.tvMsg,"scaleY",0.0f,0.5f,1.0f,1.5f,1.0f);
            AnimatorSet set=new AnimatorSet();
            set.playTogether(logoAlpha,tvAlpha,logoScaleX,tvScaleX,tvScaleY,logoScaleY);
            set.setDuration(4000);
            set.setInterpolator(new OvershootInterpolator());
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startPage(PageName.MAIN);
                    finish();
                }
            });
            set.start();
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
