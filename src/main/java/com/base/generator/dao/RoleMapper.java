package com.base.generator.dao;

import com.base.generator.entity.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}