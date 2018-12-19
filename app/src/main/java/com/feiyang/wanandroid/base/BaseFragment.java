package com.feiyang.wanandroid.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.core.util.ScreenUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.feiyang.wanandroid.base.BaseActivity.obtainViewModel;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 1:27 PM<br>
 * Desc: <br>
 */
public abstract class BaseFragment<Param extends IPage.IPageParam, DataBinding extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements IPage {

    protected Context mContext;

    protected Param param;

    protected DataBinding dataBinding;

    protected VM vm;

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
            param = (Param) savedInstanceState.getSerializable(IPage.PAGE_PARAM);
        } else {
            if (getArguments() != null) {
                param = (Param) getArguments().getSerializable(IPage.PAGE_PARAM);
            }
        }
        handler = new Handler(Looper.getMainLooper());
        tid = Process.myTid();

        if (getVm() != null) {
            vm = obtainViewModel((FragmentActivity) mContext, getVm());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId() != 0) {
            dataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
            initToolbar();
        }
        observeData();
        initView();
        return dataBinding != null ? dataBinding.getRoot() : super.onCreateView(inflater, container, savedInstanceState);
    }



    protected void loadData() {

    }

    protected void observeData(){

    }

    protected void initView() {

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

    protected void initToolbar() {

    }

    protected void post(Runnable runnable) {
        handler.post(runnable);
    }

    protected void showLoading() {
        post(() -> {
            if (isValidContext((Activity) mContext)) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new AlertDialog.Builder(mContext).create();
                    mLoadingDialog.setCanceledOnTouchOutside(false);
                }
                LottieAnimationView    loadingView  = new LottieAnimationView(mContext);
                ViewGroup.LayoutParams layoutParams = loadingView.getLayoutParams();
                layoutParams.width = ScreenUtils.getScreenWidth() / 4;
                layoutParams.height = ScreenUtils.getScreenWidth() / 4;
                loadingView.setLayoutParams(layoutParams);
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

    protected abstract int layoutId();

    protected abstract Class<VM> getVm();

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IPage.PAGE_PARAM, param);
    }

    @Override
    public void startPage(PageName pageName) {
        Intent intent = new Intent(mContext, pageName.target);
        intent.putExtra(IPage.PAGE_PARAM, pageName.pageParam);
        pageName.pageParam = null;
        startActivity(intent);
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
        vm.dispose();
    }
}
