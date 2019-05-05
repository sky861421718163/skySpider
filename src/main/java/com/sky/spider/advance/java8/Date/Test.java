package com.sky.spider.advance.java8.Date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class Test {

	public static void main(String[] args) {
		// 取当前日期：
		LocalDate today = LocalDate.now(); // -> 2014-12-24
		// 根据年月日取日期，12月就是12：
		LocalDate crischristmas = LocalDate.of(2014, 12, 25); // -> 2014-12-25
		// 根据字符串取：
		LocalDate endOfFeb = LocalDate.parse("2014-02-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
	//	LocalDate.parse("2014-02-29"); // 无效日期无法通过：DateTimeParseException: Invalid date
		//日期转换经常遇到，比如：

         // 取本月第1天：
		LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
		// 取本月第2天：
		LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
		// 取本月最后一天，再也不用计算是28，29，30还是31：
		LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
		// 取下一天：
		LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
		// 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
		LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05

		//LocalTime只包含时间，以前用java.util.Date怎么才能只表示时间呢？答案是，假装忽略日期。

		//LocalTime包含毫秒：

		LocalTime now = LocalTime.now(); // 11:09:09.240
		//你可能想清除毫秒数：

		LocalTime now2 = LocalTime.now().withNano(0); // 11:09:09
		//构造时间也很简单：

		LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
		System.out.println(zero);
		LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00

	}

}
