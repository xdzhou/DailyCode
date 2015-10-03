package com.sky.codingame.training;

import java.util.Scanner;

class Task implements Comparable{
    public int debut = 0;
    public int fin = 0;
    
	@Override
	public int compareTo(Object o) {
		Task task = (Task) o;
		return fin - task.fin;
	}
}

public class SuperComputer {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int nb = 0;
		int position = 0;
		
		int n = in.nextInt();
		Task jobs[] = new Task[n];
		for (int i = 0; i < n; i++) {
			jobs[i] = new Task();
			jobs[i].debut = in.nextInt();
			jobs[i].fin = in.nextInt() - 1 + jobs[i].debut;
		}
		
		sortShell(jobs);
		position = jobs[0].debut - 1;
		for (int i = 0; i < n; i++) {
			if(jobs[i].debut > position) {
				position = jobs[i].fin;
				nb++;
			}
		}
		
		System.out.println(nb);
		
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////
	public static  void sortShell(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h<N/3) h=3*h+1; //1,4,13,40
		while(h>=1){
			for(int i=h; i<N; i++){
				for(int j=i; j>=h && less(a[j], a[j-h]); j-=h){
					exch(a, j-h, j);
				}
			}
			h=h/3;
		}
	}
	
	private static  void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private static boolean less(Comparable c1, Comparable c2){
		return c1.compareTo(c2)<0;
	}

}
