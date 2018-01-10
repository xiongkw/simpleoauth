package com.github.simpleoauth.core.service;

import com.github.simpleoauth.core.entity.OauthAppInfo;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface IAppService {
    OauthAppInfo getAppInfoByKey(String app_key);

    boolean authApp(String app_key, String app_secret);
}
