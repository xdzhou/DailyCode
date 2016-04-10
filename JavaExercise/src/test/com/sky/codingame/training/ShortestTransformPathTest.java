package com.sky.codingame.training;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class ShortestTransformPathTest extends CommonTest<Void, Integer> {

	private void chargeData(String fileIn) {
		ShortestTransformPath<Integer> algo = (ShortestTransformPath<Integer>) getProblem();
		String floder = "src/resources/com/sky/codingame/training";
		try {
			algo.clear();
			Scanner in = new Scanner(new File(floder + File.separator + fileIn));
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				int xi = in.nextInt();
				int yi = in.nextInt();
				algo.addNewLien(xi, yi);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		chargeData("ShortestTransformPathIn1.txt");
		check(null, 2);
	}

	@Test
	public void test2() {
		chargeData("ShortestTransformPathIn2.txt");
		check(null, 2);
	}

	@Test
	public void test3() {
		chargeData("ShortestTransformPathIn3.txt");
		check(null, 3);
	}

	@Test
	public void test4() {
		chargeData("ShortestTransformPathIn4.txt");
		check(null, 5);
	}

	@Test
	public void test5() {
		chargeData("ShortestTransformPathIn5.txt");
		check(null, 5);
	}

	@Test
	public void test6() {
		chargeData("ShortestTransformPathIn6.txt");
		check(null, 7);
	}

	@Test
	public void test7() {
		chargeData("ShortestTransformPathIn7.txt");
		check(null, 15);
	}

	@Test
	public void test8() {
		chargeData("ShortestTransformPathIn8.txt");
		check(null, 9);
	}

	@Test
	public void test9() {
		chargeData("ShortestTransformPathIn9.txt");
		check(null, 15);
	}

	@Test
	public void test10() {
		chargeData("ShortestTransformPathIn10.txt");
		check(null, 5);
	}

	@Override
	public Problem<Void, Integer> getAlgo() {
		return new ShortestTransformPath<Integer>();
	}

}
