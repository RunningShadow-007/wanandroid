package com.feiyang.wanandroid.core.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/5 3:39 PM<br>
 * Desc: 平滑滚动item到顶部<br>
 */
public class TopLinearSmoothScroller extends LinearSmoothScroller {
    public TopLinearSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
