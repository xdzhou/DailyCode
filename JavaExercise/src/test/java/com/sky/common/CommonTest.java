package com.sky.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sky.problem.Problem;
import com.sky.problem.ProblemThreeSolutions;
import com.sky.problem.ProblemTwoSolutions;
import org.testng.Assert;

public abstract class CommonTest<T, E> {
    private Problem<T, E> mAlgoToTest;

    public CommonTest() {
        mAlgoToTest = getAlgo();
        Assert.assertNotEquals(mAlgoToTest, null);
    }

    protected abstract Problem<T, E> getAlgo();

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

            onOutputReady(input, threeSolutionAlgo.resolve(input));
            onOutputReady(input, threeSolutionAlgo.resolve2(input));
            onOutputReady(input, threeSolutionAlgo.resolve3(input));
        } else if (mAlgoToTest instanceof ProblemTwoSolutions) {
            ProblemTwoSolutions<T, E> twoSolutionAlgo = (ProblemTwoSolutions<T, E>) mAlgoToTest;
            onOutputReady(input, twoSolutionAlgo.resolve(input));
            onOutputReady(input, twoSolutionAlgo.resolve2(input));
        } else {
            onOutputReady(input, mAlgoToTest.resolve(input));
        }
    }

    protected void onOutputReady(T input, E output) {
    }

    // helper method
    protected <E> List<E> generateList(E... data) {
        return Arrays.asList(data);
    }

    protected Set<String> generateSet(String... data) {
        Set<String> result = new HashSet<String>(data.length);
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
