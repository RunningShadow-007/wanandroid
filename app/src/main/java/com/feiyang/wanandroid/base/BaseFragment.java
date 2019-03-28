package com.feiyang.wanandroid.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.feiyang.wanandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 1:27 PM<br>
 * Desc: <br>
 */
public abstract class BaseFragment<Param extends Parcelable> extends Fragment implements IPage {

    protected Context mContext;

    protected Param param;

    private static final long TOAST_INTERNAL = 2000;

    protected CompositeDisposable mDisposable;

    private Dialog mLoadingDialog;

    private Handler handler;

    private long lastToastTime;

    private String lastToastText;

    private int tid;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            param = savedInstanceState.getParcelable(IPage.PAGE_PARAM);
        } else {
            if (getArguments() != null) {
                param = getArguments().getParcelable(IPage.PAGE_PARAM);
            }
        }
        handler = new Handler(Looper.getMainLooper());
        tid = Process.myTid();

    }


    protected void loadData() {

    }

    protected void observeData() {

    }

    protected void initViews() {

    }

    protected boolean add(Disposable... disposable) {
        return mDisposable.addAll(disposable);
    }

    protected void showToast(String text) {
        showToast(text, false);
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
                Toast.makeText(mContext, text, Toast.LENGTH_LONG)
                     .show();
            } else {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT)
                     .show();
            }

        } else {
            post(() -> {
                if (isLong) {
                    Toast.makeText(mContext, text, Toast.LENGTH_LONG)
                         .show();
                } else {
                    Toast.makeText(mContext, text, Toast.LENGTH_SHORT)
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
            if (isValidContext((Activity) mContext)) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new AlertDialog.Builder(mContext, R.style.common_dialog_style).create();
                    mLoadingDialog.setCanceledOnTouchOutside(true);
                }
                LottieAnimationView    loadingView = new LottieAnimationView(mContext);
                ViewGroup.LayoutParams params      = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingView.setLayoutParams(params);
                loadingView.setAnimation(R.raw.loader);
                loadingView.setRepeatCount(LottieDrawable.INFINITE);
                loadingView.playAnimation();
                mLoadingDialog.show();
                mLoadingDialog.setContentView(loadingView);

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(IPage.PAGE_PARAM, param);
    }

    @Override
    public void startPage(PageName pageName) {
        startPage(pageName, null);
    }

    @Override
    public void startPage(PageName pageName, ActivityOptionsCompat options) {
        Intent intent = new Intent(mContext, pageName.target);
        intent.putExtra(IPage.PAGE_PARAM, pageName.pageParam);
        pageName.pageParam = null;
        if (options != null) {
            ActivityCompat.startActivity(mContext, intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void startPageForResult(PageName pageName, int requestCode) {
        Intent intent = new Intent(mContext, pageName.target);
        intent.putExtra(IPage.PAGE_PARAM, pageName.pageParam);
        pageName.pageParam = null;
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }


}
