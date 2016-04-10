package com.sky.codejam.training;

import java.util.Scanner;

public class ReverseWords {

	public static void main(String[] args) {
		new ReverseWords().start();
	}

	public void start() {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		in.nextLine();
		for (int i = 0; i < N; i++) {
			String s = in.nextLine();
			// System.out.println(s);
			String[] items = s.split(" ");
			StringBuffer sb = new StringBuffer();
			for (int j = items.length - 1; j >= 0; j--) {
				sb.append(items[j] + " ");
			}

			System.out.println("Case #" + (i + 1) + ": " + sb.toString());
		}
		in.close();
	}

}
