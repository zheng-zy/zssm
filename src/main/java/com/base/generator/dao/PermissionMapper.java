package com.base.generator.dao;

import java.util.List;

import com.base.generator.entity.Permission;

public interface PermissionMapper {
    int insert(Permission record);
    int insertSelective(Permission record);
    List<Permission> getPermissionsByRoleId(Integer id);
}