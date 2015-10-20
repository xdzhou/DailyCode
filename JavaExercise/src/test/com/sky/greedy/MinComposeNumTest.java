package com.sky.greedy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MinComposeNumTest
{
	@Test
	public void test()
	{
		MinComposeNum algo = new MinComposeNum();
		Assert.assertEquals(process(algo, 1,2,3,4,5,6,7), "1234567");
		Assert.assertEquals(process(algo, 1, 11, 21, 12, 99), "111122199");
		Assert.assertEquals(process(algo, 10, 15, 25, 85), "10152585");
		Assert.assertEquals(process(algo, 1,1,1,1,1,1,1), "1111111");
	}
	
	private String process(MinComposeNum algo, Integer ... list)
	{
		return algo.resolve(list);
	}
}
