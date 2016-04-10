package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class FindMajorNumTest extends CommonTest<Integer[], Integer> {

	@Test
	public void test() {
		check(transform(1, 1, 2, 3, 5, 2, 3, 5, 1, 1, 1, 1, 1), 1);
		check(transform(2, 2, 2, 2), 2);
		check(transform(2, 1, 5, 2, 2, 8, 2), 2);
	}

	@Override
	public Problem<Integer[], Integer> getAlgo() {
		return new FindMajorNum();
	}
}
