package com.sky.exercise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;

public class AdditiveNumberTest extends CommonTest<String, Boolean>
{

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new AdditiveNumber());
	}

	@Test
	public void test()
	{
		check("112358", true);
		check("199100199", true);
		check("123", true);
		check("000000", true);
		check("00654160000", false);
	}
}
