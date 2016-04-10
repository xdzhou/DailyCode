package com.sky.recursion;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class CheckPostOrderTest extends CommonTest<Integer[], Boolean> {
	@Test
	public void test() {
		check(transform(5, 7, 6, 9, 11, 10, 8), true);
		check(transform(1, 1, 1, 1, 1, 1, 1), true);
		check(transform(7, 4, 6, 5), false);
	}

	@Override
	public Problem<Integer[], Boolean> getAlgo() {
		return new CheckPostOrder();
	}
}
