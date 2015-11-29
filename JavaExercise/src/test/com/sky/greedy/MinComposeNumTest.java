package com.sky.greedy;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class MinComposeNumTest extends CommonTest<Integer[], String>
{
	@Test
	public void test()
	{
		check(transform(1,2,3,4,5,6,7), "1234567");
		check(transform(1, 11, 21, 12, 99), "111122199");
		check(transform(10, 15, 25, 85), "10152585");
		check(transform(1,1,1,1,1,1,1), "1111111");
	}

	@Override
	public Problem<Integer[], String> getAlgo()
	{
		return new MinComposeNum();
	}
}
