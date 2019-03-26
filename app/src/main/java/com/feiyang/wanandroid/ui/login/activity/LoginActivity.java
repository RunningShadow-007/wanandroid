package com.feiyang.wanandroid.ui.login.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.util.Base64;
import android.view.View;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.core.constants.Constants;
import com.feiyang.wanandroid.core.util.SpUtils;
import com.feiyang.wanandroid.databinding.ActivityLoginBinding;
import com.feiyang.wanandroid.ui.login.vm.LoginViewModel;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import static com.feiyang.wanandroid.core.util.CommonUtils.convertToStr;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 10:01 AM<br>
 * Desc:登录,注册页面 <br>
 */
public class LoginActivity extends BaseActivity<Parcelable, ActivityLoginBinding, LoginViewModel> {

    public static final int SIGN_IN = 1;

    public static final int SIGN_UP = 2;

    private int signType = SIGN_IN;

    private Map<String, String> params = new HashMap<>();

    @IntDef({SIGN_IN, SIGN_UP})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SIGN_TYPE {

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeData();
        toggleSign(signType);

        //切换到登录或注册
        add(RxView.clicks(databinding.tvToggle)
                  .subscribe(unit -> {
                      signType = signType == SIGN_IN ? SIGN_UP : SIGN_IN;
                      toggleSign(signType);
                  }));

        //登录||注册
        add(RxView.clicks(databinding.tvLoginOrReg)
                  .subscribe(unit -> {
                      switch (signType) {
                          case SIGN_IN:
                              toggleButtonStatus(false);
                              vm.login(params);
                              break;
                          case SIGN_UP:
                              toggleButtonStatus(false);
                              vm.regist(params);
                              break;
                      }
                  }));

        add(RxTextView.afterTextChangeEvents(databinding.etPassword)
                      .skipInitialValue()
                      .subscribe(event -> addParam(event, "password")));
        add(RxTextView.afterTextChangeEvents(databinding.etUserName)
                      .skipInitialValue()
                      .subscribe(event -> addParam(event, "username")));
        add(RxTextView.afterTextChangeEvents(databinding.etRePassword)
                      .skipInitialValue()
                      .subscribe(event -> addParam(event, "repassword")));

    }

    private void addParam(TextViewAfterTextChangeEvent event, String key) {
        Editable editable = event.getEditable();
        String   s        = convertToStr(editable);
        params.put(key, s);

        boolean isPassed = vm.check(params, signType);

        toggleButtonStatus(isPassed);
    }

    private void toggleButtonStatus(boolean isPassed) {
        databinding.tvLoginOrReg.setEnabled(isPassed);
        Drawable enabledDrawable  = ContextCompat.getDrawable(this, R.drawable.bg_shape_solid_blue_radius_3dp);
        Drawable disabledDrawable = ContextCompat.getDrawable(this, R.drawable.bg_shape_solid_grey_radius_4dp);

        int textColor = ContextCompat.getColor(this, isPassed ? R.color.white : R.color.textColor999);

        databinding.tvLoginOrReg.setBackground(isPassed ? enabledDrawable : disabledDrawable);
        databinding.tvLoginOrReg.setTextColor(textColor);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected Class<LoginViewModel> getVM() {
        return LoginViewModel.class;
    }

    @Override
    protected void observeData() {
        super.observeData();
        vm.isLoginSuccess.observe(this, aBoolean -> {
            toggleButtonStatus(true);
            if (aBoolean) {
                showToast("登录成功!");
                SpUtils.putString(Constants.SP_KEY_PASSWORD, Base64.encodeToString(params.get("password").getBytes(), Base64.DEFAULT));
                SpUtils.putString(Constants.SP_KEY_USER_NAME, params.get("username"));
                startPage(PageName.MAIN);
                finish();
            }
        });

        vm.isRegSuccess.observe(this, aBoolean -> {
            toggleButtonStatus(true);
            if (aBoolean)
                toggleSign(SIGN_IN);
        });

    }

    /**
     * 切换登录/注册
     */
    private void toggleSign(@SIGN_TYPE int signType) {
        switch (signType) {
            case SIGN_IN:
                databinding.tvLoginOrReg.setText("登录");
                clearHistory();
                ObjectAnimator a = ObjectAnimator.ofFloat(getWindow().getDecorView(), "alpha", 1.0f, 0.0f, 1.0f);
                ObjectAnimator b = ObjectAnimator.ofFloat(databinding.tilRePassword, "alpha", 1.0f, 0.0f);
                AnimatorSet as = new AnimatorSet();
                as.setDuration(200);
                as.playTogether(a, b);
                as.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        databinding.tilRePassword.setVisibility(View.GONE);
                        databinding.tvToggle.setText("没有账号?注册");
                    }
                });
                as.start();
                break;
            case SIGN_UP:
                databinding.tvLoginOrReg.setText("注册");
                clearHistory();
                ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(getWindow().getDecorView(), "alpha", 1.0f, 0.0f, 1.0f);
                ObjectAnimator b1 = ObjectAnimator.ofFloat(databinding.tilRePassword, "alpha", 0.0f, 1.0f);
                AnimatorSet as1 = new AnimatorSet();
                as1.setDuration(200);
                as1.playTogether(alphaAnim, b1);
                as1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        databinding.tilRePassword.setVisibility(View.VISIBLE);
                        databinding.tvToggle.setText("已有账号?登录");
                    }
                });
                as1.start();
                break;
        }
    }

    public void clearHistory() {
        databinding.etPassword.setText("");
        databinding.etRePassword.setText("");
        databinding.etUserName.setText("");
    }
}
