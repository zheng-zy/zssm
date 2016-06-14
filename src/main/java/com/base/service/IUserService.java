package com.base.service;

import java.util.List;

import com.base.generator.entity.Role;
import com.base.generator.entity.User;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月12日
*/
public interface IUserService {
	public User getUserById(int userId); 
	public User getUserByLoginName(String loginName);
	public List<Role> getRolesByUserId(int userId);
	/**
	 * 用户登录验证
	 * @param user
	 * @return
	 */
	public User authentication(User user);
}
