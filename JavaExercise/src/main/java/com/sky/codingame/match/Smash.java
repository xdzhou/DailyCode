package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Smash {
    private static final int HEIGHT = 12;
    private static final int WIDTH = 6;
    private static final int NEXT_LEN = 6;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        GeneticAlgo algo = new GeneticAlgo();
        int[] colors = new int[8];

        // game loop
        while (true) {
            for (int i = 0; i < 8; i++) {
                int colorA = in.nextInt(); // color of the first block
                int colorB = in.nextInt(); // color of the attached block
                colors[i] = colorA;
            }
            for (int i = 0; i < 12; i++) {
                String row = in.next();
                algo.mMapInfo.initLine(i, row);
            }
            for (int i = 0; i < 12; i++) {
                String row = in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
            }

            System.out.println(algo.start(colors)); // "x": the column in which to drop your blocks
        }
    }

    private static class GeneticAlgo {
        private static final int POPULATION = 16;
        private static final int GENERATION = 100;
        private static final float PV = 1 / 10f;
        private static final Comparator<Gene> GENE_COMPARATOR = (o1, o2) -> o1.mFitness - o2.mFitness;
        private List<Gene> mGenes = new ArrayList<>(POPULATION);
        private Random mRandom = new Random(new Date().getTime());
        private MapInfo mMapInfo = new MapInfo();

        public int start(int[] nextColors) {
            mGenes.clear();
            int fitnessSomme = 0;
            while (mGenes.size() < POPULATION) {
                Gene g = Gene.create(mRandom);
                computeFitness(g, nextColors);
                if (g.mFitness >= 0) {
                    mGenes.add(g);
                    fitnessSomme += g.mFitness;
                }
            }
            if (fitnessSomme < 8) return start(nextColors[0]);
            List<Gene> childrenGenes = new ArrayList<>(POPULATION);

            for (int i = 0; i < GENERATION; i++) {
                if (fitnessSomme < 8) break;
                childrenGenes.clear();
                while (childrenGenes.size() < POPULATION - 1) {
                    getChild(getOneGene(fitnessSomme), getOneGene(fitnessSomme), childrenGenes);
                }
                while (childrenGenes.size() > POPULATION - 1) {
                    childrenGenes.remove(0);
                }
                //get best in parent
                Collections.sort(mGenes, GENE_COMPARATOR);
                Gene bestGene = mGenes.get(POPULATION - 1);
                if (bestGene.mFitness >= 8 && i > 10) {
                    System.err.println("We break in generation : " + i + ", with fitness : " + bestGene.mFitness);
                    break;
                }
                childrenGenes.add(mGenes.get(POPULATION - 1));
                //
                fitnessSomme = 0;
                for (int index = 0; index < childrenGenes.size() - 1; index++) {
                    Gene child = childrenGenes.get(index);
                    if (mRandom.nextFloat() < PV) {
                        child.set(mRandom.nextInt(NEXT_LEN), mRandom.nextInt(WIDTH - 1) + 1);
                    }
                    computeFitness(child, nextColors);
                    fitnessSomme += child.mFitness;
                }
                mGenes.clear();
                mGenes.addAll(childrenGenes);
            }
            Collections.sort(mGenes, GENE_COMPARATOR);
            return mGenes.get(POPULATION - 1).mChromosome[0];
        }

        private int start(int color) {
            int retVal = 0;
            int maxWeight = Integer.MIN_VALUE;
            for (int i = 0; i < WIDTH; i++) {
                mMapInfo.resetData();
                int weight = mMapInfo.drop(i, color);
                if (weight > maxWeight) {
                    maxWeight = weight;
                    retVal = i;
                }
            }
            return retVal;
        }

        private void getChild(Gene parent1, Gene parent2, List<Gene> childrenGenes) {
            if (parent1 != null && parent2 != null) {
                try {
                    Gene child1 = parent1.clone();
                    Gene child2 = parent2.clone();
                    int pos = mRandom.nextInt(NEXT_LEN - 1);
                    for (int index = pos + 1; index < NEXT_LEN; index++) {
                        int temp = child1.mChromosome[index];
                        child1.mChromosome[index] = child2.mChromosome[index];
                        child2.mChromosome[index] = temp;
                    }
                    childrenGenes.add(child1);
                    childrenGenes.add(child2);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }

        private Gene getOneGene(int fitnessSomme) {
            float taux = mRandom.nextFloat();
            float temp = 0;
            for (int i = 0; i < POPULATION; i++) {
                Gene gene = mGenes.get(i);
                temp += (gene.mFitness / (float) fitnessSomme);
                if (temp >= taux) return gene;
            }
            System.err.println("Oops, getOneGene error..." + taux + ", temp : " + temp);
            return null;
        }

        private void computeFitness(Gene g, int[] nextColors) {
            mMapInfo.resetData();
            g.mFitness = 0;
            int[] chromosome = g.mChromosome;
            for (int i = 0; i < NEXT_LEN; i++) {
                int weight = mMapInfo.drop(chromosome[i], nextColors[i]);
                if (weight < 0) System.err.println("Fitness < 0 : " + Arrays.toString(chromosome));
                g.mFitness += weight;
            }
        }

        private static class Gene implements Cloneable {
            private int[] mChromosome = new int[NEXT_LEN];
            private int mFitness;

            public static Gene create(Random random) {
                Gene retVal = new Gene();
                for (int i = 0; i < NEXT_LEN; i++) {
                    retVal.mChromosome[i] = random.nextInt(WIDTH);
                }
                return retVal;
            }

            public void set(int index, int value) {
                mChromosome[index] = value;
            }

            @Override
            protected Gene clone() throws CloneNotSupportedException {
                Gene g = (Gene) super.clone();
                for (int i = 0; i < NEXT_LEN; i++) {
                    g.mChromosome[i] = mChromosome[i];
                }
                return g;
            }
        }
    }

    private static class MapInfo {
        private static final int MIN_LIEN = 4;

        private static final char EMPTY = '.';
        private static final char BLOCK = '0';
        private final char[][] data = new char[HEIGHT][WIDTH];
        private String[] lines = new String[HEIGHT];

        public void initLine(int line, String row) {
            lines[line] = row;
        }

        private int resetData() {
            int size = 0;
            for (int line = 0; line < HEIGHT; line++)
                for (int column = 0; column < WIDTH; column++) {
                    data[line][column] = lines[line].charAt(column);
                    if (data[line][column] != '.') size++;
                }
            return size;
        }

        private int drop(int col, int color) {
            char colorChar = (char) ('0' + color);
            int lastLine = HEIGHT - 1;
            while (lastLine >= 0 && data[lastLine][col] != '.') {
                lastLine--;
            }
            if (lastLine < 0 || lastLine - 1 < 0) {
                System.err.println("Can't put block in col " + col);
                return Integer.MIN_VALUE;
            }
            data[lastLine][col] = colorChar;
            data[lastLine - 1][col] = colorChar;
            Zone zone = getZoneFor(lastLine, col);
            if (zone != null && zone.size() >= MIN_LIEN) {
                int[] topBound = new int[WIDTH];
                int[] bottomBound = new int[WIDTH];
                return disappearZone(topBound, bottomBound, zone);
            } else {
                return 0;
            }
        }

        private int disappearZone(int[] topBound, int[] bottomBound, Zone... zones) {
            int retVal = 0;
            Arrays.fill(topBound, -1);
            Arrays.fill(bottomBound, -1);
            for (Zone z : zones) {
                retVal += z.size();
                for (int index : z.mIndexs) {
                    int x = index / WIDTH;
                    int y = index % WIDTH;
                    if (topBound[y] == -1) {
                        topBound[y] = x;
                        bottomBound[y] = x;
                    } else {
                        if (x < topBound[y]) topBound[y] = x;
                        if (x > bottomBound[y]) bottomBound[y] = x;
                    }
                }
            }

            //disappear
            List<Integer> indexToCheck = new ArrayList<>();
            for (int column = 0; column < WIDTH; column++) {
                if (topBound[column] != -1) {
                    int len = bottomBound[column] - topBound[column] + 1;
                    for (int line = bottomBound[column]; line >= 0; line--) {
                        data[line][column] = line - len >= 0 ? data[line - len][column] : EMPTY;
                        if (data[line][column] != EMPTY && data[line][column] != BLOCK) {
                            indexToCheck.add(getIndex(line, column));
                        }
                    }
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
            if (!zoneToDisappear.isEmpty()) {
                retVal += disappearZone(topBound, bottomBound, zoneToDisappear.toArray(new Zone[zoneToDisappear.size()]));
            }
            return retVal;
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
        protected MapInfo clone() throws CloneNotSupportedException {
            return (MapInfo) super.clone();
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
            if (mIndexs == null) mIndexs = new ArrayList<>();
            mIndexs.add(index);
            mBlockCount++;
        }

        public boolean containIndex(int index) {
            return mIndexs != null && mIndexs.contains(index);
        }
    }
}