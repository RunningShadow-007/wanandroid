package com.feiyang.wanandroid.ui.login.vm;

import android.app.Application;
import android.text.TextUtils;

import com.feiyang.wanandroid.base.BaseViewModel;
import com.feiyang.wanandroid.core.constants.Constants;
import com.feiyang.wanandroid.core.util.SpUtils;
import com.feiyang.wanandroid.ui.login.activity.LoginActivity;
import com.feiyang.wanandroid.ui.login.model.LoginRepository;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 1:24 PM<br>
 * Desc: <br>
 */
public class LoginViewModel extends BaseViewModel {
    private LoginRepository mRepository;

    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>();

    public MutableLiveData<Boolean> isRegSuccess = new MutableLiveData<>();

    public MutableLiveData<Boolean> isLogoutSuccess = new MutableLiveData<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository = LoginRepository.getInstance();
    }


    public void login(@NonNull Map<String, String> p) {
        Disposable subscribe = mRepository.login(p)
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(data -> {
                                              if (data.isPresent()){
                                                  SpUtils.putString(Constants.SP_KEY_USER_ID, data.get().getId());
                                              }
                                              isLoginSuccess.postValue(true);
                                          }, throwable -> isLoginSuccess.postValue(false));
        disposable.add(subscribe);

    }

    /**
     * 注册接口
     *
     * @return
     */
    public void regist(@NonNull Map<String, String> p) {
        Disposable subscribe = mRepository.regist(p)
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(loginData -> {
                                              isRegSuccess.postValue(true);
                                          }, throwable -> isRegSuccess.postValue(false));
        disposable.add(subscribe);
    }

    public void logout() {
        Disposable subscribe = mRepository.logout()
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(o -> isLogoutSuccess.postValue(true));
        disposable.add(subscribe);
    }

    public boolean check(@NonNull Map<String, String> param, @LoginActivity.SIGN_TYPE int signType) {
        if (signType == LoginActivity.SIGN_IN) {
            return !TextUtils.isEmpty(param.get("username"))
                    && !TextUtils.isEmpty(param.get("password"));
        } else {
            return !TextUtils.isEmpty(param.get("username"))
                    && !TextUtils.isEmpty(param.get("password"))
                    && !TextUtils.isEmpty(param.get("repassword"))
                    && param.get("password").equals(param.get("repassword"));
        }
    }

}
