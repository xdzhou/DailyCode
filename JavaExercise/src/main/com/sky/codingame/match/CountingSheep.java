package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingSheep {

	public static void main(String[] args) {
		new CountingSheep().start();
	}

	private void start() {
		Scanner in = new Scanner(System.in);
		int count = in.nextInt();
		in.nextLine();
		for (int i = 0; i < count; i++) {
			treat(in.nextLine(), i);
		}
		in.close();
	}

	private void treat(String num, int index) {
		if (Integer.parseInt(num) == 0) {
			System.out.println("Case #" + (index + 1) + ": INSOMNIA");
		} else {
			int[] chiffres = new int[num.length()];
			for (int i = 0; i < chiffres.length; i++) {
				chiffres[i] = num.charAt(chiffres.length - 1 - i) - '0';
			}
			BigInt bigInt = new BigInt();
			while (bigInt.changeCount < 10)
				bigInt.add(chiffres, 0);
			System.out.println("Case #" + (index + 1) + ": " + bigInt);
		}
	}

	private class BigInt {
		boolean[] flags = new boolean[10];
		int changeCount = 0;

		private List<Integer> nums = new ArrayList<>();

		public void add(int[] chiffres, int startIndex) {
			int delta = 0;
			for (int i = 0; i < chiffres.length; i++) {
				int sum = delta + chiffres[i] + getInt(i + startIndex);
				delta = (sum >= 10) ? 1 : 0;
				sum %= 10;
				if (i + startIndex < nums.size())
					nums.set(i + startIndex, sum);
				else
					nums.add(i + startIndex, sum);
				if (!flags[sum]) {
					flags[sum] = true;
					changeCount++;
				}
			}
			if (delta > 0) {
				int[] deltas = { delta };
				add(deltas, chiffres.length + startIndex);
			}
		}

		private int getInt(int index) {
			if (index < nums.size())
				return nums.get(index);
			else
				return 0;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < nums.size(); i++) {
				sb.insert(0, Integer.toString(nums.get(i)));
			}
			return sb.toString();
		}
	}
}
