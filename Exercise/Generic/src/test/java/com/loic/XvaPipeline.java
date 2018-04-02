package com.loic;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.loic.algo.flux.Flux.Transformer;
import com.loic.algo.formula.*;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiFunction;

public class XvaPipeline<Item> {
  private ExprRegistry<ExprId, Serializable> exprRegistry;
  private PipelineItemResolver<Item> itemResolver;

  public Transformer<Item, Literal> pipeline(Serializable key, ExprId id) {
    Set<ExprId> selfIds = new HashSet<>();
    Map<ExprId, ExprDependency> selfDepens = new HashMap<>();

    LinkedList<ExprId> toAnalyse = new LinkedList<>();
    toAnalyse.add(id);
    while (!toAnalyse.isEmpty()) {
      ExprId next = toAnalyse.poll();
      ExprDependency deps = DependenciesCollector.collectSelfDependencies(exprRegistry.value(next, key));
      selfIds.add(next);
      selfDepens.put(next, deps);
      toAnalyse.addAll(Sets.difference(deps.selfs(), selfIds));
    }
    //check children & item
    ExprDependency mergedDepens = DependenciesCollector.merge(selfDepens.values());

    //
    if (!mergedDepens.children().isEmpty()) {
      return
          o -> o.flatMap(item -> o.newFlux(mergedDepens.children())
              .map(exprId -> Maps.immutableEntry(exprId, item)))
              .groupBy(Map.Entry::getKey)
              .flatMap(idg -> idg.map(Map.Entry::getValue)
                  .groupBy(item -> itemResolver.nextLevelKey(item, key))
                  .flatMap(g -> g.compose(pipeline(g.key(), idg.key())))
                  .flatMap(childLiteral -> idg.newFlux(mergedDepens.opForChild(idg.key()))
                      .map(op -> Maps.immutableEntry(op, childLiteral)))
                  .groupBy(Map.Entry::getKey)
                  .flatMap(opg -> opg.map(Map.Entry::getValue)
                      .reduce(Expr.NOP, accumulator(opg.key()))
                      .map(this::compute)
                      .map(lit -> new ResolvedStream(opg.key(), idg.key(), lit))))
              .toList()
              .map(list -> computeWithResolvedStream(id, list));
    } else {
      return
          o -> o.flatMap(item -> o.newFlux(mergedDepens.opForItem())
              .map(op -> Maps.immutableEntry(op, item)))
              .groupBy(Map.Entry::getKey)
              .flatMap(opg -> opg.map(Map.Entry::getValue)
                  .map(itemResolver::value)
                  .reduce(Expr.NOP, accumulator(opg.key()))
                  .map(this::compute)
                  .map(lit -> new ResolvedStream(opg.key(), null, lit)))
              .toList()
              .map(list -> computeWithResolvedStream(id, list));
    }
  }

  private BiFunction<Expr, Literal, Expr> accumulator(StreamOp op) {
    return (expr, lit) -> {
      if (expr == Expr.NOP) {
        return lit;
      } else {
        switch (op) {
          case Sum:
            return compute(null);
          case SumNeg:
            return compute(null);
          case SumPos:
            return compute(null);
        }
      }
    };
  }

  private Literal computeWithResolvedStream(ExprId id, List<ResolvedStream> computedStreams) {
    return computeWithResolvedStream(id, computedStreams, Maps.newHashMap());
  }

  private Literal computeWithResolvedStream(ExprId id, List<ResolvedStream> computedStreams, Map<ExprId, Literal> computed) {
    if (computed.containsKey(id)) {
      return computed.get(id);
    } else {
      Literal result = null;

      computed.put(id, result);
      return result;
    }
  }

  private Literal compute(Expr expr) {
    if (expr instanceof Literal) {
      return (Literal) expr;
    } else {
      //
    }
  }

  private static class ResolvedStream {
    private StreamOp op;
    private ExprId id;
    private Literal value;

    private ResolvedStream(StreamOp op, ExprId id, Literal value) {
      this.op = op;
      this.id = id;
      this.value = value;
    }
  }
}