package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OneBitCountTest
{
	@Test
	public void test()
	{
		OneBitCount algo = new OneBitCount();
		Assert.assertEquals((int)algo.resolve(10), 2);
		Assert.assertEquals((int)algo.resolve(0), 0);
		Assert.assertEquals((int)algo.resolve(15), 4);
		Assert.assertEquals((int)algo.resolve(20), 2);
	}
}
