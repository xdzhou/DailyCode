package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest
{
	@Test
	public void test()
	{
		AutobiographicalNumber algo = new AutobiographicalNumber();
		Assert.assertEquals(algo.resolve(2020), algo.resolve2(2020));
		Assert.assertEquals(algo.resolve(1210), algo.resolve2(1210));
		Assert.assertEquals(algo.resolve(132545), algo.resolve2(132545));
	}
}
