package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest
{
	@Test
	public void test()
	{
		AutobiographicalNumber algo = new AutobiographicalNumber();
		Assert.assertEquals((boolean)algo.resolve(2020), true);
		Assert.assertEquals((boolean)algo.resolve(1210), true);
		Assert.assertEquals((boolean)algo.resolve(132545), false);
	}
}
