package com.github.simpleoauth.core.service.impl;

import com.github.simpleoauth.core.dao.IOauthAccessTokenDao;
import com.github.simpleoauth.core.entity.OauthAccessToken;
import com.github.simpleoauth.core.service.IAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

    @Autowired
    private IOauthAccessTokenDao oauthAccessTokenDao;

    @Override
    public void save(OauthAccessToken token) {
        oauthAccessTokenDao.insert(token);
    }

    @Override
    public OauthAccessToken getLastTokenByAppKey(String app_key) {
        return oauthAccessTokenDao.getLastTokenByAppKey(app_key);
    }

    @Override
    @Transactional
    public OauthAccessToken expireToken(String refresh_token) {
        OauthAccessToken token = oauthAccessTokenDao.selectByRefreshToken(refresh_token);
        OauthAccessToken entity = new OauthAccessToken();
        entity.setId(token.getId());
        entity.setHas_been_refreshed(true);
        oauthAccessTokenDao.updateByPrimaryKey(entity);
        return token;
    }

    @Override
    public void handleExpiredToken(Timestamp createTime) {
        oauthAccessTokenDao.handleExpiredToken(createTime);
    }
}
