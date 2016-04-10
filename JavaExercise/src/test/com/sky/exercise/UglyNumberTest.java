package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class UglyNumberTest extends CommonTest<Integer, Integer> {

	@Test
	public void test() {
		check(3, null);
		check(10, null);
		check(99, null);
		check(911, null);
	}

	@Override
	public Problem<Integer, Integer> getAlgo() {
		return new UglyNumber();
	}
}
