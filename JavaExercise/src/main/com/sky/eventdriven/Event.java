package com.sky.eventdriven;

public class Event implements Comparable<Event>
{
	private int time;
	private int type; // 0 arrive 1 leave,
	private Client client;

	public Event(int type, Client client)
	{
		this.type = type;
		if (type == 0)
			this.time = client.getArriveTime();
		else
			this.time = client.getServiceDuring()
					+ client.getStartServiceTime();
		this.client = client;
	}

	public Event(int type, Client client, int time)
	{
		this.type = type;
		this.time = time;
		this.client = client;
	}

	@Override
	public int compareTo(Event e)
	{
		return this.time - e.time;
	}

	public int getTime()
	{
		return time;
	}

	public int getType()
	{
		return type;
	}

	public Client getClient()
	{
		return client;
	}

	@Override
	public String toString()
	{
		return "Event [time=" + time + ", type=" + type + ", client="
				+ client.getId() + "]";
	}

}
