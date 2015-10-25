package com.sky.dynamicProgramming;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindMidNumTest
{
	@Test
	public void test()
	{
		FindMidNum algo = new FindMidNum();
		Assert.assertEquals(process(algo, 3,2,1,4,6,5,7), 4);
		Assert.assertEquals(process(algo, 2,2,2,2,2), 2);
		Assert.assertEquals(process(algo, 1,2,3), 2);
	}
	
	private int process(FindMidNum algo, Integer ... a)
	{
		return algo.resolve(a);
	}
}
