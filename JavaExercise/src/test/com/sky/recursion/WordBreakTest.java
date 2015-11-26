package com.sky.recursion;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WordBreakTest
{

	@Test
	public void test()
	{
		WordBreak algo = new WordBreak();
		check(algo, "catsanddog", "cat", "cats", "and", "sand", "dog");
		
		check(algo, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", 
				"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
	}
	
	private void check(WordBreak algo, String s, String ...data)
	{
		Set<String> dataSet = new HashSet<>();
		for(String str : data)
		{
			dataSet.add(str);
		}
	}
}
