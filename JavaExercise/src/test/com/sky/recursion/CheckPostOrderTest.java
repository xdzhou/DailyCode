package com.sky.recursion;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckPostOrderTest
{
	@Test
	public void test()
	{
		CheckPostOrder algo = new CheckPostOrder();
		Assert.assertEquals(process(algo, 5,7,6,9,11,10,8), true);
		Assert.assertEquals(process(algo, 1,1,1,1,1,1,1), true);
		Assert.assertEquals(process(algo, 7,4,6,5), false);
	}
	
	private boolean process(CheckPostOrder algo, Integer ... param)
	{
		return algo.resolve(param);
	}
}
