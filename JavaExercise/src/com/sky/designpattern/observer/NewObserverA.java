package com.sky.designpattern.observer;

public class NewObserverA implements Observer {

	@Override
	public void update(Subject subject) {
		if(subject instanceof NewSubject){
			NewSubject ns = (NewSubject)subject;
			System.out.println("observer A:"+ns.getA());
		}
	}

}
