package com.feiyang.wanandroid.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.util.ScreenUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 6:22 PM<br>
 * Desc: <br>
 */
@SuppressLint("Registered")
public class BaseActivity<Param extends IPage.IPageParam> extends AppCompatActivity implements IPage {
    private static final long TOAST_INTERNAL = 2000;

    protected CompositeDisposable mDisposable;

    private Dialog mLoadingDialog;

    private Handler handler;

    private long lastToastTime;

    private String lastToastText;

    private int tid;

    protected Param mParam;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mParam = (Param) savedInstanceState.getSerializable(IPage.PAGE_PARAM);
        } else {
            mParam = (Param) getIntent().getSerializableExtra(IPage.PAGE_PARAM);
        }
        tid = Process.myTid();
        mDisposable = new CompositeDisposable();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IPage.PAGE_PARAM, mParam);
    }

    protected boolean add(Disposable... disposable) {
        return mDisposable.addAll(disposable);
    }

    protected void showToast(final String text, final boolean isLong) {
        if (text == null) {
            return;
        }
        if ((System.currentTimeMillis() - lastToastTime) < TOAST_INTERNAL && text.equals(lastToastText)) {
            return;
        }
        if (tid == Process.myTid()) {
            if (isLong) {
                Toast.makeText(this, text, Toast.LENGTH_LONG)
                     .show();
            } else {
                Toast.makeText(this, text, Toast.LENGTH_SHORT)
                     .show();
            }

        } else {
            post(() -> {
                if (isLong) {
                    Toast.makeText(this, text, Toast.LENGTH_LONG)
                         .show();
                } else {
                    Toast.makeText(this, text, Toast.LENGTH_SHORT)
                         .show();
                }
            });
        }
        lastToastTime = System.currentTimeMillis();
        lastToastText = text;
    }

    protected void post(Runnable runnable) {
        handler.post(runnable);
    }

    protected void showLoading() {
        post(() -> {
            if (isValidContext(this)) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new AlertDialog.Builder(this).create();
                    mLoadingDialog.setCanceledOnTouchOutside(false);
                }
                LottieAnimationView    loadingView  = new LottieAnimationView(this);
                ViewGroup.LayoutParams layoutParams = loadingView.getLayoutParams();
                layoutParams.width = ScreenUtils.getScreenWidth() / 4;
                layoutParams.height = ScreenUtils.getScreenWidth() / 4;
                loadingView.setLayoutParams(layoutParams);
                loadingView.setAnimation(R.raw.loader);
                loadingView.setRepeatCount(LottieDrawable.INFINITE);
                loadingView.playAnimation();
                mLoadingDialog.setContentView(loadingView);
                mLoadingDialog.show();
            }
        });

    }

    protected void hideLoading() {
        post(() -> {
            if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
                mLoadingDialog = null;
            }
        });
    }

    protected boolean isValidContext(Activity a) {
        return !(a.isDestroyed() || a.isFinishing());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    public void startPage(PageName pageName) {
        Intent intent = new Intent(this, pageName.target);
        intent.putExtra(IPage.PAGE_PARAM, mParam);
        startActivity(intent);
    }

    @Override
    public void startPageForResult(PageName pageName, int requestCode) {
        Intent     intent = new Intent(this, pageName.target);
        IPageParam param  = pageName.pageParam.get();
        intent.putExtra(IPage.PAGE_PARAM, param);
        startActivityForResult(intent, requestCode);
    }

    public static <T extends ViewModel> T obtainViewModel(FragmentActivity activity, Class<T> clazz) {
        return ViewModelProviders.of(activity).get(clazz);
    }

}
