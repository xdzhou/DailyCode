package com.sky.divideConquer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DivideTwoIntegersTest
{

	@Test
	public void test()
	{
		DivideTwoIntegers algo = new DivideTwoIntegers();
		Assert.assertEquals(algo.divide(1, 2), 0);
	}
}
