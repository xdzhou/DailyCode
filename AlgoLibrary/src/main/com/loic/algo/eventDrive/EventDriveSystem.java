package com.loic.algo.eventDrive;

import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EventDriveSystem
{
	protected static final Logger Log = LoggerFactory.getLogger(EventDriveSystem.class);
	
	protected PriorityQueue<Event> mEventsQueue;
	protected double mTime;
	
	public EventDriveSystem()
	{
		mEventsQueue = new PriorityQueue<>();
		mTime = 0;
	}
	
	public void simulate()
	{
		while(!mEventsQueue.isEmpty())
		{
			if(isFinish(mTime))
			{
				break;
			}
			Event curEvent = mEventsQueue.poll();
			if(curEvent.isValid())
			{
				//Log.debug("start process event : {}", curEvent);
				mTime = curEvent.time;
				processEvent(curEvent);
			}
		}
		Log.debug("simulate stop !");
	}
	
	protected void addNewEvent(Event event)
	{
		mEventsQueue.add(event);
	}
	
	protected abstract boolean isFinish(double time);
	
	protected abstract void processEvent(Event event);
}
