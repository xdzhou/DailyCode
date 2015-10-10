package com.sky.divideConquer;

import com.sky.problem.OneInputOneOutputProb.IntegerDivisionProb;

public class DCIntegerDivision implements IntegerDivisionProb
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
