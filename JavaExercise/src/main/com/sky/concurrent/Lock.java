package com.sky.concurrent;

public class Lock {
	private boolean isLocked = false;
	private Thread currenThread = null;
	private int nbLocked = 0;
	
	public synchronized void lock() throws InterruptedException{
		while(isLocked && !Thread.currentThread().equals(currenThread)){
			wait();
		}
		isLocked = true;
		currenThread = Thread.currentThread();
		nbLocked++;
	}
	
	public synchronized void unlock(){		
		nbLocked --;
		if(nbLocked==0) {
			isLocked = false;
			notify();
		}
	}
}
