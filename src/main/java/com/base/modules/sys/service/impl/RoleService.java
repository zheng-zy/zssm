package com.base.modules.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.modules.sys.dao.RoleMapper;
import com.base.modules.sys.entity.Role;
import com.base.modules.sys.service.IRoleService;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月13日
*/

@Service("roleService")
public class RoleService implements IRoleService {

	@Resource
	public RoleMapper RoleMapper;
	
	@Override
	public List<Role> getRolesByUserId(int userId) {
		return RoleMapper.getRolesByUserId(userId);
	}

}
