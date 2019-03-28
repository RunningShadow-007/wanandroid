package com.feiyang.wanandroid.base;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.core.util.StatusBarUtils;
import com.feiyang.wanandroid.databinding.ActivityWebBinding;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import static androidx.core.content.PermissionChecker.PERMISSION_DENIED;
import static com.feiyang.wanandroid.core.util.ObjectUtils.isNonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/14 5:54 PM<br>
 * Desc: <br>
 */
public class WebActivity extends BaseActivity<Parcelable, ActivityWebBinding, BaseViewModel> {
    private ArticlesData.ArticleBean mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initViews();
        observeData();

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void observeData() {
        super.observeData();
        vm.checkPermissionResult.observe(this, result -> {
            if (isNonNull(result)) {
                for (Map.Entry<String, Integer> next : result.entrySet()) {
                    if (next.getKey().equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (next.getValue() == PERMISSION_DENIED) {
                            showToast("您拒绝了开启分享权限.");
                        } else {
                            doShare();
                        }
                    }
                }
            }
        });

        vm.toast.observe(this, this::showToast);

        vm.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });
    }

    private void doShare() {
        if (mData == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_type_url, getString(R.string.app_name), mData.getTitle(), mData.getLink()));
        intent.setType("text/plain");
        startActivity(intent);
    }

    @Override
    protected void initViews() {
        super.initViews();
        ViewCompat.setTransitionName(databinding.webView, getString(R.string.share_view));
        if (mParam != null) {
            if (mParam instanceof ArticlesData.ArticleBean) {
                mData = (ArticlesData.ArticleBean) mParam;
                if (TextUtils.isEmpty(mData.getLink()))
                    return;
                loadWeb(mData.getLink());
            }
        }

    }

    private void loadWeb(String url) {
        WebSettings webSettings = databinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);

        databinding.webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //                super.onReceivedSslError(view, handler, error);
                // 接受所有网站的证书，忽略SSL错误，执行访问网页
                handler.proceed();
            }
        });

        databinding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                databinding.progress.setVisibility(View.VISIBLE);
                databinding.progress.setProgress(newProgress);
                if (newProgress == 100) {
                    databinding.progress.setVisibility(View.GONE);
                    databinding.progress.setProgress(0);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                databinding.title.setText(title);
            }
        });

        databinding.webView.loadUrl(url);
    }

    @Override
    protected Class<BaseViewModel> getVM() {
        return BaseViewModel.class;
    }

    @Override
    public void onBackPressed() {
        if (databinding.webView.canGoBack()) {
            databinding.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        setSupportActionBar(databinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        databinding.title.setText("");
        StatusBarUtils.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.colorPrimary), 1f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menuShare:
                vm.verifyPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.menuCollect:
                if (mData == null)
                    return false;
                vm.collectArticle(mData);
                break;
            case R.id.menuBrower:
                if (mData == null) {
                    return false;
                }
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mData.getLink())));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

}
