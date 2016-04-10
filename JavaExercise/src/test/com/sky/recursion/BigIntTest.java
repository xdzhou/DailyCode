package com.sky.recursion;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BigIntTest {
	@Test
	public void testIntAdd() {
		BigInt bigInt = new BigInt();
		bigInt.add(999999999);
		Assert.assertEquals("999999999", bigInt.getPrintablValue());
		bigInt.add(1);
		Assert.assertEquals("1000000000", bigInt.getPrintablValue());
		bigInt.add(999999999);
		Assert.assertEquals("1999999999", bigInt.getPrintablValue());

		bigInt.add(999999999);
		Assert.assertEquals("2999999998", bigInt.getPrintablValue());
		bigInt.add(999999999);
		Assert.assertEquals("3999999997", bigInt.getPrintablValue());
	}

	@Test
	public void testBigIntAdd() {
		BigInt bigInt = new BigInt("1952234567890");
		Assert.assertEquals("1952234567890", bigInt.getPrintablValue());

		bigInt.add(new BigInt("1000000000000"));
		Assert.assertEquals("2952234567890", bigInt.getPrintablValue());
	}
}
