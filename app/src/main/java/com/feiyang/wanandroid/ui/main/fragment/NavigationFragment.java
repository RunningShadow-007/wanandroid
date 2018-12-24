package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;

import com.feiyang.wanandroid.base.BaseFragment;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/18 3:55 PM<br>
 * Desc: <br>
 */
public class NavigationFragment extends BaseFragment {
    public static NavigationFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int layoutId() {
        return 0;
    }


}
