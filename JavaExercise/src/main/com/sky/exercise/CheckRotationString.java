package com.sky.exercise;

import com.google.common.base.Preconditions;

/**
 * 假设你有一个isSubstring函数，可以检测一个字符串是否是另一个字符串的子串。 
 * 给出字符串s1和s2，只使用一次isSubstring就能判断s2是否是s1的旋转字符串， 
 * 请写出代码。旋转字符串："waterbottle"是"erbottlewat"的旋转字符串。
 */
public class CheckRotationString
{

	public boolean isRotionString(String s1, String s2)
	{
		Preconditions.checkNotNull(s1);
		Preconditions.checkNotNull(s2);
		
		return s1.length() == s2.length() ? (s1+s1).contains(s2) : false;
	}
}
