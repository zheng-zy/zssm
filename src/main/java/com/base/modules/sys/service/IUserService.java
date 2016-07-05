package com.base.modules.sys.service;

import java.util.List;

import com.base.modules.sys.entity.Role;
import com.base.modules.sys.entity.User;

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
