package com.loic.algo.formula;

public interface ExprRegistry<ID extends ExprId, Key> {
  Expr value(ID id, Key key);
}
