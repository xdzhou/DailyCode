package com.sky.dynamicProgramming;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DPLCSTest
{
	@Test
	public void test()
	{
		DPLCS algo = new DPLCS();
		
		Assert.assertEquals(check(algo, "abcdefg", "apcmzf"), "acf");
		Assert.assertEquals(check(algo, "123654987", "zfddsf"), null);
		Assert.assertEquals(check(algo, "1234", "34"), "34");
	}
	
	private String check(DPLCS algo, String ... list)
	{
		return algo.resolve(list);
	}
}
