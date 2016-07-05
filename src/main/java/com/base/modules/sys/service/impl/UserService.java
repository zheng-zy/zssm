package com.base.modules.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.modules.sys.dao.UserMapper;
import com.base.modules.sys.entity.Role;
import com.base.modules.sys.entity.User;
import com.base.modules.sys.service.IUserService;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月12日
*/

@Service("userService")
public class UserService implements IUserService {

	@Resource
	public UserMapper userMapper;
	
	@Override
	public User getUserById(int userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User getUserByLoginName(String loginName) {
		return userMapper.selectByLoginName(loginName);
	}

	@Override
	public List<Role> getRolesByUserId(int userId) {
		return userMapper.getRolesByUserId(userId);
	}

	@Override
	public User authentication(User user) {
		return userMapper.authentication(user);
	}

}
