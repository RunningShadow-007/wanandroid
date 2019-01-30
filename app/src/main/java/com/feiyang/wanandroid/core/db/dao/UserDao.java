package com.feiyang.wanandroid.core.db.dao;

import com.feiyang.wanandroid.ui.login.model.bean.LoginData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/24 11:49 AM<br>
 * Desc: <br>
 */
@Dao
public abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addLogin(LoginData data);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract int updateLogin(LoginData data);

    @Delete
    public abstract void deleteLogin(LoginData data);

    @Query("delete from LoginData where id=:id")
    public abstract void deleteLogin(String id);

}
