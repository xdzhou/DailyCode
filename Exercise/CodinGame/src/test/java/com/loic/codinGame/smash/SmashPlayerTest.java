package com.loic.codinGame.smash;

import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.impl.MinimaxAlphaBeta;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SmashPlayerTest {
  private final Random random = new Random(new Date().getTime());

  @Test
  public void test() throws Exception {
    final ColorSet[] colorSets = new ColorSet[8];
    fill(colorSets);

    GameState rootState = new GameState();
    TreeSearch algo = new MinimaxAlphaBeta();
    SearchParam<GameState, Drop> param = SmashPlayer.searchParam(colorSets, 6);

    List<Drop> dropList = TreeSearchUtils.asStringSort(SmashPlayer.DROP_SET);

    for (int turn = 0; turn < 100; turn++) {
      Drop drop;
      if (turn == 0) {
        drop = dropList.get(0);
        rootState.apply(dropList.get(0), colorSets);
        rootState.apply(dropList.get(colorSets.length - 1), colorSets);
      } else {
        drop = algo.find(rootState, param).get();
        rootState.apply(drop, colorSets);
        rootState.apply(dropList.get(random.nextInt(colorSets.length)), colorSets);
      }
      if (rootState.isOver()) break;
      rootState.asRoot();
      addRandomOne(colorSets);

      //System.out.println(drop);
      //System.out.println(rootState);
    }
    assertFalse(rootState.myBord().isOver());
    assertTrue(rootState.otherBord().isOver());
  }

  private void fill(ColorSet[] colorSets) {
    for (int i = 0; i < colorSets.length; i++) {
      colorSets[i] = randomColorSet();
    }
  }

  private void addRandomOne(ColorSet[] colorSets) {
    for (int i = 1; i < colorSets.length; i++) {
      colorSets[i - 1] = colorSets[i];
    }
    colorSets[colorSets.length - 1] = randomColorSet();
  }

  private ColorSet randomColorSet() {
    return new ColorSet(random.nextInt(5) + 1, random.nextInt(5) + 1);
  }
}
