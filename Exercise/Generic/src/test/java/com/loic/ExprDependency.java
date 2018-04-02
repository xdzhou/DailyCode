package com.loic;

import com.loic.algo.formula.ExprId;
import com.loic.algo.formula.StreamOp;

import java.util.Set;

public interface ExprDependency {
  Set<ExprId> selfs();

  Set<ExprId> children();

  Set<StreamOp> opForChild(ExprId id);

  boolean items();

  Set<StreamOp> opForItem();
}
