package com.sky.dynamicProgramming;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class LongestIncreasingSubsequenceTest extends CommonTest<Integer[], Integer> {
	@Test
	public void test() {
		check(transform(4, 5, 6, 1, 2, 3), 3);
		check(transform(1, 2, 3, 4, 5, 6, 7, 8, 9), 9);
		check(transform(9, 8, 7, 6, 5, 4, 3, 2, 1), 1);
		check(transform(7, 7, 7, 7, 7), 5);
	}

	@Override
	public Problem<Integer[], Integer> getAlgo() {
		return new LongestIncreasingSubsequence();
	}
}
