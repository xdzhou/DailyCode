package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class JumpingNumbersTest extends CommonTest<Integer, Integer>
{

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new JumpingNumbers());
	}

	@Test
	public void test()
	{
		check(9, 9);
		check(20, 12);
		check(105, 28);
	}
}
