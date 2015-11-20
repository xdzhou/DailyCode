package com.sky.recursion;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class CombinationParenthesTest extends CommonTest<Integer, Integer>
{

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new CombinationParenthes());
	}

	@Test
	public void test()
	{
		check(1, 1);
		check(2, 2);
		check(3, 5);
	}
}
