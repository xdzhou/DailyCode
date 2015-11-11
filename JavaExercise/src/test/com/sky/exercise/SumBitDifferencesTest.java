package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class SumBitDifferencesTest extends CommonTest<Integer[], Integer>
{

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new SumBitDifferences());
	}
	
	@Test
	public void test()
	{
		check(transform(1,2), 4);
		check(transform(1,3,5), 8);
	}

}
