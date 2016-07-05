package com.base.modules.sys.dao;

import java.util.List;

import com.base.modules.sys.entity.Role;
import com.base.modules.sys.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<Role> getRolesByUserId(Integer id);
    
	User selectByLoginName(String loginName);
	
	User authentication(User record);
}