package com.feiyang.wanandroid.ui.login.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.databinding.ActivityLoginBinding;
import com.jakewharton.rxbinding3.view.RxView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 10:01 AM<br>
 * Desc:登录,注册页面 <br>
 */
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding mBinding;

    private static final int SIGN_IN = 1;

    private static final int SIGN_UP = 2;

    private int signType = SIGN_IN;

    @IntDef({SIGN_IN, SIGN_UP})
    @Retention(RetentionPolicy.RUNTIME)
    @interface SIGN_TYPE {

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        toggleSign(signType);
        RxView.clicks(mBinding.tvToggle)
              .throttleWithTimeout(2, TimeUnit.SECONDS)
              .subscribe(unit -> {
                  toggleSign(signType);
                  signType = signType == SIGN_IN ? SIGN_UP : SIGN_IN;
              });
        RxView.clicks(mBinding.tvLoginOrReg)
              .throttleWithTimeout(1200,TimeUnit.MILLISECONDS)
              .subscribe(unit -> {
//                    if (signType==SIGN_IN)

              });

    }

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected Class getVM() {
        return null;
    }

    /**
     * 切换登录/注册
     */
    private void toggleSign(@SIGN_TYPE int signType) {
        switch (signType) {
            case SIGN_IN:
                ObjectAnimator a = ObjectAnimator.ofFloat(getWindow().getDecorView(), "alpha", 1.0f, 0.0f, 1.0f);
                if (mBinding.tilRePassword.isShown()) {
                    ObjectAnimator b  = ObjectAnimator.ofFloat(mBinding.tilRePassword, "alpha", 1.0f, 0.0f);
                    AnimatorSet    as = new AnimatorSet();
                    as.setDuration(2000);
                    as.playTogether(a, b);
                    as.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mBinding.tilRePassword.setVisibility(View.GONE);
                            mBinding.tvToggle.setText("没有账号?注册");
                        }
                    });
                    as.start();
                } else {
                    a.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mBinding.tvToggle.setText("没有账号?注册");
                        }
                    });
                }
                break;
            case SIGN_UP:
                ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(getWindow().getDecorView(), "alpha", 1.0f, 0.0f, 1.0f);
                ObjectAnimator b = ObjectAnimator.ofFloat(mBinding.tilRePassword, "alpha", 0.0f, 1.0f);
                AnimatorSet as = new AnimatorSet();
                as.setDuration(2000);
                as.playTogether(alphaAnim, b);
                as.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mBinding.tilRePassword.setVisibility(View.VISIBLE);
                    }
                });
                as.start();
                break;
        }
    }
}
