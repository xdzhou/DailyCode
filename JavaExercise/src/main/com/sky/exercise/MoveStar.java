package com.sky.exercise;

import com.google.common.base.Preconditions;
import com.sky.problem.OneInputOneOutputProb;
/**
 * 函数将字符串中的字符'*'移到串的前部分，前面的非'*'字符后移，
 * 但不能改变非'*'字符的先后顺序，函数返回串中字符'*'的数量。
 * 如原始串为：ab**cd**e*12，处理后为*****abcde12
 */
public class MoveStar implements OneInputOneOutputProb<String, String>
{

	@Override
	public String resolve(String param)
	{
		Preconditions.checkNotNull(param);
		char[] values = new char[param.length()];
		for(int i=0; i<param.length(); i++)
		{
			values[i] = param.charAt(i);
		}
		int starFlag = param.length();
		int charFlag = param.length();
		while(true)
		{
			while(values[--starFlag] != '*')
			{
				if(starFlag < 0)
					break;
			}
			charFlag = starFlag;
			if(charFlag > 0)
			{
				while(values[--charFlag] == '*')
				{
					if(charFlag <= 0)
						break;
				}
			}
			if(starFlag >= 0 && charFlag > 0)
			{
				exch(values, starFlag, charFlag);
			}
			else 
			{
				break;
			}
		}
		return new String(values);
	}
	
	private void exch(char[] a, int i, int j)
	{
		System.out.println("change "+a[i]+", "+a[j]);
		char c = a[i];
		a[i] = a[j];
		a[j] = c;
	}

}
