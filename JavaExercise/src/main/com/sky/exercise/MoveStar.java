package com.sky.exercise;

import com.google.common.base.Preconditions;
import com.sky.problem.Problem;

/**
 * 函数将字符串中的字符'*'移到串的前部分，前面的非'*'字符后移， 但不能改变非'*'字符的先后顺序，函数返回串中字符'*'的数量。
 * 如原始串为：ab**cd**e*12，处理后为*****abcde12
 */
public class MoveStar implements Problem<String, String> {
	// 用1个游标从尾部开始向前移动，指向*时和前面第一个非*字符互换
	@Override
	public String resolve(String param) {
		Preconditions.checkNotNull(param);
		char[] values = new char[param.length()];
		for (int i = 0; i < param.length(); i++) {
			values[i] = param.charAt(i);
		}
		int starFlag = param.length() - 1;
		int charFlag = 0;
		while (starFlag >= 0 && charFlag >= 0) {
			while (starFlag >= 0 && values[starFlag] != '*') {
				starFlag--;
			}
			charFlag = starFlag - 1;
			while (charFlag >= 0 && values[charFlag] == '*') {
				charFlag--;
			}
			if (starFlag >= 0 && charFlag >= 0) {
				exch(values, starFlag, charFlag);
			}
		}
		return new String(values);
	}

	private void exch(char[] a, int i, int j) {
		char c = a[i];
		a[i] = a[j];
		a[j] = c;
	}

}
