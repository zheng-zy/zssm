package com.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.generator.dao.PermissionMapper;
import com.base.generator.entity.Permission;
import com.base.service.IPermissionService;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月13日
*/
@Service("permissionService")
public class PermissionService implements IPermissionService{

	@Resource
	public PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> getPermissionsByRoleId(int roleId) {
		return permissionMapper.getPermissionsByRoleId(roleId);
	}

}
