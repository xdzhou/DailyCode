package com.sky.dynamicProgramming;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class DPLCSTest extends CommonTest<String[], String>
{
	@Test
	public void test()
	{
		check(transform("abcdefg", "apcmzf"), "acf");
		check(transform("123654987", "zfddsf"), null);
		check(transform("1234", "34"), "34");
	}

	@Override
	public Problem<String[], String> getAlgo()
	{
		return new DPLCS();
	}
}
