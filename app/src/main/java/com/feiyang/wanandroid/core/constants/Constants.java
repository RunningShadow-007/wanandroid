package com.feiyang.wanandroid.core.constants;

import com.feiyang.wanandroid.App;

import java.io.File;

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
}
