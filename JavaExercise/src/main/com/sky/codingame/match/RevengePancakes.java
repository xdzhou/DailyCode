package com.sky.codingame.match;

import java.util.Scanner;

public class RevengePancakes {

	public static void main(String[] args) {
		new RevengePancakes().start();
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

	private void treat(String cakeList, int index) {
		int nbChanges = 0;
		char curSizd = cakeList.charAt(0);
		for (int i = 1; i < cakeList.length(); i++) {
			if (cakeList.charAt(i) != curSizd) {
				curSizd = cakeList.charAt(i);
				nbChanges++;
			}
		}
		if (cakeList.charAt(cakeList.length() - 1) == '-')
			nbChanges++;
		System.out.println("Case #" + (index + 1) + ": " + nbChanges);
	}
}
