package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class SumIsContinuousNumSequenceTest extends CommonTest<Integer, Integer>
{
	@Test
	public void test()
	{
		check(15, 3);
		check(3, 1);
	}

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new SumIsContinuousNumSequence());
	}
}
