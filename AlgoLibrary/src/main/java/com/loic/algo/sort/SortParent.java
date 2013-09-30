package com.loic.algo.sort;

public abstract class SortParent {
	
	public abstract <T> void sort(Comparable<T>[] a);
	
	public <T> void exch(Comparable<T>[] a, int i, int j){
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static <T> void show(Comparable<T>[] a){
		for(int i=0; i<a.length; i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	//check if the list is sorted (from samll to big)
	public static <T> boolean isSorted(Comparable<T>[] a){
		for(int i=1; i<a.length; i++){
			if(a[i-1].compareTo((T) a[i])>0) return false;
		}
		return true;
	}

}
