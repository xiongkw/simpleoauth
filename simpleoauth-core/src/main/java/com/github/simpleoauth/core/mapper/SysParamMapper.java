package com.github.simpleoauth.core.mapper;

import com.github.simpleoauth.core.entity.SysParam;

public interface SysParamMapper {
    int deleteByPrimaryKey(Long sys_param_id);

    int insert(SysParam record);

    int insertSelective(SysParam record);

    SysParam selectByPrimaryKey(Long sys_param_id);

    int updateByPrimaryKeySelective(SysParam record);

    int updateByPrimaryKey(SysParam record);
}