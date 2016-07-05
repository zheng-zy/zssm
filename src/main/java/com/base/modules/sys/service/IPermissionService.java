package com.base.modules.sys.service;

import java.util.List;

import com.base.modules.sys.entity.Permission;


/** 
* @desc 
* @author zhengzy 
* @version 2016年6月13日
*/
public interface IPermissionService{
	public List<Permission> getPermissionsByRoleId(int RoleId);
}
