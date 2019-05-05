package com.sky.spider.interview;

import java.util.HashMap;

/**
 * 题目：已知按序排列的整数数组，输入任意数number，当数组中某两数之和等于number时，打印出两个数。
 *    要求：复杂度为o(n)
 * @author 
 *
 */
public class SumOfTwoNum {

	public static void main(String[] args) {
		int[] intArr = { 1, 2, 7, 4 };
		int target = 6;

		int[] r = SumOfTwoNum.sum(intArr, target);
		System.out.println("" + r[0] + r[1]);

	}

	public static int[] sum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			map.put(numbers[i], i + 1);
		}
		for (int i = 0; i < numbers.length; i++) {
			int value = target - numbers[i];
			if (map.containsKey(value) && map.get(value) != i + 1) {
				int index = map.get(value);
				if (i + 1 < index)
					return new int[] { i + 1, index };
				return new int[] { index, i + 1 };
			}
		}
		return new int[0];
	}

}
