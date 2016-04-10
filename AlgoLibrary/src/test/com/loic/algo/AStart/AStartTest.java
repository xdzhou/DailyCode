package com.loic.algo.AStart;

import org.testng.annotations.Test;

public class AStartTest {
	@Test
	public void test() {
		int[][] map = new int[][] { // 地图数组
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 }, { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1 } };
		AStart aStart = new AStart(map, 6, 10);
		aStart.search(5, 0, 5, 9);
	}

}
