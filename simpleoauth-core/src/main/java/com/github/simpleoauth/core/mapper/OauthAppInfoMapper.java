package com.github.simpleoauth.core.mapper;

import com.github.simpleoauth.core.entity.OauthAppInfo;

public interface OauthAppInfoMapper {
    int deleteByPrimaryKey(String app_code);

    int insert(OauthAppInfo record);

    int insertSelective(OauthAppInfo record);

    OauthAppInfo selectByPrimaryKey(String app_code);

    int updateByPrimaryKeySelective(OauthAppInfo record);

    int updateByPrimaryKey(OauthAppInfo record);
}