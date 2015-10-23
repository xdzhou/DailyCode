package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VenomProblemTest
{
	@Test
	public void test()
	{
		VenomProblem algo = new VenomProblem();
		Assert.assertEquals((int)algo.resolve(1000), 10);
		Assert.assertEquals((int)algo.resolve(4), 2);
		Assert.assertEquals((int)algo.resolve(7), 3);
		Assert.assertEquals((int)algo.resolve(1024), 10);
		Assert.assertEquals((int)algo.resolve(16), 4);
	}
}
