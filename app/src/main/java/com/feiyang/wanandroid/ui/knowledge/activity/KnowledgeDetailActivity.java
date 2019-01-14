package com.feiyang.wanandroid.ui.knowledge.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.core.util.StatusBarUtils;
import com.feiyang.wanandroid.databinding.ActivityWebBinding;
import com.feiyang.wanandroid.ui.knowledge.vm.KnowledgeViewModel;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/28 4:39 PM<br>
 * Desc: <br>
 */
public class KnowledgeDetailActivity extends BaseActivity<KnowledgeDetailActivity.Param, ActivityWebBinding, KnowledgeViewModel> {
    private ArticlesData.ArticleBean mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initToolbar();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected Class<KnowledgeViewModel> getVM() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected void initViews() {
        super.initViews();
        if (mParam != null) {
            mData = mParam.data;
        }
        if (mData == null || TextUtils.isEmpty(mData.getLink()))
            return;

        WebSettings webSettings=databinding.webView.getSettings();
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

        databinding.webView.setWebViewClient(new WebViewClient(){

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

        databinding.webView.loadUrl(mData.getLink());
    }

    @Override
    public void onBackPressed() {
        if (databinding.webView.canGoBack()) {
            databinding.webView.goBack();
        }else {
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

        databinding.title.setText("...");
        StatusBarUtils.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.colorPrimary), 1f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class Param implements Parcelable {
        public ArticlesData.ArticleBean data;

        public Param(ArticlesData.ArticleBean data) {
            this.data = data;
        }

        protected Param(Parcel in) {
            data = in.readParcelable(ArticlesData.ArticleBean.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(data, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Param> CREATOR = new Creator<Param>() {
            @Override
            public Param createFromParcel(Parcel in) {
                return new Param(in);
            }

            @Override
            public Param[] newArray(int size) {
                return new Param[size];
            }
        };
    }
}
