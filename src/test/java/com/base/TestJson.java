package com.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.common.util.FileUtils;

/**
 * @desc
 * @author zhengzy
 * @version 2016年7月18日
 */
public class TestJson {

	public void test() throws Exception {
		String targetName = "C:\\Users\\zheng-zy\\Desktop\\temp.txt";
		String fileName = "C:\\Users\\zheng-zy\\Desktop\\emp.txt";
		File file = new File(fileName);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while (line != null) {
//			System.out.println(line);
			jsonParse(targetName, line);
			line = bufferedReader.readLine();
		}
	}

	public void jsonParse(String fileName, String str) {
		JSONObject ob = (JSONObject) JSONObject.parse(str);
		String idstr = "{\"index\":{\"_id\":\"cid\"}}";
		idstr = idstr.replaceFirst("cid", ob.getString("account_number"));
		System.out.println("idstr:"+idstr);
		FileUtils.writeToFile(fileName, idstr+"\n", true);
		FileUtils.writeToFile(fileName, str+"\n", true);
	}
	
	@Test
	public void jsonPackage(){
		JSONObject object = new JSONObject();
//		object.put(key, value)
	}
	
}