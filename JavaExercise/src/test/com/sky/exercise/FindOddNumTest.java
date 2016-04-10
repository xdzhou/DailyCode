package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class FindOddNumTest extends CommonTest<Integer[], Integer> {
	@Test
	public void test() {
		check(transform(1, 2, 2), 1);
		check(transform(1, 1, 2, 2, 2, 3, 4, 4, 3), 2);
	}

	@Override
	public Problem<Integer[], Integer> getAlgo() {
		return new FindOddNum();
	}
}
