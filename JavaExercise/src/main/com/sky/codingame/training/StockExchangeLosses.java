package com.sky.codingame.training;

import java.util.Scanner;

public class StockExchangeLosses
{

	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		int min;

		int n = in.nextInt();
		int tableValut[] = new int[n];
		int tableMin[] = new int[n];
		for (int i = 0; i < n; i++)
		{
			tableValut[i] = in.nextInt();
		}
		min = tableValut[n - 1];
		for (int i = n - 1; i >= 0; i--)
		{
			if (tableValut[i] < min)
				min = tableValut[i];
			tableMin[i] = min;
		}
		min = 0;
		for (int i = 0; i < n - 1; i++)
		{
			if (tableValut[i] > tableMin[i + 1])
				min = (min < tableMin[i + 1] - tableValut[i]) ? min : tableMin[i + 1] - tableValut[i];
		}

		System.out.print(min);
		in.close();
	}
}
