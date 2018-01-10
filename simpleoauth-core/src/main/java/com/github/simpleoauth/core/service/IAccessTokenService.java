package com.github.simpleoauth.core.service;

import com.github.simpleoauth.core.entity.OauthAccessToken;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface IAccessTokenService {
    void save(OauthAccessToken token);

    OauthAccessToken getLastTokenByAppKey(String app_key);

    OauthAccessToken expireToken(String refresh_token);

    void handleExpiredToken(Timestamp createTime);
}
