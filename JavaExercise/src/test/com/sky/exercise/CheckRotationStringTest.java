package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckRotationStringTest
{
	@Test
	public void test()
	{
		CheckRotationString algo = new CheckRotationString();
		Assert.assertEquals(algo.isRotionString("sqf", "123"), false);
		Assert.assertEquals(algo.isRotionString("12345", "45123"), true);
		Assert.assertEquals(algo.isRotionString("12345", "12"), false);
		Assert.assertEquals(algo.isRotionString("azert", "ertaz"), true);
	}
}
