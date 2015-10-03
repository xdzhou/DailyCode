package com.sky.codingame.training;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Note
{
	public int sizeC = 0;
	public long totalL = 0L;

	public Note(int sizeC, long totalL)
	{
		this.sizeC = sizeC;
		this.totalL = totalL;
	}
}

public class RollerCoaster
{

	public static void main(String args[])
	{
		int L, C, N;
		Queue<Integer> queue = new LinkedList<Integer>();

		Scanner in = new Scanner(System.in);
		L = in.nextInt();
		C = in.nextInt();
		N = in.nextInt();
		for (int i = 0; i < N; i++)
		{
			queue.offer(in.nextInt());
		}
		int cap;
		int nbGroup = queue.size();
		int numFlag = 1;
		HashMap<Integer, Note> noteMap = new HashMap<Integer, Note>();
		long resulta = 0;
		for (int i = 1; i <= C; i++)
		{
			if (noteMap.containsKey(numFlag))
			{

			} else
			{
				noteMap.put(numFlag, new Note(i, resulta));
			}

			int size = nbGroup;
			cap = 0;
			while (true)
			{
				cap += queue.peek();
				if (cap <= L)
				{
					queue.offer(queue.poll());
					size--;
					if (size == 0)
						break;
				} else
					break;
			}
			if (cap > L)
				cap -= queue.element();
			System.out.println("cap = " + cap);
			resulta += cap;
		}
		System.out.println(resulta);
	}

}
