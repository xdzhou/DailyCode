package com.sky.designpattern.observer;

public class Test
{

	public static void main(String[] args) throws ClassNotFoundException
	{
		NewSubject subject = new NewSubject();
		NewObserverA oa = new NewObserverA();
		NewObserverB ob = new NewObserverB();
		subject.addObserver(oa);
		subject.addObserver(ob);

		subject.setChange();

	}

}
