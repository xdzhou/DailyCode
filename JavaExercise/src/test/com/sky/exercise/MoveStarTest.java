package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class MoveStarTest extends CommonTest<String, String>
{
	@Test
	public void test()
	{
		check("ab**cd**e*12", "*****abcde12");
		check("*az*12*", "***az12");
		check("*****", "*****");
		check("12345", "12345");
	}

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new MoveStar());
	}
}
