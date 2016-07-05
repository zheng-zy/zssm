package com.base.modules.sys.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @desc 测试controller
 * @author zhengzy
 * @version 2016年7月5日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml", "classpath*:spring-context-mybatis.xml",
		"classpath*:spring-mvc.xml" })
// 当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserControllerTest {

	private static Logger logger = Logger.getLogger(UserControllerTest.class);
	
	@Resource
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Resource
	private UserController UserController;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetUserById() throws Exception {
		// request.setParameter(name, value);
		// mockMvc.perform(post("/getUserById").param(name, values))
		MvcResult mvcResult = mockMvc.perform(post("/user/getUserById")).andExpect(status().isOk()).andDo(print()).andReturn();
		logger.info(mvcResult.getResponse().getStatus());
		logger.info(mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	// 有些单元测试你不希望回滚
	@Rollback(false)
	public void testInsert() throws Exception {
		mockMvc.perform((post("/insertTest"))).andExpect(status().isOk()).andDo(print());
	}

}
