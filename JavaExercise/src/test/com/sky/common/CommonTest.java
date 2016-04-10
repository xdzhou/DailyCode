package com.sky.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;

import com.sky.problem.Problem;
import com.sky.problem.ProblemThreeSolutions;
import com.sky.problem.ProblemTwoSolutions;

public abstract class CommonTest<T, E> {
	private Problem<T, E> mAlgoToTest;

	abstract public Problem<T, E> getAlgo();

	public CommonTest() {
		mAlgoToTest = getAlgo();
		Assert.assertNotEquals(mAlgoToTest, null);
	}

	public Problem<T, E> getProblem() {
		return mAlgoToTest;
	}

	protected void check(T input, E output) {
		if (mAlgoToTest instanceof ProblemThreeSolutions) {
			ProblemThreeSolutions<T, E> threeSolutionAlgo = (ProblemThreeSolutions<T, E>) mAlgoToTest;
			E baseOutPut = threeSolutionAlgo.resolve(input);
			Assert.assertEquals(baseOutPut, threeSolutionAlgo.resolve2(input));
			Assert.assertEquals(baseOutPut, threeSolutionAlgo.resolve3(input));
			if (output != null) {
				Assert.assertEquals(baseOutPut, output);
			}
		} else if (mAlgoToTest instanceof ProblemTwoSolutions) {
			ProblemTwoSolutions<T, E> twoSolutionAlgo = (ProblemTwoSolutions<T, E>) mAlgoToTest;
			E baseOutPut = twoSolutionAlgo.resolve(input);
			Assert.assertEquals(baseOutPut, twoSolutionAlgo.resolve2(input));
			if (output != null) {
				Assert.assertEquals(baseOutPut, output);
			}
		} else {
			Assert.assertEquals(mAlgoToTest.resolve(input), output);
		}
	}

	protected void check(T input) {
		if (mAlgoToTest instanceof ProblemThreeSolutions) {
			ProblemThreeSolutions<T, E> threeSolutionAlgo = (ProblemThreeSolutions<T, E>) mAlgoToTest;

			onOuputReady(input, threeSolutionAlgo.resolve(input));
			onOuputReady(input, threeSolutionAlgo.resolve2(input));
			onOuputReady(input, threeSolutionAlgo.resolve3(input));
		} else if (mAlgoToTest instanceof ProblemTwoSolutions) {
			ProblemTwoSolutions<T, E> twoSolutionAlgo = (ProblemTwoSolutions<T, E>) mAlgoToTest;
			onOuputReady(input, twoSolutionAlgo.resolve(input));
			onOuputReady(input, twoSolutionAlgo.resolve2(input));
		} else {
			onOuputReady(input, mAlgoToTest.resolve(input));
		}
	}

	protected void onOuputReady(T input, E output) {
	}

	// helper method
	protected List<String> generateList(String... data) {
		List<String> result = new ArrayList<>();
		for (String s : data) {
			result.add(s);
		}
		return result;
	}

	protected Set<String> generateSet(String... data) {
		Set<String> result = new HashSet<>();
		for (String s : data) {
			result.add(s);
		}
		return result;
	}

	protected Integer[] transform(Integer... list) {
		return list;
	}

	protected String[] transform(String... list) {
		return list;
	}

}
