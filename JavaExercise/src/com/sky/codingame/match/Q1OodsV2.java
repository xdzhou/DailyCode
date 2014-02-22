package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q1OodsV2 {
	
	public static void main(String args[]) {
		int N, C;
		List<Integer> budget;
		//int pay[];
		int totalBudget = 0;
		
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		C = in.nextInt();
		budget = new ArrayList<>(N);
		
		for (int i = 0; i < N; i++) {
			int b = in.nextInt();
			totalBudget += b;
			budget.add(b);
		}
		if(totalBudget<C) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		Collections.sort(budget);
		int moyen = C/N;
		
		pay(0, budget, C);
		
	}
	
	private static void pay(int numPeople, List<Integer> monerys, int priceToPay){
		if(numPeople >= monerys.size()) return;
		int moyen = priceToPay / (monerys.size()-numPeople);
		int aleardyPay = 0, i;
		for(i=numPeople; i<monerys.size(); i++){
			if(monerys.get(i) < moyen) {
				aleardyPay += monerys.get(i);
				System.out.println(monerys.get(i));
			}
			else break;
		}
		if(aleardyPay == 0){
			int moreNuw = priceToPay % (monerys.size()-numPeople);
			for(int j=0; j<monerys.size()-numPeople-moreNuw; j++) System.out.println(moyen);
			for(int j=0; j<moreNuw; j++) System.out.println(moyen+1);
			pay(monerys.size(), monerys, 0);
		}else {
			pay(i, monerys, priceToPay-aleardyPay);
		}
	}
}
