package com.sky.designpattern.observer;

public class NewSubject extends Subject{
	private int a = 0;
	private int b = 0;
	
	public void setChange(){
		a++;
		b++;
		notifyAllObserver();
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}
	
	
}
