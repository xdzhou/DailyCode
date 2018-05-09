package com.loic;

import com.loic.algo.formula.Literal;

public interface PipelineItemResolver<Item> {
  <K> K nextLevelKey(Item item, K curKey);

  Literal value(Item item);
}
