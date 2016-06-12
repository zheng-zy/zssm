package com.base.service;

import com.base.generator.entity.User;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月12日
*/
public interface IUserService {
	public User getUserById(int userId); 
	public User getUserByLoginName(String loginName);
}
