package com.base.modules.sys.dao;

import java.util.List;

import com.base.modules.sys.entity.Role;

public interface RoleMapper {
    int insert(Role record);
    int insertSelective(Role record);
    List<Role> getRolesByUserId(Integer id);
}