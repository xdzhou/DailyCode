package com.sky.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sky.problem.OneInputOneOutputProb;

/**
 * 把数组排成最小的数。
 * 输入一个正整数数组，将它们连接起来排成一个数，输出能排出的所有数字中最小的一个。
 * 例如输入数组{32, 321}，则输出这两个能排成的最小数字32132
 */
public class MinComposeNum implements OneInputOneOutputProb<Integer[], String>
{

	@Override
	public String resolve(Integer[] param)
	{
		List<Integer> list = Arrays.asList(param);
		Comparator<Integer> comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2)
			{
				int len1 = o1.toString().length();
				int len2 = o2.toString().length();
				if(len1 == len2)
				{
					return o1 - o2;
				}
				else if (len1 > len2) 
				{
					return (int) (o1 - o2 * Math.pow(10, len1 - len2));
				}
				else 
				{
					return (int) (o1 * Math.pow(10, len2 - len1) - o2 );
				}
			}
		};
		Collections.sort(list, comparator);
		
		while(! list.isEmpty())
		{
			
		}
		return null;
	}
	
	private String getHeadMinNum(List<Integer> list)
	{
		
	}

}
