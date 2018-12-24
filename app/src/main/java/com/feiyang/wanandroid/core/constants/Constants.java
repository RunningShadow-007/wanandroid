package com.feiyang.wanandroid.core.constants;

import com.feiyang.wanandroid.App;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 3:08 PM<br>
 * Desc: <br>
 */
public class Constants {
    /**
     * Path
     */
    public static final String PATH_DATA = App.getApp().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final int TYPE_HEADER = 1;

    public static final int TYPE_DATA = 2;

    public static final int TYPE_FOOTER = 3;

    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({TYPE_DATA, TYPE_FOOTER, TYPE_HEADER})
    public @interface ITEM_TYPE {

    }
}
