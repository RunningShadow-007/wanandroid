package com.feiyang.wanandroid.core.callback;

import android.view.View;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/28 4:57 PM<br>
 * Desc: <br>
 */
public interface OnItemClickListener<T> {
    void onItemClick(View view, T data, int pos);
}
