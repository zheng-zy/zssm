package com.base.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.generator.entity.User;
import com.base.service.IUserService;
import com.base.util.JsonResult;

/** 
* @desc 
* @author zhengzy 
* @version 2016年6月12日
*/
@Controller
@RequestMapping("/user")
public class UserController {
	
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
}
