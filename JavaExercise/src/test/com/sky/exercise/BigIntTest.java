package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BigIntTest
{
	@Test
	public void test()
	{
		BigInt bigInt = new BigInt();
		bigInt.increase(999999999);
		Assert.assertEquals("999999999", bigInt.getPrintablValue());
		bigInt.increase(1);
		Assert.assertEquals("1000000000", bigInt.getPrintablValue());
		bigInt.increase(999999999);
		Assert.assertEquals("1999999999", bigInt.getPrintablValue());
		
		bigInt.increase(999999999);
		Assert.assertEquals("2999999998", bigInt.getPrintablValue());
		bigInt.increase(999999999);
		Assert.assertEquals("3999999997", bigInt.getPrintablValue());
	}
}
