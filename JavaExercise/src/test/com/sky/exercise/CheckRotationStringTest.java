package com.sky.exercise;

import org.testng.annotations.Test;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class CheckRotationStringTest extends CommonTest<Pair<String, String>, Boolean> {
	@Test
	public void test() {
		check(Pair.create("sqf", "123"), false);
		check(Pair.create("12345", "45123"), true);
		check(Pair.create("12345", "12"), false);
		check(Pair.create("azert", "ertaz"), true);
	}

	@Override
	public Problem<Pair<String, String>, Boolean> getAlgo() {
		return new CheckRotationString();
	}
}
