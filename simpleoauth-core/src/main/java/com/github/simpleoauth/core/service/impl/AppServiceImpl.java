package com.github.simpleoauth.core.service.impl;

import com.github.simpleoauth.core.dao.IOauthAppInfoDao;
import com.github.simpleoauth.core.entity.OauthAppInfo;
import com.github.simpleoauth.core.service.IAppService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Service
public class AppServiceImpl implements IAppService {
    @Autowired
    private IOauthAppInfoDao oauthAppInfoDao;

    @Override
    public OauthAppInfo getAppInfoByKey(String app_key) {
        OauthAppInfo appInfo = oauthAppInfoDao.selectByAppkey(app_key);
        appInfo.setApp_secret(null);
        return appInfo;
    }

    @Override
    public boolean authApp(String app_key, String app_secret) {
        OauthAppInfo app = getAppInfoByKey(app_key);
        return app != null && StringUtils.equals(app.getApp_secret(), app_secret);
    }
}
