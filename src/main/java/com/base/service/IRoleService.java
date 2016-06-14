package com.base.service;

import java.util.List;

import com.base.generator.entity.Role;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月13日
*/
public interface IRoleService {
	public List<Role> getRolesByUserId(int userId);
}
