package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindOddNumTest
{
	@Test
	public void test()
	{
		FindOddNum algo = new FindOddNum();
		Assert.assertEquals(check(algo, 1, 2,2), 1);
		Assert.assertEquals(check(algo, 1,1,2,2,2,3,4,4,3), 2);
	}
	
	private int check(FindOddNum algo, Integer ... list)
	{
		return algo.resolve(list);
	}
}
