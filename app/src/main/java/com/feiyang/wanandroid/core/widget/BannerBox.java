package com.feiyang.wanandroid.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.databinding.LayoutBannerBinding;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/20 7:43 PM<br>
 * Desc: <br>
 */
public class BannerBox extends ConstraintLayout {
    private LayoutBannerBinding mBinding;

    public BannerBox(Context context) {
        this(context, null, 0);
    }

    public BannerBox(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true);
        view.setTag("layout/layout_banner_0");
        mBinding= DataBindingUtil.bind(view);
    }

    public void play(@NonNull List<BannerData>data){
        mBinding.banner.play(data);
        mBinding.banner.setOnPageChangedCallback((indexTxt, title) -> {
            mBinding.tvPosition.setText(indexTxt);
            mBinding.tvBannerTitle.setText(title);
        });
    }

}
