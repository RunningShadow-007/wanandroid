package com.feiyang.wanandroid.core.util;

import android.text.Editable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/24 2:22 PM<br>
 * Desc: <br>
 */
public class CommonUtils {
    public static String convertToStr(Editable editable) {
        return editable == null ? "" : editable.toString();
    }

}
