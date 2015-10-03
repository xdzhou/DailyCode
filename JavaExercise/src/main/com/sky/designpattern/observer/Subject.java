package com.sky.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject
{
	private List<Observer> list = new ArrayList<Observer>();

	public void addObserver(Observer o)
	{
		if (o != null)
		{
			list.add(o);
		}
	}

	public void deleteObserver(Observer o)
	{
		list.remove(o);
	}

	public void notifyAllObserver()
	{
		for (Observer o : list)
		{
			o.update(this);
		}
	}

}
