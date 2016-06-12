package com.base.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.base.generator.entity.User;
import com.base.service.IUserService;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月12日
*/
public class MyRealm extends AuthenticatingRealm{
	
	@Resource
	public IUserService userService;

	/**
	 * 为当限前登录的用户授予角色和权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String loginName = String.valueOf(principals.getPrimaryPrincipal());

        final User user = userService.getUserByLoginName(loginName);
//        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
//        for (Role role : roleInfos) {
//            // 添加角色
//            System.err.println(role);
//            authorizationInfo.addRole(role.getRoleSign());
//
//            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
//            for (Permission permission : permissions) {
//                // 添加权限
//                System.err.println(permission);
//                authorizationInfo.addStringPermission(permission.getPermissionSign());
//            }
//        }
        return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
		
	}

}
