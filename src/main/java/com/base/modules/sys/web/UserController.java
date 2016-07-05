package com.base.modules.sys.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.util.JsonResult;
import com.base.modules.sys.entity.User;
import com.base.modules.sys.service.IUserService;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月12日
*/
@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	public IUserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/getUserById", method = RequestMethod.POST)
	public JsonResult getUserById() {
		JsonResult json = new JsonResult();
		User user = userService.getUserById(1);
		if (user == null) {
			json.failure();
		} else {
			// 如果有中文，要考虑乱码问题，此处不处理
			json.success(user);
		}
		return json;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(){
		logger.info("登录成功") ;
//		return "redirect:main";
		return "main";
	}

}
