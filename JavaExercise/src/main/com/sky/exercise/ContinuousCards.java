package com.sky.exercise;

import java.util.Arrays;

import com.sky.problem.Problem;

/**
 * 扑克牌的顺子
 * 从扑克牌中随机抽n张牌，判断是不是一个顺子，即这n张牌是不是连续的。
 * 2-10 为数字本身，A 为1，J 为11，Q 为12，K 为13，而大小王可以看成任意数字。
 */
public class ContinuousCards implements Problem<Integer[], Boolean>
{

	// make king = 0
	@Override
	public Boolean resolve(Integer[] param)
	{
		if(param.length <= 1)
		{
			return true;
		}
		Arrays.sort(param);
		int allowGaps = 0;
		if(param[0] == 0)
		{
			allowGaps = 1;
		}
		if(param[1] == 0)
		{
			allowGaps = 2;
		}
		int curIndex = allowGaps;
		while(curIndex + 1 < param.length)
		{
			allowGaps -= param[curIndex + 1] - param[curIndex] - 1;
			if(allowGaps >= 0)
			{
				curIndex ++;
			}
			else 
			{
				return false;
			}
		}
		return true;
	}

}
