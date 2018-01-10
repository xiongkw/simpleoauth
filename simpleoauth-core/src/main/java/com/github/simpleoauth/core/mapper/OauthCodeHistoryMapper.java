package com.github.simpleoauth.core.mapper;

import com.github.simpleoauth.core.entity.OauthCodeHistory;

public interface OauthCodeHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OauthCodeHistory record);

    int insertSelective(OauthCodeHistory record);

    OauthCodeHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthCodeHistory record);

    int updateByPrimaryKey(OauthCodeHistory record);
}