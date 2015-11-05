package com.sky.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test
{

	public void test()
	{
		Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int X = in.nextInt();
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("answer");
	}
	
	private static boolean isAutobiographicalNum(int a)
	{
		char[] chars = Integer.toString(a).toCharArray();
		int len = chars.length;
		int[] num = new int[chars.length];
		int sum = 0;
		for(int i=0; i<chars.length; i++)
		{
			num[i] = chars[i] - '0';
			sum += num[i];
		}
		Map<Integer, Integer> map = new HashMap<>(len);
		if(sum == chars.length && num[chars.length - 1] == 0)
		{
			for(int i : num)
			{
				if(map.containsKey(i))
				{
					
				}
			}
		}
		return false;
	}
}
