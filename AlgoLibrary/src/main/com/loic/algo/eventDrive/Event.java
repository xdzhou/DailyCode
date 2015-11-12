package com.loic.algo.eventDrive;

public abstract class Event implements Comparable<Event>
{
	public final double time;
	
	public Event(double time)
	{
		this.time = time;
	}
	
	
	
	public abstract boolean isValid();
	
	@Override
	public int compareTo(Event o)
	{
		return (int) (time - o.time);
	}
}
