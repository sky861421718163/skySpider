package com.sky.spider.interview;

import java.util.LinkedHashMap;

/**
 * 找出 字符串 第一次出现且出现一次的字符  如 “abacfc”  --> b
 *@ClassName:E35FirstNotRepeatingChar.java
 *@ClassDescribe:
 *@createPerson:SKY
 *@createDate:2018年6月25日 下午2:02:49
 *@version
 */
public class E35FirstNotRepeatingChar {

	public Character firstNotRepeating(String str) {
		if (str == null)
			return null;
		char[] strChar = str.toCharArray();
		LinkedHashMap<Character, Integer> hash = new LinkedHashMap<Character, Integer>();
		for (char item : strChar) {
			if (hash.containsKey(item))
				hash.put(item, hash.get(item) + 1);
			else
				hash.put(item, 1);
		}
		for (char key : hash.keySet()) {
			if (hash.get(key) == 1)
				return key;
		}
		return null;
	}

	public static void main(String[] args) {
		String str = "abaccdebff";
		E35FirstNotRepeatingChar test = new E35FirstNotRepeatingChar();
		System.out.println(test.firstNotRepeating(str)); //d
	}
}