package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContinuousCardsTest
{
	@Test
	public void test()
	{
		ContinuousCards algo = new ContinuousCards();
		Assert.assertEquals(check(algo, 1), true);
		Assert.assertEquals(check(algo, 1, 2, 3), true);
		Assert.assertEquals(check(algo, 1, 2, 5), false);
		Assert.assertEquals(check(algo, 0, 2, 4, 5), true);
		Assert.assertEquals(check(algo, 0, 3, 4, 7), false);
		Assert.assertEquals(check(algo, 0, 0), true);
		Assert.assertEquals(check(algo, 0, 0, 2), true);
		Assert.assertEquals(check(algo, 0, 0, 2, 4), true);
		Assert.assertEquals(check(algo, 0, 0, 2, 5, 6, 7), true);
	}
	
	private boolean check(ContinuousCards algo, Integer ... list)
	{
		return algo.resolve(list);
	}
}
