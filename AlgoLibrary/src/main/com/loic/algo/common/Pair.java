package com.loic.algo.common;

public class Pair<T, E> implements Cloneable
{
	private T first;
	private E second;
	
	public Pair(T first, E second)
	{
		this.first = first;
		this.second = second;
	}

	public T getFirst()
	{
		return first;
	}

	public void setFirst(T first)
	{
		this.first = first;
	}

	public E getSecond()
	{
		return second;
	}

	public void setSecond(E second)
	{
		this.second = second;
	}
	
	public Pair<T, E> clone()
	{
		return new Pair<T, E>(first, second);
	}

	@Override
	public String toString()
	{
		return "[" + first + ", " + second + "]";
	}
}
