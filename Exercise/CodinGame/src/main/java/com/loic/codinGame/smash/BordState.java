package com.loic.codinGame.smash;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BordState implements Cloneable {
    static final int MIN_LIEN = 4;

    static final int HEIGHT = 12;
    private static final int WIDTH = 6;
    private static final char EMPTY = '.';
    private static final char BLOCK = '0';

    private char[][] data = new char[HEIGHT][WIDTH];
    private boolean isOver = false;

    public BordState() {
        for(int i = 0; i < HEIGHT; i ++) {
            Arrays.fill(data[i], EMPTY);
        }
    }

    public void checkLine(String s, int line) {
        for(int column = 0; column < WIDTH; column ++) {
            checkState(data[line][column] == s.charAt(column));
        }
    }

    public double getEmptyCount(double blockRatio) {
        checkState(blockRatio >= 1);
        int emptyCount = 0;
        int blockCount = 0;
        for(int line = 0; line < HEIGHT; line ++) {
            for(int column = 0; column < WIDTH; column ++) {
                switch (data[line][column]) {
                    case EMPTY:
                        emptyCount++;
                        break;
                    case BLOCK:
                        blockCount++;
                        break;
                }
            }
        }
        return emptyCount;
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

    public void dropBlockLine(int line) {
        for (int col = 0; col < WIDTH; col++) {
            int lastLine = getEmptyLine(col);
            int toAdd = line;
            while (toAdd > 0 && lastLine >= 0) {
                data[lastLine][col] = BLOCK;
                toAdd--;
                lastLine--;
            }
            if (toAdd > 0) {
                isOver = true;
            }
        }
    }

    //return score
    public int drop(int col, int rotation, ColorSet colorSet) {
        if (isOver) {
            return 0;
        }
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
        data[lastLine1][col] = colorSet.color1();
        data[lastLine2][col + 1] = colorSet.color2();
        Zone z1, z2 = null;
        z1 = getZoneFor(lastLine1, col);

        if (z1 == null || !z1.containIndex(lastLine2 * WIDTH + col + 1)) {
            z2 = getZoneFor(lastLine2, col + 1);
            if (z2 != null && z2.size() < MIN_LIEN) {
                z2 = null;
            }
        }
        if (z1 != null && z1.size() < MIN_LIEN) {
            z1 = null;
        }
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
        data[lastLine][col] = colorSet.color2();
        data[lastLine - 1][col] = colorSet.color1();
        Zone z1, z2 = null;
        z1 = getZoneFor(lastLine, col);
        if (z1 != null && z1.size() < MIN_LIEN) {
            z1 = null;
        }
        if (! colorSet.sameColor()) {
            z2 = getZoneFor(lastLine - 1, col);
            if (z2 != null && z2.size() < MIN_LIEN) {
                z2 = null;
            }
        }
        if (z1 != null || z2 != null) {
            return disappearZone(0, z1, z2);
        } else {
            return 0;
        }
    }

    private int disappearZone(int chainIndex, Zone... zones) {
        int blockClearedNb = 0, colorBonus = 0, groupBonus = 0;
        Set<Integer> colorClearedSet = new HashSet<>(5);
        //disappear and fill EMPTY char
        for (Zone z : zones) {
            if (z == null) {
                continue;
            }
            blockClearedNb += z.size();
            colorClearedSet.add(z.getColor() - '0');
            groupBonus += (z.size() <= 10 ? z.size() - 4 : 8);
            for(int index : z.getIndexs()) {
                int x = index / WIDTH;
                int y = index % WIDTH;
                data[x][y] = EMPTY;
            }
        }
        int colorNb = colorClearedSet.size();
        if (colorNb == 2) {
            colorBonus = 2;
        } else if (colorNb == 3) {
            colorBonus = 4;
        } else if (colorNb == 4) {
            colorBonus = 8;
        } else if (colorNb == 5) {
            colorBonus = 16;
        }

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
        List<Integer> indexToCheck = new ArrayList<>();
        for (int line = 0; line < HEIGHT; line++) {
            for (int column = 0; column < WIDTH; column++) {
                if (data[line][column] != EMPTY && data[line][column] != BLOCK) {
                    indexToCheck.add(getIndex(line, column));
                }
            }
        }
        //check chain
        List<Zone> zoneToDisappear = new ArrayList<>();
        while (!indexToCheck.isEmpty()) {
            int index = indexToCheck.get(0);
            Zone oneZone = getZoneFor(index);
            if (oneZone != null) {
                if (oneZone.size() >= MIN_LIEN) {
                    zoneToDisappear.add(oneZone);
                }
                indexToCheck.removeAll(oneZone.getIndexs());
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

    public int extraScore() {
        if (isOver) {
            return 0;
        }
        Set<Integer> traitedIndex = new HashSet<>();
        List<Zone> zones = new LinkedList<>();
        for (int line = HEIGHT - 1; line >= 0; line --) {
            for (int col = 0; col < WIDTH; col ++) {
                int index = getIndex(line, col);
                Zone zone;
                if (data[line][col] != EMPTY && !traitedIndex.contains(index) && (zone = getZoneFor(index)) != null) {
                    zones.add(zone);
                    traitedIndex.addAll(zone.getIndexs());
                }
            }
        }
        int extraScore = zones.stream().mapToInt(Zone::heuristic).sum();

        return extraScore;
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
            if (data[x][y] == zone.getColor()) {
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
    protected BordState clone(){
        try {
            BordState m = (BordState) super.clone();
            m.isOver = false;
            m.data = new char[HEIGHT][WIDTH];
            for (int line = 0; line < HEIGHT; line++) {
                for (int column = 0; column < WIDTH; column++) {
                    m.data[line][column] = data[line][column];
                }
            }
            return m;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public char[] getData(int line) {
        return data[line];
    }

    @Override
    public String toString() {
        String s = "BordState : \n";
        for (int i = 0; i < HEIGHT; i++) {
            s += Arrays.toString(data[i]) + "\n";
        }
        return s;
    }
}
