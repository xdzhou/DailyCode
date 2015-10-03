package com.sky.codejam.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MinimumScalarProduct
{

	public static void main(String[] args)
	{
		new MinimumScalarProduct().start();
	}

	public void start()
	{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		for (int i = 0; i < N; i++)
		{
			int num = in.nextInt();
			List<Long> list1 = new ArrayList<Long>(num);
			List<Long> list2 = new ArrayList<Long>(num);
			for (int j = 0; j < num; j++)
				list1.add(in.nextLong());
			for (int j = 0; j < num; j++)
				list2.add(in.nextLong());
			Collections.sort(list1);
			Collections.sort(list2);
			long somme = 0;
			for (int j = 0; j < num; j++)
			{
				somme += (list1.get(j) * list2.get(num - 1 - j));
			}
			System.out.println("Case #" + (i + 1) + ": " + somme);
		}
	}
}
