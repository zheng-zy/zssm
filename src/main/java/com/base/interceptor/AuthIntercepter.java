package com.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器示例
 * 
 * @desc
 * @author zhengzy
 * @version 2016年4月26日
 */
public class AuthIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("示例：权限拦截器:" + AuthIntercepter.class.getName());
		System.out.println("进入AuthIntercepter拦截器");
		return true;
	}
}