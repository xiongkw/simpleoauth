package com.github.simpleoauth.core.mapper;

import com.github.simpleoauth.core.entity.OauthAccessToken;

public interface OauthAccessTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OauthAccessToken record);

    int insertSelective(OauthAccessToken record);

    OauthAccessToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthAccessToken record);

    int updateByPrimaryKey(OauthAccessToken record);
}