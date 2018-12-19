package com.feiyang.wanandroid.ui.login.vm;

import android.app.Application;

import com.feiyang.wanandroid.base.BaseViewModel;
import com.feiyang.wanandroid.ui.login.model.LoginRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 1:24 PM<br>
 * Desc: <br>
 */
public class LoginViewModel extends BaseViewModel {
    private LoginRepository mRepository;

    public MutableLiveData<Boolean>loading=new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository = LoginRepository.getInstance();
    }





}
