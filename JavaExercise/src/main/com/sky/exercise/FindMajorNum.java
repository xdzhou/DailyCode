package com.sky.exercise;

import com.loic.algo.array.ArrayUtils;
import com.sky.problem.ProblemTwoSolutions;
/**
 * 数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字。
 */
public class FindMajorNum implements ProblemTwoSolutions<Integer[], Integer>
{

	@Override
	public Integer resolve(Integer[] param)
	{
		int king = 0, count = 0;
		for(int num : param)
		{
			if(count == 0)
			{
				king = num;
				count ++;
			}
			else if (count > param.length >>> 1) 
			{
				break;
			}
			else if(king == num) 
			{
				count ++;
			}
			else 
			{
				count --;
			}
		}
		return king;
	}

	@Override
	public Integer resolve2(Integer[] param)
	{
		return ArrayUtils.findKth(param, (param.length - 1) / 2);
	}

}
