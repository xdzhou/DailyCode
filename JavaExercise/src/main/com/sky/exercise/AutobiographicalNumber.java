package com.sky.exercise;

import com.google.common.base.Preconditions;
import com.sky.problem.OneInputOneOutputProb;
/**
 * 给你10 分钟时间，根据上排给出十个数，在其下排填出对应的十个数
 * 要求下排每个数都是先前上排那十个数在下排出现的次数。
 * 上排的十个数如下：
 * 【0，1，2，3，4，5，6，7，8，9】
 * 举一个例子，
 * 数值: 0,1,2,3,4,5,6,7,8,9
 * 分配: 6,2,1,0,0,0,1,0,0,0 ==> 6210001000 is a Autobiographical Number
 * 0 在下排出现了6 次，1 在下排出现了2 次，
 * 2 在下排出现了1 次，3 在下排出现了0 次....
 */
public class AutobiographicalNumber implements OneInputOneOutputProb<Integer, Boolean>
{

	@Override
	public Boolean resolve(Integer param)
	{
		Preconditions.checkNotNull(param);
		String numString = param.toString();
		int[] counts = new int[numString.length()];
		for(int i=0; i<counts.length; i++)
		{
			counts[numString.charAt(i) - '0'] ++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<counts.length; i++)
		{
			sb.append(counts[i]);
		}
		
		return numString.equals(sb.toString());
	}
	
	public boolean resolve2(int n)
	{
		return false;
	}

}
