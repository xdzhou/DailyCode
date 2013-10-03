package com.loic.algo;

public class SortAlgo {
	/*
	 * 选择排序：每次把最小的放在前面
	 * @param a the list of comparable to sort
	 */
	public static  void sortSelect(Comparable[] a) {
		int N = a.length;
		for(int i=0; i<N; i++){
			int min = i;
			for(int j=i+1; j<N; j++){
				if(less(a[j], a[min]))
					exch(a, min, j);
			}
		}
	}
	
	/*
	 * 插入排序：每次左边的数都是有序的
	 * @param a the list of comparable to sort
	 */
	public static  void sortInsert(Comparable[] a) {
		int N = a.length;
		for(int i=1; i<N; i++){
			for(int j=i; j>0 && less(a[j], a[j-1]); j--)
				exch(a, j-1, j);
		}
	}
	
	/*
	 * 希尔排序：插入排序的改进版，步长增加
	 * @param a the list of comparable to sort
	 */
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
	

	private static Comparable[] aux;
	/*
	 * 归并排序(自底向上)：2个有序数组归并为更大的有序数组
	 * @param a the list of comparable to sort
	 */
	public static  void sortMergeBU(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int sz=1; sz<N; sz+=sz){
			for(int lo=0; lo<N-sz; lo+=sz+sz)
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
		}
	}
	/*
	 * 归并排序(自上向下)：分治思想，大的数组逐渐分为小数组
	 * @param a the list of comparable to sort
	 */
	public static  void sortMerge(Comparable[] a) {
		aux = new Comparable[a.length];
		sortMerge(a, 0, a.length-1);
	}
	
	private static  void sortMerge(Comparable[] a, int lo, int hi) {
		if(hi<=lo) return;
		int mid = lo+(hi-lo)/2;
		sortMerge(a, lo, mid);
		sortMerge(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	//合并2个有序数组
	private static void merge(Comparable[] a, int lo, int mid, int hi){
		//将a[lo...mid] 和 a[mid+1...hi] 合并
		int i = lo, j = mid+1;				
		for(int k=lo; k<=hi; k++){
			aux[k]=a[k];
		}
		for(int k=lo;k<=hi; k++){
			if(i>mid)	a[k]=aux[j++];
			else if (j>hi) a[k]=aux[i++];
			else if (less(aux[j],aux[i])) a[k]=aux[j++];
			else	a[k]=aux[i++];
		}
	}
	
	
	/*
	 * 快速排序：使用切分，每次找到把一个元素放到准确的位置
	 * @param a the list of comparable to sort
	 */
	public static  void sortQuick(Comparable[] a) {
		sortQuick(a, 0, a.length-1);
	}
	
	private static  void sortQuick(Comparable[] a, int lo, int hi) {
		if(hi<=lo) return;
		int j = partition(a, lo, hi);
		sortQuick(a, lo, j-1);
		sortQuick(a, j+1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi){
		int i=lo, j=hi+1;
		Comparable p = a[lo]; //切分元素
		while(true){
			while(less(a[++i], p)) if(i==hi) break;
			while(less(p, a[--j])) if(j==lo) break;
			if(i>=j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private static  void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private static boolean less(Comparable c1, Comparable c2){
		return c1.compareTo(c2)<0;
	}
	
	public static  void show(Comparable[] a){
		for(int i=0; i<a.length; i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	//check if the list is sorted (from samll to big)
	public static  boolean isSorted(Comparable[] a){
		for(int i=1; i<a.length; i++){
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}

}
