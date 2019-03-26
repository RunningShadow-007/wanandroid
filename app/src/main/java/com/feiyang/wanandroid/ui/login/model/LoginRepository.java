package com.feiyang.wanandroid.ui.login.model;

import com.feiyang.wanandroid.core.db.Db;
import com.feiyang.wanandroid.core.db.dao.UserDao;
import com.feiyang.wanandroid.core.net.ApiService;
import com.feiyang.wanandroid.core.net.NetworkObserver;
import com.feiyang.wanandroid.core.net.ServiceProvider;
import com.feiyang.wanandroid.core.util.Optional;
import com.feiyang.wanandroid.ui.login.model.bean.LoginData;

import java.util.Map;

import io.reactivex.Observable;


/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 4:04 PM<br>
 * Desc: <br>
 */
public class LoginRepository {
    private static volatile LoginRepository INSTANCE;

    private ApiService mRemote;

    private UserDao mLocal;

    private LoginRepository() {
        mRemote = ServiceProvider.getInstance().getRetrofit(ServiceProvider.BASE_URLS).create(ApiService.class);
        mLocal = Db.getInstance().getUserDao();
    }

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Observable<Optional<LoginData>> login(Map<String, String> param) {
        return NetworkObserver.on(mRemote.login(param))
                              .map(data -> {
                                  if (data.isPresent()) {
                                      mLocal.addLogin(data.get());
                                  }
                                  return data;
                              });
    }

    /**
     * 注册接口
     *
     * @return
     */
    public Observable<Optional<LoginData>> regist(Map<String, String> param) {
        return NetworkObserver.on(mRemote.regist(param));
    }

    /**
     * 退出登录
     *
     * @return
     */
    public Observable<Optional<Object>> logout() {
        ApiService service = ServiceProvider.getInstance().provide(ApiService.class);
        return NetworkObserver.on(service.logout());
    }

}
