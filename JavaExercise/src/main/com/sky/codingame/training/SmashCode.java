package com.sky.codingame.training;

import com.loic.algo.search.MonteCarlo;

import java.util.*;

public class SmashCode {
    private static final int HEIGHT = 12;
    private static final int WIDTH = 6;
    private static final int MAX_DEPTH = 8;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        SmashCode algo = new SmashCode();

        ColorSet[] colors = new ColorSet[MAX_DEPTH];
        String[] lines = new String[HEIGHT];

        Node root = new Node();
        root.mMapInfo = new MapInfo();
        int round = 0;
        // game loop
        while (true) {
            for (int i = 0; i < 8; i++) {
                int colorA = in.nextInt(); // color of the first block
                int colorB = in.nextInt(); // color of the attached block
                colors[i] = new ColorSet((char)('0'+colorA), (char)('0'+colorB));
            }
            int score1 = in.nextInt();
            for (int i = 0; i < 12; i++) {
                String row = in.next();
                lines[i] = row;
            }
            int score2 = in.nextInt();
            for (int i = 0; i < 12; i++) {
                String row = in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
            }
            root.mMapInfo.buildData(lines);
            algo.setData(root, colors);

            System.out.println(algo.getCol(round)); // "x": the column in which to drop your blocks
            round++;
        }
    }

    private static final float C = (float) Math.sqrt(2);
    private Random mRandom = new Random(new Date().getTime());
    private Node mRoot;
    private ColorSet[] mColors;

    public void setData(Node root, ColorSet[] colors) {
        root.reset();
        mRoot = root;
        mColors = colors;
    }

    public String getCol(int turnNb) {
        if (turnNb < 4) {
            return simpleCase();
        } else {
            for(int i = 0; i < 3000; i++) {
                process();
            }
            Node bestChild = getBestChild(mRoot);
            int col = (bestChild.mRotation == 2) ? bestChild.mStep + 1 : bestChild.mStep;
            return col + " " + bestChild.mRotation;
        }
    }

    private String simpleCase() {
        int fireTop = mRoot.mMapInfo.getEmptyLine(0);
        int lastTop = mRoot.mMapInfo.getEmptyLine(5);
        int lowCol = (fireTop < lastTop) ? 5 : 0;
        int lowTop = (fireTop < lastTop) ? lastTop : fireTop;
        if (lowTop + 1 < HEIGHT) {
            char c = mRoot.mMapInfo.getChar(lowTop + 1, lowCol);
            if (c != mColors[0].c1) {
                return lowCol + " 1";
            } else if (c != mColors[0].c2) {
                return lowCol + " 3";
            } else {
                int highCol = (fireTop < lastTop) ? 0 : 5;
                int highTop = (fireTop < lastTop) ? fireTop : lastTop;
                char highChar = mRoot.mMapInfo.getChar(highTop + 1, highCol);
                if (highChar != mColors[0].c1) {
                    return highCol + " 1";
                } else if (highChar != mColors[0].c2) {
                    return highCol + " 3";
                } else {
                    return lowCol + " 0";
                }
            }
        } else {
            return lowCol + " 1";
        }
    }

    private void process() {
        List<Node> path = selection();
        //System.err.println("get Path len : "+path.size());
        Node leafNode = path.get(path.size() - 1);
        Node selectedNode = null;
        if (leafNode != mRoot && leafNode.simulationCount == 0 ) {
            selectedNode = leafNode;
            path.remove(path.size() - 1);
        } else {
            selectedNode = expansion(leafNode, path.size() - 1);
        }
        //System.err.println("select Node : "+(selectedNode == null ? "NULL" : selectedNode.print(true)));
        float winning;
        if (selectedNode == null) {
            winning = leafNode.heuristic();
            //System.err.println("heuristic winning : " + winning);
        } else {
            winning = simulation(selectedNode, path.size());
            //System.err.println("simulation winning : " + winning);
        }
        backPropagation(winning, path);
        //System.err.println("after process : "+mRoot.print(false));
    }

    private List<Node> selection() {
        List<Node> path = new ArrayList<Node>();
        Node curNode = mRoot;
        while (curNode.children != null && ! curNode.children.isEmpty()) {
            path.add(curNode);
            curNode = getBestChild(curNode);
        }
        path.add(curNode);
        return path;
    }

    private Node getBestChild(Node parent) {
        double best = Double.NEGATIVE_INFINITY;
        Node bestChild = null;
        for(Node child : parent.children) {
            if (child.simulationCount == 0) {
                bestChild = child;
                break;
            } else {
                double value = (child.mWin / (float) child.simulationCount + C * Math.sqrt(Math.log(parent.simulationCount) / (float) child.simulationCount));
                if (value > best || (value == best && mRandom.nextBoolean())) {
                    best = value;
                    bestChild = child;
                }
            }
        }
        return bestChild;
    }

    private Node expansion(Node leafNode, int leafDepth) {
        if (leafNode.mMapInfo.isOver() || leafDepth >= MAX_DEPTH) {
            return null;
        } else {
            leafNode.children = new ArrayList<Node>(22);
            int[] rotations = new int[] {0,1,2,3};
            for (int rotation : rotations) {
                int size = (rotation % 2 == 0) ? WIDTH - 1 : WIDTH;
                for(int col = 0; col < size; col ++) {
                    Node child = new Node();
                    child.mStep = col;
                    child.mRotation = rotation;
                    child.mMapInfo = leafNode.mMapInfo.clone();
                    child.mScore += child.mMapInfo.drop(col, rotation, mColors[leafDepth]);
                    leafNode.children.add(child);
                }
            }

            if (leafNode != mRoot) leafNode.mMapInfo = null;
            //
            int childIndex = mRandom.nextInt(leafNode.children.size());
            //System.err.println("expansion node "+leafNode.print(false)+", depth "+leafDepth+", choice child "+childIndex);
            return leafNode.children.get(childIndex);
        }
    }

    private float simulation(Node node, int depth) {
        float winning;
        if (depth >= MAX_DEPTH) {
            winning = node.heuristic();
        } else {
            //System.err.println("simulation in depth "+depth+" for Node "+node.print(false));
            MapInfo mapInfo = node.mMapInfo.clone();
            int score = node.mScore;
            for (int i = depth + 1; i <= MAX_DEPTH && !mapInfo.isOver(); i++) {
                int rotation = mRandom.nextInt(4);
                int dropCol = mRandom.nextInt((rotation % 2 == 0) ? WIDTH - 1 : WIDTH);
                score += mapInfo.drop(dropCol, rotation, mColors[i - 1]);
            }
            winning = scoreToWinning(score);
        }
        node.applyWinning(winning);
        return winning;
    }

    private void backPropagation(float winning, List<Node> path) {
        for(Node n : path) {
            n.applyWinning(winning);
        }
    }

    private static float scoreToWinning(int score) {
        float max = 500f;
        float s = Math.abs(Math.max(max * 2, score) - max);

        return 1 - s / max;
    }

    private static class Node {
        private float mWin = 0f;
        private int simulationCount = 0;
        private List<Node> children;

        private int mStep; //0,1...5 col
        private int mRotation; //0,1,2,3
        private int mScore = 0;
        private MapInfo mMapInfo;

        public void applyWinning(float winning) {
            simulationCount ++;
            mWin += winning;
        }

        public void reset() {
            mWin = 0f;
            simulationCount = 0;
            mScore = 0;
            children = null;
        }

        public float heuristic() {
            if (mMapInfo.isOver || mScore <= 0) return 0;
            else return scoreToWinning(mScore);
        }

        public String print(boolean withMap) {
            String title = mWin+"/"+simulationCount+"\n";
            if (withMap) title += mMapInfo + "\n";
            if (children != null) {
                for(Node c : children) {
                    title += c.mWin+"/"+c.simulationCount+"\n";
                }
            }
            return title.substring(0, title.length());
        }
    }

    private static class MapInfo implements Cloneable{
        private static final int MIN_LIEN = 4;

        private static final char EMPTY = '.';
        private static final char BLOCK = '0';

        private char[][] data = new char[HEIGHT][WIDTH];
        private boolean isOver = false;

        private int buildData(String[] lines) {
            int size = 0;
            for(int line = 0; line < HEIGHT; line ++)
                for(int column = 0; column < WIDTH; column ++) {
                    data[line][column] = lines[line].charAt(column);
                    if (data[line][column] != '.') size ++;
                }
            return size;
        }

        public int getEmptyLine(int col) {
            int lastLine = HEIGHT - 1;
            while (lastLine >= 0 && data[lastLine][col] != EMPTY) {
                lastLine--;
            }
            return lastLine;
        }

        public char getChar(int line, int col) {
            return data[line][col];
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
                return 0;
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
            if (! colorSet.sameColor()) {
                z2 = getZoneFor(lastLine - 1, col);
                if (z2 != null && z2.size() < MIN_LIEN) z2 = null;
            }
            if (z1 != null || z2 != null) {
                return disappearZone(0, z1, z2);
            } else {
                return 0;
            }
        }

        private int disappearZone(int chainIndex, Zone ... zones) {
            int blockClearedNb = 0, colorBonus = 0, groupBonus = 0;
            Set<Integer> colorClearedSet = new HashSet<Integer>(5);
            //disappear and fill EMPTY char
            for (Zone z : zones) {
                if (z == null) continue;
                blockClearedNb += z.size();
                colorClearedSet.add(z.mColor - '0');
                groupBonus += (z.size() <= 10 ? z.size() - 4 : 8);
                for(int index : z.mIndexs) {
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
                int line  = getEmptyLine(column);
                if (line >= 1) {
                    int topLine = line - 1;
                    while (topLine >= 0) {
                        if (data[topLine][column] != EMPTY) {
                            data[line][column] = data[topLine][column];
                            data[topLine][column] = EMPTY;
                            line --;
                        }
                        topLine --;
                    }
                }
            }
            //find checkIndex
            List<Integer> indexToCheck = new ArrayList<Integer>();
            for(int line = 0; line < HEIGHT; line ++)
                for(int column = 0; column < WIDTH; column ++) {
                    if (data[line][column] != EMPTY && data[line][column] != BLOCK) {
                        indexToCheck.add(getIndex(line, column));
                    }
                }
            //check chain
            List<Zone> zoneToDisappear = new ArrayList<Zone>();
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
            if (! zoneToDisappear.isEmpty()) {
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
        protected MapInfo clone(){
            try {
                MapInfo m = (MapInfo) super.clone();
                m.data = new char[HEIGHT][WIDTH];
                for(int line = 0; line < HEIGHT; line ++)
                    for(int column = 0; column < WIDTH; column ++) {
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
            String s = "MAP : \n";
            for (int i = 0; i < HEIGHT; i++) {
                s += Arrays.toString(data[i]) + "\n";
            }
            return s;
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
            if (mIndexs == null) mIndexs = new ArrayList<Integer>();
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

    private static class Transition {
        private int mCol;
        private int mRotation;
    }
    private static class MapNode extends MonteCarlo.MonteCarloNode<Transition> {
        private char[][] data = new char[HEIGHT][WIDTH];
        private boolean isOver = false;

        @Override
        public float heuristic() {
            return 0;
        }

        @Override
        public com.loic.algo.search.Node<Transition> applyTransition(Transition transition) {
            return null;
        }

        @Override
        public boolean isOver() {
            return false;
        }

        @Override
        public List<Transition> getPossibleTransitions() {
            return null;
        }
    }
}