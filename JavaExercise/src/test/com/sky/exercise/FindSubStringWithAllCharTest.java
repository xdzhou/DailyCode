package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindSubStringWithAllCharTest
{
	FindSubStringWithAllChar algo;
	
	@BeforeTest
	public void Init()
	{
		algo = new FindSubStringWithAllChar();
	}
	
	@Test
	public void testOneChar()
	{
		String result = algo.findAllChar("00000000000", 1);
		System.out.println(result);
		Assert.assertEquals(result, "0");
	}
	
	@Test
	public void testThreeChar()
	{
		String result = algo.findAllChar("000212102002202102012220210", 3);
		System.out.println(result);
		Assert.assertEquals(result.length(), 3);
	}
	
	@Test
	public void testTenChar()
	{
		FindSubStringWithAllChar algo = new FindSubStringWithAllChar();
		String result = algo.findAllChar("202105649848910207523690841", 10);
		System.out.println(result);
		Assert.assertEquals(result.length(), 10);
	}
}
