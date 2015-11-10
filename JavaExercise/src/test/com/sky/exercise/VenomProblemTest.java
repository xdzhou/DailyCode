package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class VenomProblemTest extends CommonTest<Integer, Integer>
{
	@Test
	public void test()
	{
		check(1000, 10);
		check(4, 2);
		check(7, 3);
		check(1024, 10);
		check(16, 4);
	}

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new VenomProblem());
	}
}
