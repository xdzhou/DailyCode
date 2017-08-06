package com.loic.codinGame.smash;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.base.Preconditions;
import com.loic.algo.search.core.ApplyStrategy;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TransitionStrategy;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.impl.MinimaxAlphaBeta;
import com.loic.codinGame.CodinGameResolver;

public class SmashPlayer implements CodinGameResolver<String> {
    static final Set<Drop> DROP_SET;
    private static final int MAX_LEN = 8;

    private final ColorSet[] colors = new ColorSet[MAX_LEN];
    private GameState rootState = new GameState();
    private TreeSearch algo = new MinimaxAlphaBeta();
    private SearchParam<GameState, Drop> param = searchParam(colors, 6);

    @Override
    public void accept(Scanner in, Consumer<String> outputConsumer) {
        for (int i = 0; i < MAX_LEN; i++) {
            int colorA = in.nextInt(); // color of the first block
            int colorB = in.nextInt(); // color of the attached block
            colors[i] = new ColorSet((char)('0'+colorA), (char)('0'+colorB));
        }
        rootState.setMyScore(in.nextInt());
        for (int i = 0; i < 12; i++) {
            String row = in.next();
            rootState.myBord().setLine(row, i);
        }
        rootState.setOtherScore(in.nextInt());
        for (int i = 0; i < 12; i++) {
            String row = in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
            rootState.otherBord().setLine(row, i);
        }

        Drop drop = algo.find(rootState, param).get();
        rootState.apply(drop, colors);
        outputConsumer.accept(drop.column() + " " + drop.rotation());
        //System.out.println(drop.column() + " " + drop.rotation()); // "x": the column in which to drop your blocks
    }

    static {
        DROP_SET = new HashSet<>();
        for (int rotation = 0; rotation < 4; rotation ++) {
            int colCount = (rotation == 0 || rotation == 2) ? 5 : 6;
            for (int col = 0; col < colCount; col++) {
                DROP_SET.add(new Drop(col, rotation));
            }
        }
    }

    static SearchParam<GameState, Drop> searchParam(ColorSet[] colors, int depth) {
        Preconditions.checkState(depth > 0 && depth % 2 == 0);
        return SearchParam.<GameState, Drop>builder()
            .applyStrategy(applyStrategy(colors))
            .heuristicStrategy(heuristicStrategy())
            .transitionStrategy(transitionStrategy())
            .maxDepth(depth)
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
            //TODO other feature to estimate
            return gameState.myScore() - gameState.otherScore() + gameState.extraScore();
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
