package com.loic.algo.AStart;

public class Test {

	public static void main(String[] args) {
		int[][] map=new int[][]{// 地图数组
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1}
        };
		AStart aStart = new AStart(map, 6, 10);
		aStart.search(5, 0, 5, 9);
	}

}
