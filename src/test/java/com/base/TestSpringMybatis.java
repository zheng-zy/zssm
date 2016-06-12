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

import com.base.generator.entity.User;
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
	}

}
