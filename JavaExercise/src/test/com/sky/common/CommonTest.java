package com.sky.common;

import org.testng.Assert;

import com.sky.problem.Problem;
import com.sky.problem.ProblemThreeSolutions;
import com.sky.problem.ProblemTwoSolutions;

public abstract class CommonTest<T, E>
{
	private Problem<T, E> mAlgoToTest;

	abstract public void init();
	
	public void setAlgo(Problem<T, E> algo)
	{
		mAlgoToTest = algo;
	}
	
	protected void check(T input, E output)
	{
		if(mAlgoToTest instanceof ProblemThreeSolutions)
		{
			ProblemThreeSolutions<T, E> threeSolutionAlgo = (ProblemThreeSolutions<T, E>)mAlgoToTest;
			E baseOutPut = threeSolutionAlgo.resolve(input);
			Assert.assertEquals(baseOutPut, threeSolutionAlgo.resolve2(input));
			Assert.assertEquals(baseOutPut, threeSolutionAlgo.resolve3(input));
			if(output != null)
			{
				Assert.assertEquals(baseOutPut, output);
			}
		}
		else if (mAlgoToTest instanceof ProblemTwoSolutions) 
		{
			ProblemTwoSolutions<T, E> twoSolutionAlgo = (ProblemTwoSolutions<T, E>)mAlgoToTest;
			E baseOutPut = twoSolutionAlgo.resolve(input);
			Assert.assertEquals(baseOutPut, twoSolutionAlgo.resolve2(input));
			if(output != null)
			{
				Assert.assertEquals(baseOutPut, output);
			}
		}
		else 
		{
			Assert.assertEquals(mAlgoToTest.resolve(input), output);
		}
	}
	
	protected Integer[] transform(Integer ... list)
	{
		return list;
	}
	
	protected String[] transform(String ... list)
	{
		return list;
	}

}
