package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class SumIsContinuousNumSequenceTest extends CommonTest<Integer, Integer> {
	@Test
	public void test() {
		check(15, 3);
		check(3, 1);
	}

	@Override
	public Problem<Integer, Integer> getAlgo() {
		return new SumIsContinuousNumSequence();
	}
}
