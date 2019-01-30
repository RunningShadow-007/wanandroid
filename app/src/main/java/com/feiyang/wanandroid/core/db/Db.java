package com.feiyang.wanandroid.core.db;

import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.core.db.dao.UserDao;
import com.feiyang.wanandroid.ui.login.model.bean.LoginData;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/24 11:27 AM<br>
 * Desc: <br>
 */
@Database(entities = LoginData.class, version = 2)
public abstract class Db extends RoomDatabase {
    private static volatile Db sINSTANCE;

    public abstract UserDao getUserDao();

    public static Db getInstance() {
        if (sINSTANCE == null) {
            synchronized (Db.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = Room.databaseBuilder(App.getApp(), Db.class, "wanandroid.db")
                                    .fallbackToDestructiveMigration()
                                    .allowMainThreadQueries()
                                    .build();
                }
            }
        }
        return sINSTANCE;
    }
}
