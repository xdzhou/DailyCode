package com.loic.algo.DP;

public class KnapsackTest {

	public static void main(String[] args) {
		int capacity = 6 ;   
		int weight[] = {6 , 4 , 2 , 4 , 3}; 
	    int value[] = {8 , 10 , 4 , 5 , 5};       	    
	    
		Knapsack bagpb = new Knapsack();
		System.out.println(bagpb.getMaxValue(capacity, weight, value));

	}

}
