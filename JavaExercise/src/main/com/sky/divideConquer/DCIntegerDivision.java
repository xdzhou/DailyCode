package com.sky.divideConquer;

import com.sky.problem.Problem;
/**
 * 整数划分问题： 将一个正整数n表示成一系列正整数之和，n=n[1]+n[2]+...+n[k]，
 * 其中n[1]>=n[2]>=...>=n[k]>=1,k>=1。正整数n的一个这种表示称为n的一个划分.
 * 求n的不同划分个数。
 */
public class DCIntegerDivision implements Problem<Integer, Integer>
{
	@Override
	public Integer resolve(Integer n)
	{
		if(n == 1)
		{
			return 1;
		}
		return getDivisionCount(n, n);
	}
	
	private int getDivisionCount(int max, int n)
	{
		if(n <= 1 || max == 1)
		{
			return 1;
		}
		int retVal = 0;
		for(int i = Math.min(max, n); i > 0; i--)
		{
			retVal += getDivisionCount(i, n-i);
		}
		return retVal;
	}

}
