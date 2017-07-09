package com.sky.recursion;

import java.util.ArrayList;
import java.util.List;

import com.sky.solution.AbstractSolutionProvider;

public class EightQueens extends AbstractSolutionProvider<Void, Integer> {

    @Override
    protected Integer resolve(Void param) {
        List<Integer> positions = new ArrayList<>(8);
        return search(0, positions);
    }

    private int search(int curLine, List<Integer> positions) {
        if (curLine == 8) {
            System.out.println("solution : " + positions);
            return 1;
        }
        int result = 0;
        for (int i = 0; i < 8; i++) {
            boolean available = true;
            for (int j = 0; j < positions.size() && available; j++) {
                int p = positions.get(j);
                if (p % 8 == i || Math.abs(curLine - (p / 8)) == Math.abs(i - (p % 8))) {
                    available = false;
                }
            }
            if (available) {
                positions.add(curLine * 8 + i);
                result += search(curLine + 1, positions);
                positions.remove(positions.size() - 1);
            }
        }
        return result;
    }
}
