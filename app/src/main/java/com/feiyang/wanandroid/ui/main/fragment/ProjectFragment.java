package com.feiyang.wanandroid.ui.main.fragment;

import android.os.Bundle;

import com.feiyang.wanandroid.base.BaseFragment;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/18 3:56 PM<br>
 * Desc: <br>
 */
public class ProjectFragment extends BaseFragment {
    public static ProjectFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
