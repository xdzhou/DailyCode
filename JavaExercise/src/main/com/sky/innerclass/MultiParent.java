package com.sky.innerclass;

import com.sky.designpattern.visitor.Element;
import com.sky.designpattern.visitor.ElementA;
import com.sky.designpattern.visitor.ElementB;

public class MultiParent {
	private int price=0;
	
	private class parent1 extends ElementA{
		public int getPrice(){
			return price ++;
		}
	}
	
	private class parent2 extends ElementB{
		public int getPrice(){
			return price ++;
		}
	}
	
	public int getElementA(){
		return new parent1().getPrice();
	}
	public int getElementB(){
		return new parent2().getPrice();
	}
}
