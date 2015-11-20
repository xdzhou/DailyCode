package com.sky.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.sky.problem.ProblemTwoSolutions;
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
public class AutobiographicalNumber implements ProblemTwoSolutions<Integer, Boolean>
{
	private static final Logger Log = LoggerFactory.getLogger(AutobiographicalNumber.class);
	private List<Integer> autobiographicalNumberList;
	
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
	
	@Override
	public Boolean resolve2(Integer param)
	{
		if(autobiographicalNumberList == null)
		{
			autobiographicalNumberList = new ArrayList<Integer>();
			int[] temp = new int[10];
			for(int len = 3; len <= 10; len ++)
			{
				Arrays.fill(temp, 0);
				fillTempList(0, len, len - 2, len, temp);
			}
			Log.debug("All autobiographical Number : "+autobiographicalNumberList);
		}
		return autobiographicalNumberList.contains(param);
	}

	private boolean fillTempList(int from, int len, int max, int sum, int[] list)
	{
		if(len <= 0 || sum < 0)
		{
			return false;
		}
		if(len == 1)
		{
			if(max < sum)
			{
				return false;
			}
			else 
			{
				list[from] = sum;
				checkAutobiographicalNumber(list, from + len);
				return true;
			}
		}
		else 
		{
			if(sum == 0)
			{
				checkAutobiographicalNumber(list, from + len);
				return true;
			}
			else 
			{
				int curMax = Math.min(max, sum);
				boolean success = true;
				while(curMax > 0 && success)
				{
					list[from] = curMax;
					success = fillTempList(from + 1, len - 1, curMax, sum - curMax, list);
					curMax --;
				}
				return success;
			}
		}
	}
	
	private void checkAutobiographicalNumber(int list[], int length)
	{
		if(list[length - 1] != 0)
		{
			return;
		}
		//Log.debug("check list for length {} : {} ", length, list);
		int[] count = new int[length];
		for(int i=0; i<length; i++)
		{
			count[list[i]] ++;
		}
		int[] count2 = new int[length];
		for(int i=0; i<length; i++)
		{
			int index = count[i];
			if(index < length)
			{
				count2[count[i]] ++;
			}
			else 
			{
				return;
			}
		}
		boolean found = Arrays.equals(count, count2);
		if(found)
		{
			StringBuilder numBuilder = new StringBuilder();
			for(int i=0; i<length; i++)
			{
				numBuilder.append(count[i]);
			}
			try
			{
				int num = Integer.parseInt(numBuilder.toString());
				autobiographicalNumberList.add(num);
				Log.debug("Find new Autobiographical Number : {}", num);
			} 
			catch (NumberFormatException e)
			{
				Log.debug("Find new Autobiographical Number(bigger than Int_Max) : {}", numBuilder.toString());
			}
		}
	}
}
