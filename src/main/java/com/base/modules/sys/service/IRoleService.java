package com.base.modules.sys.service;

import java.util.List;

import com.base.modules.sys.entity.Role;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月13日
*/
public interface IRoleService {
	public List<Role> getRolesByUserId(int userId);
}
