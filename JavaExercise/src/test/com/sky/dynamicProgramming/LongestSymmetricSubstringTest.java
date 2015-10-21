package com.sky.dynamicProgramming;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestSymmetricSubstringTest
{
	@Test
	public void test()
	{
		LongestSymmetricSubstring algo = new LongestSymmetricSubstring();
		Assert.assertEquals((int)algo.resolve("google"), 4);
		Assert.assertEquals((int)algo.resolve("abcdef"), 0);
		Assert.assertEquals((int)algo.resolve("elgoogle"), 8);
		Assert.assertEquals((int)algo.resolve("454sd5456sbaab116565"), 4);
	}
}
