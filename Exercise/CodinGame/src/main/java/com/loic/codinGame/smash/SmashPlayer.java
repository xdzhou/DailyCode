package com.loic.codinGame.smash;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.loic.algo.search.core.ApplyStrategy;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TransitionStrategy;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.impl.MinimaxAlphaBeta;

public class SmashPlayer {
    public static final Set<Drop> DROP_SET;
    private static final int MAX_LEN = 8;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        final ColorSet[] colors = new ColorSet[MAX_LEN];

        GameState rootState = new GameState();
        TreeSearch algo = new MinimaxAlphaBeta();
        SearchParam<GameState, Drop> param = searchParam(colors);

        // game loop
        while (true) {
            for (int i = 0; i < MAX_LEN; i++) {
                int colorA = in.nextInt(); // color of the first block
                int colorB = in.nextInt(); // color of the attached block
                colors[i] = new ColorSet((char)('0'+colorA), (char)('0'+colorB));
            }
            rootState.checkMyScore(in.nextInt());
            for (int i = 0; i < 12; i++) {
                String row = in.next();
                rootState.myBord().checkLine(row, i);
            }
            rootState.checkOtherScore(in.nextInt());
            for (int i = 0; i < 12; i++) {
                String row = in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
                rootState.otherBord().checkLine(row, i);
            }

            Drop drop = algo.find(rootState, param).get();
            rootState.apply(drop, colors);
            System.out.println(drop.column() + " " + drop.rotation()); // "x": the column in which to drop your blocks
        }
    }

    static {
        List<Drop> list = new LinkedList<>();
        for (int rotation = 0; rotation < 4; rotation ++) {
            int colCount = (rotation == 0 || rotation == 2) ? 5 : 6;
            for (int col = 0; col < colCount; col++) {
                list.add(new Drop(col, rotation));
            }
        }
        DROP_SET = ImmutableSet.copyOf(list);
    }

    static SearchParam<GameState, Drop> searchParam(ColorSet[] colors) {
        return SearchParam.<GameState, Drop>builder()
            .applyStrategy(applyStrategy(colors))
            .heuristicStrategy(heuristicStrategy())
            .transitionStrategy(transitionStrategy())
            .maxDepth(colors.length * 2 - 1)
            .build();
    }

    private static ApplyStrategy<GameState, Drop> applyStrategy(ColorSet[] colors) {
        return (gameState, drop) -> {
            GameState clone = gameState.clone();
            clone.apply(drop, colors);
            return clone;
        };
    }

    private static HeuristicStrategy<GameState> heuristicStrategy() {
        return (gameState, depth) -> {
            return gameState.myScore() - gameState.otherScore();
        };
    }

    private static TransitionStrategy<GameState, Drop> transitionStrategy() {
        return gameState -> {
            BordState bordState = gameState.isMyTurn() ? gameState.myBord() : gameState.otherBord();
            if (bordState.isOver()) {
                return Collections.emptySet();
            } else {
                return DROP_SET;
            }
        };
    }
}
