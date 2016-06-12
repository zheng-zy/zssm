package com.base.util;

import java.util.UUID;

/** 
* @desc 生成唯一标识
* @author zhengzy 
* @version 2016年4月26日
*/
public class KeyGenerator {

	private KeyGenerator() {
	}

	/**
	 * 生成标识,由32位16进制字符组成，字母小写
	 */
	public static String generate() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}
