package com.loic.algo.sort;

public class SelectSort extends SortParent {

	@Override
	public <T> void sort(Comparable<T>[] a) {
		int N = a.length;
		for(int i=0; i<N; i++){
			int min = i;
			for(int j=i+1; j<N; j++){
				if(a[j].compareTo((T) a[min])<0)
					exch(a, min, j);
			}
		}

	}

}
