package com.sky.codingame.training;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ShortestTransformPathTest
{
	private ShortestTransformPath<Integer> algo;
	
	@BeforeTest
	public void Init()
	{
		algo = new ShortestTransformPath<Integer>();
	}

	@Test
	public void test1()
	{
		algo.addNewLien(1, 2);
		algo.addNewLien(2, 3);
		algo.addNewLien(3, 4);
		algo.addNewLien(3, 7);
		algo.addNewLien(4, 5);
		algo.addNewLien(4, 6);
		algo.addNewLien(7, 8);
		Assert.assertEquals(algo.getShortestTransformPathLength(), 2);
	}
}
