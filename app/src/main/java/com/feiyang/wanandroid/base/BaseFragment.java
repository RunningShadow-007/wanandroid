package com.feiyang.wanandroid.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 1:27 PM<br>
 * Desc: <br>
 */
public class BaseFragment<Param extends IPage.IPageParam> extends Fragment implements IPage {

    protected Context mContext;

    protected Param param;

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
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IPage.PAGE_PARAM, param);
    }

    @Override
    public void startPage(PageName pageName) {
        Intent     intent = new Intent(mContext, pageName.target);
        IPageParam param  = pageName.pageParam.get();
        intent.putExtra(IPage.PAGE_PARAM, param);
        startActivity(intent);
    }

    @Override
    public void startPageForResult(PageName pageName, int requestCode) {
        Intent     intent = new Intent(mContext, pageName.target);
        IPageParam param  = pageName.pageParam.get();
        intent.putExtra(IPage.PAGE_PARAM, param);
        startActivityForResult(intent, requestCode);
    }
}
