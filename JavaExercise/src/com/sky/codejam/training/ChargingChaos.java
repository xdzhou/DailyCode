package com.sky.codejam.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ChargingChaos {

	public static void main(String[] args) {
		new ChargingChaos().start();
	}
	
	public void start(){
		Scanner in = new Scanner(System.in);
		int nbCase = in.nextInt();
		BREAKFLAG: for(int i=0; i<nbCase; i++){
			int nbSwitch = 0;
			int N = in.nextInt();
			int L = in.nextInt();
			List<Integer> electric = new ArrayList<Integer>(N);
			List<Integer> device = new ArrayList<Integer>(N);
			in.nextLine();
			String[] elcStr = in.nextLine().split(" ");
			for(int j=0; j<N; j++) electric.add(Integer.valueOf(elcStr[j], 2));
			String[] devStr = in.nextLine().split(" ");
			for(int j=0; j<N; j++) device.add(Integer.valueOf(devStr[j], 2));
			int[] elcNBone = getNbOne(electric, L);
			int[] devNBone = getNbOne(device, L);
			List<Integer> specialIndex = new ArrayList<Integer>();
			for(int j=0; j<L; j++){
				if(elcNBone[j] == devNBone[j]){
					if(elcNBone[j] == N/2 && N%2==0) specialIndex.add(j); //not sure
				}else if (elcNBone[j]+devNBone[j] == N) {
					bitSwitch(electric, L-1-j);
					nbSwitch ++;
				}else {
					System.out.println("Case #"+(i+1)+": NOT POSSIBLE");
					break BREAKFLAG;
				}
			}
			while(specialIndex.size()>0){
				
			}
			System.out.println("Case #"+(i+1)+": "+nbSwitch);
		}
	}
	
	private int[] getNbOne(List<Integer> list, int L){
		int[] nbOne = new int[L];
		for(int i=0; i<list.size(); i++)
			for(int j=0; j<L; j++){
				nbOne[j] += list.get(i) &(1<<(L-1-j));
			}
		return nbOne;
	}
	
	private void bitSwitch(List<Integer> list, int position){
		for(int i=0; i<list.size(); i++){
			int temp = list.get(i) ^ (1 << position);
			list.set(i, temp);
		}
	}
	
	private boolean isListEqual(List<Integer> l1, List<Integer> l2){
		Collections.sort(l1);
		Collections.sort(l2);
		for(int i=0; i<l1.size(); i++){
			if(l1.get(i) != l2.get(i)) return false;
		}
		return true;
	}
	
	private boolean isBitEqual(List<Integer> l1, List<Integer> l2, List<Integer> specialIndex, int L){
		List<Integer> copyL1 = new ArrayList<Integer>(l1.size());
		Collections.copy(copyL1, l1);
		List<Integer> copyL2 = new ArrayList<Integer>(l2.size());
		Collections.copy(copyL2, l2);
		for(int index: specialIndex){
			for(int i=0; i<copyL1.size(); i++){
				copyL1.set(i, copyL1.get(i)&(0xFFFFFFFF ^(1<<(L-1-index))));
				copyL2.set(i, copyL2.get(i)&(0xFFFFFFFF ^(1<<(L-1-index))));
			}
		}
		return isListEqual(copyL1, copyL2);
	}

}
