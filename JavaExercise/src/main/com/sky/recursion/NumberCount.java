package com.sky.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.sky.problem.ProblemTwoSolutions;

/**
 * count the number of is between 0 and n (1<= i <=9)
 * 计算从 0 到 n 这些数字中 0,1,2...9的个数
 * 
 * @link http://www.hawstein.com/posts/20.4.html
 *
 */
public class NumberCount implements ProblemTwoSolutions<Integer, Integer[]>
{
	private static final Logger Log = LoggerFactory.getLogger(NumberCount.class);
	
	@Override
	public Integer[] resolve(Integer param)
	{
		Objects.requireNonNull(param);
		Preconditions.checkArgument(param > 0, "n must be bigger than 0 !");
		Map<Integer, Integer[]> cacheMap = new HashMap<>();
		return getNumberCount(param, cacheMap);
	}
	
	private Integer[] getNumberCount(int n, Map<Integer, Integer[]> cacheMap)
	{
		if(n < 10)
		{
			Integer[] result = new Integer[10];
			for(int i = 0; i <= n; i++)
			{
				result[i] = 1;
			}
			if(n == 9)
			{
				cacheMap.put(9, result);
			}
			Log.info("i(s) Count for {} : {}", n, result);
			return result;
		}
		else
		{
			String num = Integer.toString(n);
			int highNum = num.charAt(0) - '0';
			int continus9 = getContinus9(num.length() - 1);
			Integer[] continus9NumCount = cacheMap.get(continus9);
			if(continus9NumCount == null)
			{
				continus9NumCount = getNumberCount(continus9, cacheMap);
				cacheMap.put(continus9, continus9NumCount);
			}
			multipleList(highNum, continus9NumCount);
			for(int i = 1; i < highNum; i++)
			{
				continus9NumCount[i] += (continus9 + 1);
			}
			int rest = n - highNum * (continus9 + 1);
			Integer[] restList = getNumberCount(rest, cacheMap);
			addList(continus9NumCount, restList);
			continus9NumCount[highNum] += (rest + 1);
			Log.info("i(s) Count for {} : {}", n, continus9NumCount);
			return continus9NumCount;
		}
	}
	
	private void addList(Integer[] list1, Integer[] list2)
	{
		for(int i = 0; i < list1.length; i++)
		{
			list1[i] += list2[i];
		}
	}
	
	private void multipleList(int a, Integer[] list)
	{
		for(int i = 0; i < list.length; i++)
		{
			list[i] *= a;
		}
	}
	
	private int getContinus9(int len)
	{
		int num = 9;
		while((len -= 1) > 0)
		{
			num *= 10;
			num += 9;
		}
		return num;
	}

	@Override
	public Integer[] resolve2(Integer param)
	{
		Objects.requireNonNull(param);
		Preconditions.checkArgument(param > 0, "n must be bigger than 0 !");
		return null;
	}

	

}
