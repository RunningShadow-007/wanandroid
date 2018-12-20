package com.feiyang.wanandroid.core.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/20 4:23 PM<br>
 * Desc: <br>
 */
public class ViewModelUtils {
    public static <T extends ViewModel> T obtainViewModel(Fragment fragment, Class<T> clazz) {
        return ViewModelProviders.of(fragment).get(clazz);
    }

    public static <T extends ViewModel> T obtainViewModel(FragmentActivity activity, Class<T> clazz) {
        return ViewModelProviders.of(activity).get(clazz);
    }
}
