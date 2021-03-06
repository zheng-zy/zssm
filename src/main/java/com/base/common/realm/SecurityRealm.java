package com.base.common.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.base.common.util.StringUtil;
import com.base.modules.sys.entity.Permission;
import com.base.modules.sys.entity.Role;
import com.base.modules.sys.entity.User;
import com.base.modules.sys.service.IPermissionService;
import com.base.modules.sys.service.IRoleService;
import com.base.modules.sys.service.IUserService;

/**
 * @desc 自定义权限验证
 * @author zhengzy
 * @version 2016年6月12日
 */
public class SecurityRealm extends AuthenticatingRealm {

	private final static Logger logger = Logger.getLogger(SecurityRealm.class);

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
		logger.info("shiro 授权");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		// String currentUsername =
		// (String)super.getAvailablePrincipal(principals);
		String loginName = String.valueOf(principals.getPrimaryPrincipal());

		if (StringUtil.isEmpty(loginName)) {
			throw new AuthorizationException();
		}

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
		logger.info("shiro 登录验证");
		String loginName = String.valueOf(token.getPrincipal());
		String password = new String((char[]) token.getCredentials());
		// 通过数据库进行验证
		final User authentication = userService.authentication(new User(loginName, password));
		if (authentication == null) {
			throw new AuthenticationException("用户名或密码错误.");
		}
		this.setSession("currentUser", authentication.getLoginname());
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, password, getName());
		return authenticationInfo;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
