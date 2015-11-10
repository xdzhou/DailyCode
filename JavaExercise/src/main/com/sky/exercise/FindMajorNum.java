package com.sky.exercise;

import com.sky.problem.Problem;
/**
 * 数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字。
 */
public class FindMajorNum implements Problem<Integer[], Integer>
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

}
