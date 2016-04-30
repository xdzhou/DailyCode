package com.loic.algo.AStar;

import org.testng.annotations.Test;

public class AStarAlgoTest {
    @Test
    public void test() {
        final String[] maps = new String[] {
                "##############################",
                "#T...........................#",
                "##...........................#",
                "#............................#",
                "?............................#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????....#",
                "?????????????????????????...C#",
                "??????????????????????????####"
        };
        AStarAlgo.IMapInfo mapInfo = new AStarAlgo.IMapInfo() {
            @Override
            public int getHeight() {
                return maps.length;
            }

            @Override
            public int getWidth() {
                return maps[0].length();
            }

            @Override
            public boolean reachable(int x, int y) {
                if (x >= 0 && x < 15 && y >= 0 && y < 30){
                    char c = maps[x].charAt(y);
                    switch (c) {
                        case '#':
                            return false;
                        case '?':
                            return false;
                        default:
                            return true;
                    }
                }
                return false;
            }

            @Override
            public int getEstimateDis(int x1, int y1, int x2, int y2) {
                return Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }
        };

        AStarAlgo algo = new AStarAlgo(mapInfo);
        algo.search(13, 28, 1, 1);
        algo.search(0, 0, new AStarAlgo.IResultChecker() {
            @Override
            public boolean isResult(int x, int y) {
                return maps[x].charAt(y) == '?';
            }
        });
    }
}
