package com.base.modules.sys.dao;

import java.util.List;

import com.base.modules.sys.entity.Permission;

public interface PermissionMapper {
    int insert(Permission record);
    int insertSelective(Permission record);
    List<Permission> getPermissionsByRoleId(Integer id);
}