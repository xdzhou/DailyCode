package com.sky.recursion;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class NumberCountTest extends CommonTest<Integer, Integer[]>
{

	@Override
	public Problem<Integer, Integer[]> getAlgo()
	{
		return new NumberCount();
	}
	
	@Test
	public void test()
	{
		check(1235, null);
		check(1212, null);
	}

}
