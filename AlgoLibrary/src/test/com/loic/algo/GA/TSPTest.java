package com.loic.algo.GA;

public class TSPTest {
	public static void main(String[] args) {
		int[] x = { 0, 1, 2, 2, 2, 1, 0, 0 };
		int[] y = { 0, 0, 0, 1, 2, 2, 2, 1 };
		TSP tsp = new TSP();
		tsp.start(x, y);
	}

}
