package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindMajorNumTest
{
	@Test
	public void test()
	{
		FindMajorNum algo = new FindMajorNum();
		Assert.assertEquals(process(algo, 1,1,2,3,5,2,3,5,1,1,1,1,1), 1);
		Assert.assertEquals(process(algo, 2,2,2,2), 2);
		Assert.assertEquals(process(algo, 2,1,5,2,2,8,2), 2);
	}
	
	private int process(FindMajorNum algo, Integer ... list)
	{
		return algo.resolve(list);
	}
}
