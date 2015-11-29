package com.sky.exercise;

import org.testng.annotations.Test;

import com.loic.algo.common.Point;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class MaxPointsInLineTest extends CommonTest<Point[], Integer>
{

	@Test
	public void test()
	{
		check(generateList(0,0, 1,1, 2,2, 0,0), 4);
		check(generateList(2,3, 3,3, -5,3), 3);
		check(generateList(1,1, 1,1, 1,1), 3);
		check(generateList(1,1, 1,1, 1,1, 1,1, 2,2), 5);
	}
	
	private Point[] generateList(int ...p)
	{
		int len = p.length >>> 1;
		Point[] result = new Point[len];
		
		for(int i=0; i<len; i++)
		{
			result[i] = new Point(p[i*2], p[i*2+1]);
		}
		return result;
	}

	@Override
	public Problem<Point[], Integer> getAlgo()
	{
		return new MaxPointsInLine();
	}
}
