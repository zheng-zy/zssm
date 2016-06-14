package com.base.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.base.generator.entity.Permission;
import com.base.generator.entity.Role;
import com.base.generator.entity.User;
import com.base.service.IPermissionService;
import com.base.service.IRoleService;
import com.base.service.IUserService;

/**
 * @desc 自定义权限验证
 * @author zhengzy
 * @version 2016年6月12日
 */
public class SecurityRealm extends AuthenticatingRealm {

	@Resource
	public IUserService userService;

	@Resource
	public IRoleService roleService;

	@Resource
	public IPermissionService permissionService;

	/**
	 * 为当限前登录的用户授予角色和权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String loginName = String.valueOf(principals.getPrimaryPrincipal());

		final User user = userService.getUserByLoginName(loginName);
		final List<Role> roles = roleService.getRolesByUserId(user.getId());
		for (Role role : roles) {
			// 添加角色
			authorizationInfo.addRole(role.getSign());
			final List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
			for (Permission permission : permissions) {
				// 添加权限
				authorizationInfo.addStringPermission(permission.getSign());
			}
		}
		return authorizationInfo;
	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginName = String.valueOf(token.getPrincipal());
		String password = new String((char[]) token.getCredentials());
		// 通过数据库进行验证
		final User authentication = userService.authentication(new User(loginName, password));
		if (authentication == null) {
			throw new AuthenticationException("用户名或密码错误.");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, password, getName());
		return authenticationInfo;
	}

}
