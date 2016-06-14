package com.base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @desc
 * @author zhengzy
 * @version 2016年6月14日
 */
public class TestString {

	@Test
	public void test() {
		String s = "abc";
		String[] sp = s.split("");
		System.out.println(sp.length);
	}

	@Test
	public void anagrams() {
		String[] strs = {"tea","","eat","","tea",""};
		// write your code here
		List<String> rl = new ArrayList<>();
		
		List<List<String>> llst = new ArrayList<>();

		for (String s : strs) {
			List<String> lst = new ArrayList<>();
			char[] c = s.toCharArray();
			
			for (int i = 0; i < strs.length; i++) {
				boolean flag = true;
				String str = strs[i];
				for (int j = 0; j < c.length; j++) {
					char chr = c[j];
					int pos = str.indexOf(chr);
					if (pos == -1) {
						flag = false;
						break;
					}
				}
				if (str.length() == 0){
				    flag = true;
				}
//				if ((c.length == 0 && str.length() != 0)||(c.length != 0 && str.length() == 0)){
//					flag = false;
//				}
				
				if (c.length != str.length()){
					flag = false;
				}
				if (flag) {
					lst.add(str);
				}
			}
			
			if (lst.size()>1){
//				for (List l:llst){
//					if (!l.containsAll(lst)){
//						llst
//					}
//				}
//				llst.add(lst);
				if (rl.size()+lst.size()<strs.length){
					if (!rl.containsAll(lst)){
						rl.addAll(lst);
					}
				}
//				System.out.println(lst);
//				System.out.println(lst.size());
//				return lst;
			}
		}
//		return rl;
		System.out.println(rl.size());

	}
}
