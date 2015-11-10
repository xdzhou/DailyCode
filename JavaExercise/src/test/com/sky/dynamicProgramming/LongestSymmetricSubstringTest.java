package com.sky.dynamicProgramming;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sky.common.CommonTest;
 
public class LongestSymmetricSubstringTest extends CommonTest<String, Integer>
{
	@Test
	public void test()
	{
		check("google", 4);
		check("abcdef", 0);
		check("elgoogle", 8);
		check("454sd5456sbaab116565", 4);
	}

	@Override
	@BeforeTest
	public void init()
	{
		setAlgo(new LongestSymmetricSubstring());
	}
}
