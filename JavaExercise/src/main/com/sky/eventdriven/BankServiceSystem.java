package com.sky.eventdriven;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.loic.algo.eventDrive.Event;
import com.loic.algo.eventDrive.EventDriveSystem;

public class BankServiceSystem extends EventDriveSystem
{
	Random r = new Random();
	private boolean[] windows;
	private Queue<Client> mQueue;
	
	public BankServiceSystem()
	{
		super();
		mQueue = new LinkedList<>();
		//we have 2 window in service
		windows = new boolean[2];
		Arrays.fill(windows, true);
		Client c1 = new Client("Loic");
		Client c2 = new Client("Michel");
		Client c3 = new Client("Helene");
		addNewEvent(new ArriveEvent(c1, 1));
		addNewEvent(new ArriveEvent(c2, 3));
		addNewEvent(new ArriveEvent(c3, 7));
	}

	@Override
	protected boolean isFinish(double time)
	{
		return false;
	}

	@Override
	protected void processEvent(Event event)
	{
		if(event instanceof ArriveEvent)
		{
			Client c = ((ArriveEvent)event).client;
			c.serviceDuration = Math.abs((r.nextInt()+10) % 20);
			int win = getAvailableWindow();
			if(win >= 0)
			{
				Log.debug("T : {} - client : {} arrives, and will be serviced in window {}", event.time, c, win);
				windows[win] = false;
				addNewEvent(new LeaveEvent(c, event.time + c.serviceDuration, win));
			}
			else 
			{
				Log.debug("T : {} - client : {} arrives, and will wait in queue", event.time, c);
				mQueue.add(c);
			}
		}
		else if (event instanceof LeaveEvent) 
		{
			Log.debug("T : {} - client : {} leave", event.time, ((LeaveEvent)event).client);
			
			int win = ((LeaveEvent)event).winNum;
			Client clientInWait = mQueue.poll();
			if(clientInWait != null)
			{
				Log.debug("T : {} - client : {} stop waiting, and will be serviced in window {}", event.time, clientInWait, win);
				addNewEvent(new LeaveEvent(clientInWait, event.time + clientInWait.serviceDuration, win));
			}
			else 
			{
				windows[win] = true;
			}
		}
		else 
		{
			Log.debug("Unknown Event : {}", event);
		}
	}
	
	private int getAvailableWindow()
	{
		for(int i = 0; i < windows.length; i++)
		{
			if(windows[i])
			{
				return i;
			}
		}
		return -1;
	}
	
	//inner class
	private static class Client
	{
		private String name;
		private double serviceDuration;
		
		public Client(String name)
		{
			this.name = name;
		}

		@Override
		public String toString()
		{
			return name;
		}
	}
	
	private static class ArriveEvent extends Event
	{
		private Client client;
		
		public ArriveEvent(Client client, double time)
		{
			super(time);
			this.client = client;
		}

		@Override
		public boolean isValid()
		{
			return true;
		}
	}
	
	private static class LeaveEvent extends Event
	{
		private Client client;
		private int winNum;
		
		public LeaveEvent(Client client, double time, int win)
		{
			super(time);
			this.client = client;
			this.winNum = win;
		}

		@Override
		public boolean isValid()
		{
			return true;
		}
	}

}
