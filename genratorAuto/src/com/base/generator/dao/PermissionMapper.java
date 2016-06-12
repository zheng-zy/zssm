package com.base.generator.dao;

import com.base.generator.entity.Permission;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}