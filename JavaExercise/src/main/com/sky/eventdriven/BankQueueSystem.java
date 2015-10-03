package com.sky.eventdriven;

import java.util.PriorityQueue;
import java.util.Random;

public class BankQueueSystem
{
	static boolean[] workedWin;
	static PriorityQueue<Event> leaveEventPQ;
	static PriorityQueue<Event> arriveEventPQ;

	public static void main(String[] args)
	{
		// bank windows(guichet)
		int nbWin = 2;
		int pauseTime = 0;
		workedWin = new boolean[nbWin];
		workedWin[0] = workedWin[1] = false;

		// client info
		int nbClient = 5;
		Client[] clientListe = new Client[nbClient];
		clientListe[0] = new Client(0, 0, 100);
		clientListe[1] = new Client(1, 0, 5);
		clientListe[2] = new Client(2, 2, 5);
		clientListe[3] = new Client(3, 3, 5);
		clientListe[4] = new Client(4, 4, 5);

		// event manager
		leaveEventPQ = new PriorityQueue<Event>();
		arriveEventPQ = new PriorityQueue<Event>();
		for (int i = 0; i < nbClient; i++)
		{
			arriveEventPQ.add(new Event(0, clientListe[i]));
		}

		// simuler
		while (!arriveEventPQ.isEmpty() || !leaveEventPQ.isEmpty())
		{
			Event ae = arriveEventPQ.peek();
			Event le = leaveEventPQ.peek();
			if (ae == null && le != null)
			{
				doLeaveEvent();
			} else if (ae != null && le == null)
			{
				doArriveEvent();
			} else
			{
				if (ae.compareTo(le) >= 0)
					doLeaveEvent();
				else
				{
					int numWin = getNumWin();
					if (numWin == -1)
					{
						ae.getClient().setStartServiceTime(le.getTime());
						doLeaveEvent();
					} else
					{
						doArriveEvent();
					}
				}
			}
		}
	}

	private static void doArriveEvent()
	{
		Event e = arriveEventPQ.poll();
		Client c = e.getClient();
		int numWin = getNumWin();
		c.setNumWin(numWin);
		Event newEvent = new Event(1, c);
		leaveEventPQ.add(newEvent);
		workedWin[numWin] = true;
		System.out.println(c.getStartServiceTime() + " remove--" + e);
		// System.out.println(c.getStartServiceTime()+" add--"+newEvent);
	}

	private static void doLeaveEvent()
	{
		Event e = leaveEventPQ.poll();
		Client c = e.getClient();
		workedWin[c.getNumWin()] = false;
		// System.out.println(e.getTime()+" remove++"+e);
	}

	private static int getNumWin()
	{
		if (workedWin[0] && workedWin[1])
			return -1;
		else if (!workedWin[0] && workedWin[1])
			return 0;
		else if (!workedWin[1] && workedWin[0])
			return 1;
		else
		{
			Random r = new Random();
			return r.nextInt() % 2;
		}
	}

}
