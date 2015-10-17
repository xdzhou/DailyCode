package com.sky.dynamicProgramming;

import java.util.Arrays;

import com.sky.problem.OneInputOneOutputProb.LISProb;

public class DPLongestIncreasingSubsequence implements LISProb
{
	@Override
	public Integer resolve(Integer[] param)
	{
		int[] dp = new int[param.length];
		dp[0] = 1;
		int retVal = 1;
		for(int i=1; i<param.length; i++)
		{
			int len = 1;
			for(int j=0; j<i; j++)
			{
				if(param[i] >= param[j] && len < dp[j] + 1)
				{
					len = dp[j] + 1;
				}
			}
			dp[i] = len;
			if(retVal < len)
			{
				retVal = len;
			}
		}
		return retVal;
	}

}
