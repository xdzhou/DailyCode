package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class MoveStarTest extends CommonTest<String, String> {
	@Test
	public void test() {
		check("ab**cd**e*12", "*****abcde12");
		check("*az*12*", "***az12");
		check("*****", "*****");
		check("12345", "12345");
	}

	@Override
	public Problem<String, String> getAlgo() {
		return new MoveStar();
	}
}
