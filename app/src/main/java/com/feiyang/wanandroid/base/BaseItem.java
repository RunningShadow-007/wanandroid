package com.feiyang.wanandroid.base;

import com.feiyang.wanandroid.core.constants.Constants;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/20 7:14 PM<br>
 * Desc: <br>
 */
public interface BaseItem {
    @Constants.ITEM_TYPE
    int getItemType();

    Object getData();
}
