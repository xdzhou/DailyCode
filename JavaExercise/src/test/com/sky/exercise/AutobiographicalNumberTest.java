package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class AutobiographicalNumberTest extends CommonTest<Integer, Boolean>
{
	@BeforeTest
	public void init()
	{
		setAlgo(new AutobiographicalNumber());
	}
	
	@Test
	public void test()
	{
		check(2020, null);
		check(1210, null);
		check(132545, null);
	}
}
