package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MoveStarTest
{
	@Test
	public void test()
	{
		MoveStar algo = new MoveStar();
		Assert.assertEquals(algo.resolve("ab**cd**e*12"), "*****abcde12");
		Assert.assertEquals(algo.resolve("*az*12*"), "***az12");
		Assert.assertEquals(algo.resolve("*****"), "*****");
		Assert.assertEquals(algo.resolve("12345"), "12345");
	}
}
