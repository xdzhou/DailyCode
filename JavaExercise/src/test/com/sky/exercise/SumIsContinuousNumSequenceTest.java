package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SumIsContinuousNumSequenceTest
{
	@Test
	public void test()
	{
		SumIsContinuousNumSequence algo = new SumIsContinuousNumSequence();
		Assert.assertEquals((int)algo.resolve(15), 3);
		Assert.assertEquals((int)algo.resolve(3), 1);
	}
}
