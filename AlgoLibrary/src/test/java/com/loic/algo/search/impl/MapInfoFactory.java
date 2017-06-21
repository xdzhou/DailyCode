package com.loic.algo.search.impl;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class MapInfoFactory {

    public static MapInfo create(String... data) {
        int width = -1;
        int height = 0;
        int startPos = -1;
        int endPos = -1;
        boolean[] passFlag = new boolean[data.length * data[0].length()];
        Arrays.fill(passFlag, true);
        for (String lineData : data) {
            if (width > 0) {
                Preconditions.checkState(width == lineData.length(), "Map Data have different width");
            } else {
                width = lineData.length();
            }
            for (int i = 0; i < lineData.length(); i++) {
                char c = lineData.charAt(i);
                switch (c) {
                    case 'S':
                        startPos = width * height + i;
                        break;
                    case 'E':
                        endPos = width * height + i;
                        break;
                    case '#':
                        passFlag[width * height + i] = false;
                        break;
                }
            }

            height++;
        }
        return new MapInfoImpl(width, height, passFlag, endPos);
    }

    private static final class MapInfoImpl implements MapInfo {
        private int width, height;
        private boolean[] passFlag;
        private int endX, endY;
        private int minDis;

        private MapInfoImpl(int width, int height, boolean[] passFlag, int endPos) {
            this.width = width;
            this.height = height;
            this.passFlag = passFlag;
            endX = endPos / width;
            endY = endPos % width;
        }

        @Override
        public void setStartPosition(int pos) {
            minDis = Math.abs(endX - (pos / width)) + Math.abs(endY - (pos % width));
        }

        @Override
        public int getEndPos() {
            return endX * width + endY;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public boolean reachable(int posX, int posY) {
            if (posX < 0 || posY < 0) {
                return false;
            }
            if (posX >= height || posY >= width) {
                return false;
            }
            int pos = posX * width + posY;
            if (!passFlag[pos]) {
                return false;
            }
            return true;
        }

        @Override
        public double heuristicToEnd(int lengthPassed, int posX, int posY) {
            if (posX == endX && posY == endY) {
                return 1;
            }
            return minDis / (double) (lengthPassed + Math.abs(posX - endX) + Math.abs(posY - endY));
        }
    }

}
