package com.loic.algo;

import java.util.Scanner;

public class UnionFindTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		UnionFind uf = new UnionFind(N);
		while (true) {
			int p = in.nextInt();
			if (p == -1)
				break;
			int q = in.nextInt();
			uf.union(p, q);
		}
		System.out.println(uf.getCount() + " components");
		in.close();
	}

}
