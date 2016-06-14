package com.base;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.base.generator.entity.Permission;
import com.base.generator.entity.Role;
import com.base.generator.entity.User;
import com.base.service.IPermissionService;
import com.base.service.IRoleService;
import com.base.service.IUserService;

/**
 * @desc 测试spring跟mybatis整合是否成功
 * @author zhengzy
 * @version 2016年4月22日
 */

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestSpringMybatis {
	private static Logger logger = org.apache.log4j.Logger.getLogger(TestSpringMybatis.class);
	// private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;
	@Resource
	private IRoleService roleService = null;
	@Resource
	private IPermissionService permissionService = null;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void testSpringMybatis() {
		User user = userService.getUserById(1);
		System.out.println(user.getName());
		logger.info("值：" + user.getName());
		
		List<Role> roles = userService.getRolesByUserId(1);
		System.out.println(roles.size());
	}
	
	@Test
	public void TestRealm(){
		String loginName = "admin";

        final User user = userService.getUserByLoginName(loginName);
        
        final List<Role> roles = roleService.getRolesByUserId(user.getId());
        for (Role role: roles){
        	// 添加角色
        	System.out.println("添加角色："+role.getSign());
        	final List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
        	for (Permission permission: permissions){
        		// 添加权限
        		System.out.println("添加权限："+permission.getSign());
        	}
        }
	}
	@Test
	public void testAuthentication(){
		final User authentication = userService.authentication(new User("admin", "123"));
		System.out.println(authentication);
	}

}
