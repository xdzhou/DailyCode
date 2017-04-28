package com.sky.codingame.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.loic.algo.search.GeneticAlgorithm;

public class SmashCode {
    private static final int HEIGHT = 12;
    private static final int WIDTH = 6;
    private static final int MAX_DEPTH = 8;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        final ColorSet[] colors = new ColorSet[MAX_DEPTH];
        GameBoard myBoard = new GameBoard();
        GameBoard oppoBoard = new GameBoard();
        myBoard.setOppenent(oppoBoard);

        GAListener myListener = new GAListener(myBoard) {
            @Override
            public float computerFitness(ColorGene gene) {
                Integer[] data = gene.mData;
                MapInfo clone = mBoard.mMapInfo.clone();
                float score = 0;
                float rate = 1f;
                float param = mValidPosition <= 3 ? 0.01f : getParam();
                for (int i = 0; i < 8; i++) {
                    int addScore = clone.drop(data[i * 2], data[i * 2 + 1], colors[i]);
                    if (i <= mValidPosition) {
                        score += addScore;
                    } else {
                        rate *= param;
                        score += addScore * rate;
                    }
                    if (clone.isOver()) break;
                }
                return score;
            }

            private float getParam() {
                int emptyCount = mBoard.mMapInfo.getEmptyCount();
                return emptyCount / (float) (WIDTH * HEIGHT);
            }
        };
        GeneticAlgorithm<ColorGene> myAlgo = new GeneticAlgorithm<>(myListener);

        GAListener otherListener = new GAListener(oppoBoard) {
            @Override
            public float computerFitness(ColorGene gene) {
                Integer[] data = gene.mData;
                MapInfo clone = mBoard.mMapInfo.clone();
                float score = 0;
                float deltaScore = (18 - mBoard.mOpponentBoard.mNuisancePoints) * GameBoard.DIVIDOR;
                for (int i = 0; i < 8; i++) {
                    score += clone.drop(data[i * 2], data[i * 2 + 1], colors[i]);
                    if (clone.isOver()) return 0;
                    else if (score > deltaScore) return 8 - i;
                }
                return 0;
            }
        };
        GeneticAlgorithm<ColorGene> otherAlgo = new GeneticAlgorithm<>(otherListener);

