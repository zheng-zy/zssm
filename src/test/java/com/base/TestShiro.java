package com.base;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @desc
 * @author zhengzy
 * @version 2016年7月14日
 */
public class TestShiro {
	private static Logger logger = Logger.getLogger(TestShiro.class);

	@Test
	public void test(){
		JSONObject object = new JSONObject();
		object.put("employee", "emp32");
		logger.info(object.toJSONString());
		String value = object.toJSONString();
		JSONObject object2 = new JSONObject();
		object2.put("test", value);
		logger.info(object2.toJSONString());
		
	}
	
	
	@Test
	public void testShiro() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录
		// 6、退出
		subject.logout();
	}
}
