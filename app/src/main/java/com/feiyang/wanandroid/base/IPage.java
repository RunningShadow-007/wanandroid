package com.feiyang.wanandroid.base;

import android.os.Parcelable;

import com.feiyang.wanandroid.ui.knowledge.activity.KnowledgeActivity;
import com.feiyang.wanandroid.ui.login.activity.LoginActivity;
import com.feiyang.wanandroid.ui.main.activity.MainActivity;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 7:49 PM<br>
 * Desc: <br>
 */
public interface IPage {
    String PAGE_PARAM = "PAGE_PARAM";

    void startPage(PageName pageName);

    void startPageForResult(PageName pageName, int requestCode);

    enum PageName {
        MAIN(MainActivity.class),
        LOGIN(LoginActivity.class),
        KNOWLEDGE(KnowledgeActivity.class),
        WEB(WebActivity.class),
        ;

        public Class target;

        public Parcelable pageParam;

        PageName(Class target) {
            this.target = target;
        }
    }


}
