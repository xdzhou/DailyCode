package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UglyNumberTest
{

	@Test
	public void test()
	{
		UglyNumber algo = new UglyNumber();
		Assert.assertEquals(algo.resolve(3), algo.resolve2(3));
		Assert.assertEquals(algo.resolve(10), algo.resolve2(10));
		Assert.assertEquals(algo.resolve(99), algo.resolve2(99));
		Assert.assertEquals(algo.resolve(911), algo.resolve2(911));
		//Assert.assertEquals(algo.resolve(1500), algo.resolve2(1500));
	}
}
