package com.sky.greedy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCloseSumTest
{
	@Test
	public void test()
	{
		GetCloseSum algo = new GetCloseSum();
		Assert.assertEquals(algo.resolve(new int[]{2, 2}, new int[]{1, 5}), 2);
		Assert.assertEquals(algo.resolve(new int[]{100, 99, 98, 1, 2, 3}, new int[]{4, 5, 40, 1, 2, 3}), 48);
		Assert.assertEquals(algo.resolve(new int[]{2, 2}, new int[]{1, 3}), 0);
		Assert.assertEquals(algo.resolve(new int[]{1,1,2}, new int[]{8,5,3}), 0);
	}
}
