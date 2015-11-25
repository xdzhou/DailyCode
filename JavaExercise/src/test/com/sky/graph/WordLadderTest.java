package com.sky.graph;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WordLadderTest
{
	private WordLadder algo = new WordLadder();
	
	@Test
	public void test()
	{
		check(2, "hit", "cog", "hot","dot","dog","lot","log");
		check(1, "hot", "dog", "hot","dot","dog");
	}
	
	private void check(int size, String start, String stop, String ...data)
	{
		Set<String> words = new HashSet<>();
		for(String s : data)
		{
			words.add(s);
		}
		Assert.assertEquals(algo.findLadders(start, stop, words).size(), size);
		
	}
}
