package com.github.simpleoauth.core.mapper;

import com.github.simpleoauth.core.entity.OauthAccessTokenHistory;

public interface OauthAccessTokenHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OauthAccessTokenHistory record);

    int insertSelective(OauthAccessTokenHistory record);

    OauthAccessTokenHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthAccessTokenHistory record);

    int updateByPrimaryKey(OauthAccessTokenHistory record);
}