package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class AutobiographicalNumberTest extends CommonTest<Integer, Boolean>
{

	@Test
	public void test()
	{
		check(2020, null);
		check(1210, null);
		check(132545, null);
	}

	@Override
	public Problem<Integer, Boolean> getAlgo()
	{
		return new AutobiographicalNumber();
	}
}