        // game loop
        while (true) {
            for (int i = 0; i < 8; i++) {
                int colorA = in.nextInt(); // color of the first block
                int colorB = in.nextInt(); // color of the attached block
                colors[i] = new ColorSet((char) ('0' + colorA), (char) ('0' + colorB));
            }
            myBoard.setNewScore(in.nextInt());
            for (int i = 0; i < 12; i++) {
                String row = in.next();
                myBoard.mMapInfo.setLine(row, i);
            }
            oppoBoard.setNewScore(in.nextInt());
            for (int i = 0; i < 12; i++) {
                String row = in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
                oppoBoard.mMapInfo.setLine(row, i);
            }
            System.err.println(myBoard);
            System.err.println(oppoBoard);
            int validStep = 8 - (int) otherAlgo.execute(50, 1000).getFitness();
            validStep = Math.min(validStep, 7);
            float deltaScore = (6 - myBoard.mNuisancePoints) * GameBoard.DIVIDOR;
            float emptyRate = myBoard.mMapInfo.getEmptyCount() / (float) (WIDTH * HEIGHT);
            System.err.println("validStep:" + validStep + ", deltaScore:" + deltaScore + ", emptyRate:" + emptyRate);
            myListener.mValidPosition = validStep;
            ColorGene bestGene = myAlgo.execute(50, 1000);
            System.err.println("fitness : " + bestGene.getFitness());
            Integer[] data = bestGene.mData;
            int col = data[1] == 2 ? data[0] + 1 : data[0];
            System.out.println(col + " " + data[1]); // "x": the column in which to drop your blocks
        }
    }

    private abstract static class GAListener implements GeneticAlgorithm.IGAListener<ColorGene> {
        protected final GameBoard mBoard;
        protected int mValidPosition = 7;

        public GAListener(GameBoard gameBoard) {
            mBoard = gameBoard;
        }

        @Override
        public ColorGene createNewGene(Random random) {
            return ColorGene.getOneGene(random);
        }

        @Override
        public ColorGene getChild(Random random, ColorGene parent1, ColorGene parent2) {
            int changIndex = random.nextInt(parent1.mData.length - 1);
            ColorGene child = new ColorGene(new Integer[ColorGene.LEN]);
            for (int i = 0; i < parent1.mData.length; i++) {
                child.mData[i] = (i <= changIndex) ? parent1.mData[i] : parent2.mData[i];
            }
            if (changIndex % 2 == 0 && child.mData[changIndex] == 5 && child.mData[changIndex + 1] % 2 == 0) {
                child.mData[changIndex] = random.nextInt(5);
            }
            return child;
        }
    }

    private static class ColorGene extends GeneticAlgorithm.Gene<Integer> {
        public static final int LEN = 16;

        private ColorGene(Integer[] data) {
            super(data);
        }

        public static ColorGene getOneGene(Random random) {
            Integer[] data = new Integer[LEN];
            for (int i = 0; i < 8; i++) {
                int rotate = random.nextInt(4);
                int col = (rotate == 0 || rotate == 2) ? random.nextInt(5) : random.nextInt(6);
                data[i * 2] = col;
                data[i * 2 + 1] = rotate;
            }
            //System.err.println("score : " + Arrays.toString(data));
            return new ColorGene(data);
        }

        @Override
        protected void mutation(Random random) {
            int index = random.nextInt(LEN);
            if (index % 2 == 0) {
                mData[index] = (mData[index + 1] % 2 == 0) ? random.nextInt(5) : random.nextInt(6);
            } else {
                mData[index] = random.nextInt(4);
                if (mData[index] % 2 == 0 && mData[index - 1] == 5) {
                    mData[index - 1] = random.nextInt(5);
                }
            }
        }
    }

    private static class GameBoard {
        private static final float DIVIDOR = 70.0f;
        private int mCurScore;
        private float mNuisancePoints;
        private MapInfo mMapInfo = new MapInfo();

        private GameBoard mOpponentBoard;

        public void setOppenent(GameBoard oppo) {
            mOpponentBoard = oppo;
            oppo.mOpponentBoard = this;
        }

        public void setNewScore(int score) {
            if (score > mCurScore) {
                float add = (score - mCurScore) / DIVIDOR + mOpponentBoard.mNuisancePoints;
                mOpponentBoard.mNuisancePoints = add % 6f;
            }
            mCurScore = score;
        }

        @Override
        public String toString() {
            return "score " + mCurScore + ", nuisance " + mNuisancePoints;
        }
    }

    private static class MapInfo implements Cloneable {
        private static final int MIN_LIEN = 4;

        private static final char EMPTY = '.';
        private static final char BLOCK = '0';

        private char[][] data = new char[HEIGHT][WIDTH];
        private boolean isOver = false;

        private void setLine(String s, int line) {
            for (int column = 0; column < WIDTH; column++) {
                data[line][column] = s.charAt(column);
            }
        }

        public int getEmptyCount() {
            int count = 0;
            for (int line = 0; line < HEIGHT; line++) {
                for (int column = 0; column < WIDTH; column++) {
                    if (data[line][column] == EMPTY) count++;
                }
            }
            return count;
        }

        public int getEmptyLine(int col) {
            int lastLine = HEIGHT - 1;
            while (lastLine >= 0 && data[lastLine][col] != EMPTY) {
                lastLine--;
            }
            return lastLine;
        }

        public boolean isOver() {
            return isOver;
        }

        //return score
        private int drop(int col, int rotation, ColorSet colorSet) {
            if (isOver) return 0;
            if (rotation == 1 || rotation == 3) {
                ColorSet cs = (rotation == 1 && !colorSet.sameColor()) ? colorSet.exchange() : colorSet;
                return verticalDrop(col, cs);
            } else {
                ColorSet cs = (rotation == 2 && !colorSet.sameColor()) ? colorSet.exchange() : colorSet;
                return horizontalDrop(col, cs);
            }
        }

        private int horizontalDrop(int col, ColorSet colorSet) {
            int lastLine1 = getEmptyLine(col);
            if (lastLine1 < 0) {
                isOver = true;
                return 0;
            }
            int lastLine2 = getEmptyLine(col + 1);
            if (lastLine2 < 0) {
                isOver = true;
                return 0;
            }
            data[lastLine1][col] = colorSet.c1;
            data[lastLine2][col + 1] = colorSet.c2;
            Zone z1, z2 = null;
            z1 = getZoneFor(lastLine1, col);

            if (z1 == null || !z1.containIndex(lastLine2 * WIDTH + col + 1)) {
                z2 = getZoneFor(lastLine2, col + 1);
                if (z2 != null && z2.size() < MIN_LIEN) z2 = null;
            }
            if (z1 != null && z1.size() < MIN_LIEN) z1 = null;
            if (z1 != null || z2 != null) {
                return disappearZone(0, z1, z2);
            } else {
                return 1;
            }
        }

        private int verticalDrop(int col, ColorSet colorSet) {
            int lastLine = getEmptyLine(col);
            if (lastLine < 0 || lastLine - 1 < 0) {
                isOver = true;
                return 0;
            }
            data[lastLine][col] = colorSet.c2;
            data[lastLine - 1][col] = colorSet.c1;
            Zone z1, z2 = null;
            z1 = getZoneFor(lastLine, col);
            if (z1 != null && z1.size() < MIN_LIEN) z1 = null;
            if (!colorSet.sameColor()) {
                z2 = getZoneFor(lastLine - 1, col);
                if (z2 != null && z2.size() < MIN_LIEN) z2 = null;
            }
            if (z1 != null || z2 != null) {
                return disappearZone(0, z1, z2);
            } else {
                return 1;
            }
        }

        private int disappearZone(int chainIndex, Zone... zones) {
            int blockClearedNb = 0, colorBonus = 0, groupBonus = 0;
            Set<Integer> colorClearedSet = new HashSet<>(5);
            //disappear and fill EMPTY char
            for (Zone z : zones) {
                if (z == null) continue;
                blockClearedNb += z.size();
                colorClearedSet.add(z.mColor - '0');
                groupBonus += (z.size() <= 10 ? z.size() - 4 : 8);
                for (int index : z.mIndexs) {
                    int x = index / WIDTH;
                    int y = index % WIDTH;
                    data[x][y] = EMPTY;
                }
            }
            int colorNb = colorClearedSet.size();
            if (colorNb == 2) colorBonus = 2;
            else if (colorNb == 3) colorBonus = 4;
            else if (colorNb == 4) colorBonus = 8;
            else if (colorNb == 5) colorBonus = 16;

            //reform and find checkIndex
            for (int column = 0; column < WIDTH; column++) {
                int line = getEmptyLine(column);
                if (line >= 1) {
                    int topLine = line - 1;
                    while (topLine >= 0) {
                        if (data[topLine][column] != EMPTY) {
                            data[line][column] = data[topLine][column];
                            data[topLine][column] = EMPTY;
                            line--;
                        }
                        topLine--;
                    }
                }
            }
            //find checkIndex
            List<Integer> indexToCheck = new ArrayList<>();
            for (int line = 0; line < HEIGHT; line++)
                for (int column = 0; column < WIDTH; column++) {
                    if (data[line][column] != EMPTY && data[line][column] != BLOCK) {
                        indexToCheck.add(getIndex(line, column));
                    }
                }
            //check chain
            List<Zone> zoneToDisappear = new ArrayList<>();
            while (!indexToCheck.isEmpty()) {
                int index = indexToCheck.get(0);
                Zone oneZone = getZoneFor(index);
                if (oneZone != null) {
                    if (oneZone.size() >= MIN_LIEN) zoneToDisappear.add(oneZone);
                    indexToCheck.removeAll(oneZone.mIndexs);
                } else {
                    System.err.println("IMPOSSIBLE, no zone");
                }
            }
            int chainPower = chainIndex == 0 ? 0 : 1 << (2 + chainIndex);
            int score = 10 * blockClearedNb * (Math.max(1, Math.min(999, colorBonus + groupBonus + chainPower)));
            //System.err.println("Score "+score+", chainPower "+chainPower+", chainIndex "+chainIndex);
            if (!zoneToDisappear.isEmpty()) {
                score += disappearZone(chainIndex + 1, zoneToDisappear.toArray(new Zone[zoneToDisappear.size()]));
            }
            return score;
        }

        private Zone getZoneFor(int index) {
            int x = index / WIDTH;
            int y = index % WIDTH;
            return getZoneFor(x, y);
        }

        private Zone getZoneFor(int x, int y) {
            if (isValid(x, y) && data[x][y] != EMPTY && data[x][y] != BLOCK) {
                Zone zone = new Zone(data[x][y]);
                search(x, y, zone);
                return zone;
            }
            return null;
        }

        private void search(int x, int y, Zone zone) {
            int index = getIndex(x, y);
            if (isValid(x, y) && !zone.containIndex(index)) {
                if (data[x][y] == zone.mColor) {
                    zone.addIndex(index);
                    search(x + 1, y, zone);
                    search(x - 1, y, zone);
                    search(x, y + 1, zone);
                    search(x, y - 1, zone);
                } else if (data[x][y] == BLOCK) {
                    zone.addBlock(index);
                }
            }
        }

        private boolean isValid(int x, int y) {
            return x >= 0 && x < HEIGHT && y >= 0 && y < WIDTH;
        }

        private int getIndex(int x, int y) {
            return x * WIDTH + y;
        }

        @Override
        protected MapInfo clone() {
            try {
                MapInfo m = (MapInfo) super.clone();
                m.isOver = false;
                m.data = new char[HEIGHT][WIDTH];
                for (int line = 0; line < HEIGHT; line++)
                    for (int column = 0; column < WIDTH; column++) {
                        m.data[line][column] = data[line][column];
                    }
                return m;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("MAP : \n");
            for (int i = 0; i < HEIGHT; i++) {
                sb.append(Arrays.toString(data[i])).append('\n');
            }
            return sb.toString();
        }
    }

    private static class ColorSet {
        private char c1, c2;

        public ColorSet(char c1, char c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        public ColorSet exchange() {
            return new ColorSet(c2, c1);
        }

        public boolean sameColor() {
            return c1 == c2;
        }
    }

    private static class Zone {
        private char mColor;
        private int mBlockCount = 0;
        private List<Integer> mIndexs;

        public Zone(char color) {
            mColor = color;
        }

        public int size() {
            return (mIndexs.size() - mBlockCount >= MapInfo.MIN_LIEN) ? mIndexs.size() : mIndexs.size() - mBlockCount;
        }

        public void addIndex(int index) {
            if (mIndexs == null) mIndexs = new ArrayList<>();
            mIndexs.add(index);
        }

        public void addBlock(int index) {
            addIndex(index);
            mBlockCount++;
        }

        public boolean containIndex(int index) {
            return mIndexs != null && mIndexs.contains(index);
        }
    }
}