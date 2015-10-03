package com.sky.codejam.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Milkshakes {

	public static void main(String[] args) {
		//new Milkshakes().start();
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(2);
		list.add(1);
		list.remove(1);
		for(int i=0;i<list.size();i++){
			System.out.println("i="+i+" - "+list.get(i));
		}
	}
	
	public void start(){// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		int numCase = in.nextInt();
		for(int i=0; i<numCase; i++){
			int numType = in.nextInt();
			int numClient = in.nextInt();
			int[] typeMikeShark = new int[numType];
			for(int j=0; j<numType; j++) typeMikeShark[j] = -1;
			List<ArrayList<Integer>> untreatedList = new ArrayList<ArrayList<Integer>>();
			
			boolean isPossible = true;
			for(int j=0; j<numClient; j++){
				int numFlavor = in.nextInt();
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				for(int m=0; m<numFlavor; m++){
					int type = in.nextInt()-1;
					if(typeMikeShark[type]==-1){
						tempList.add(type);
						tempList.add(in.nextInt());
					}
				}
				if(tempList.size()==0){
					isPossible = false;
					break;
				}else if (tempList.size()==2) {
					typeMikeShark[tempList.get(0)] = tempList.get(1);
				}else {
					untreatedList.add(tempList);
				}
			}
			if(!isPossible) System.out.println("Case #"+(i+1)+" IMPOSSIBLE");
			
		}
	}

}
