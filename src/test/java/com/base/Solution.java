package com.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc
 * @author zhengzy
 * @version 2016年6月14日
 */
public class Solution {
	/**
	 * @param strs:
	 *            A list of strings
	 * @return: A list of strings
	 */
	public List<String> anagrams(String[] strs) {
		// write your code here
		Map<String, List<String>> map = new HashMap<>();
		List<String> result = new ArrayList<>();
		for (String str:strs){
			String key = generateKey(str);
			if (map.containsKey(key)){
				map.get(key).add(str);
			}else{
				List<String> lst = new ArrayList<>();
				lst.add(str);
				map.put(key, lst);
			}
		}
		
		for (List<String> lst :map.values()){
			if (lst.size()>1){
				result.addAll(lst);
			}
		}
		return result;
	}
	
	public String generateKey(String str){
		char[] cstr =  str.toCharArray();
		Arrays.sort(cstr);
		return new String(cstr);
	}
	
	public static void main(String[] args) {
//		String[] strs = {"ray","cod","abe","ned","arc","jar","owl","pop","paw","sky","yup","fed","jul","woo","ado","why","ben","mys","den","dem","fat","you","eon","sui","oct","asp","ago","lea","sow","hus","fee","yup","eve","red","flo","ids","tic","pup","hag","ito","zoo"};
		String[] strs = {"",""}; 
		List<String> result = new Solution().anagrams(strs);
		 System.out.println(result.size());
	}
}