package com.sky.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sky.problem.Problem;

/**
 * 把数组排成最小的数。
 * 输入一个正整数数组，将它们连接起来排成一个数，输出能排出的所有数字中最小的一个。
 * 例如输入数组{32, 321}，则输出这两个能排成的最小数字32132
 */
public class MinComposeNum implements Problem<Integer[], String>
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
				return (""+o1+o2).compareTo(""+o2+o1);
			}
		};
		Collections.sort(list, comparator);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); i++)
		{
			sb.append(list.get(i));
		}
		return sb.toString();
	}

}
