package com.sky.codingame.training;

import java.util.HashMap;
import java.util.Scanner;

public class Surface
{

	public static void main(String[] args)
	{
		int width, height, N;
		char table[][];

		Scanner in = new Scanner(System.in);
		width = in.nextInt();
		height = in.nextInt();
		in.nextLine(); // don't know why

		table = new char[width][height];
		for (int i = 0; i < height; i++)
		{
			String line = in.nextLine();
			for (int j = 0; j < width; j++)
			{
				if (line.charAt(j) == '#')
					table[j][i] = 1;
				else
					table[j][i] = 0;
			}
		}

		N = in.nextInt();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++)
		{
			int px = in.nextInt();
			int py = in.nextInt();
			if (map.containsKey((int) table[px][py]))
			{
				System.out.println("" + map.get((int) table[px][py]));
			} else
			{
				int surface = getSurfaceWater(table, width, height, px, py,
						i + 2);
				if (surface != 0)
					map.put(i + 2, surface);
				System.out.println(surface);
			}
		}
		in.close();
	}

	private static int getSurfaceWater(char[][] table, int width, int height, int px, int py, int id)
	{
		int surface;
		if (px < 0 || py < 0 || px >= width || py >= height)
		{
			return 0;
		}
		if (table[px][py] == 0)
		{
			surface = 1;
			table[px][py] = (char) id;
			surface += getSurfaceWater(table, width, height, px - 1, py, id);
			surface += getSurfaceWater(table, width, height, px + 1, py, id);
			surface += getSurfaceWater(table, width, height, px, py - 1, id);
			surface += getSurfaceWater(table, width, height, px, py + 1, id);
		} 
		else
		{
			return 0;
		}
		return surface;

	}

}
