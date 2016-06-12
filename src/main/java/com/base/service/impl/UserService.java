package com.base.service.impl;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.generator.dao.UserMapper;
import com.base.generator.entity.User;
import com.base.service.IUserService;

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

}
