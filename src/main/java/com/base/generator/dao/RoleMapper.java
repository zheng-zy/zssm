package com.base.generator.dao;

import java.util.List;

import com.base.generator.entity.Role;

public interface RoleMapper {
    int insert(Role record);
    int insertSelective(Role record);
    List<Role> getRolesByUserId(Integer id);
}