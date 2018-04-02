package com.loic.algo.flux;

import java.util.List;
import java.util.function.*;

public interface Flux<I> {

  <R> Flux<R> newFlux(Iterable<? extends R> iterable);


  <K> Flux<GroupFlux<K, I>> groupBy(Function<? super I, ? extends K> keyMapper);

  <R> Flux<R> map(Function<? super I, ? extends R> mapper);

  Flux<I> filter(Predicate<? super I> predicate);

  Flux<I> peek(Consumer<I> consumer);

  <R> Flux<R> flatMap(Function<? super I, Flux<? extends R>> mapper);

  <R> Flux<R> reduce(R initialValue, BiFunction<R, ? super I, R> accumulator);

  Flux<List<I>> toList();

  <R> Flux<R> compose(Transformer<? super I, ? extends R> transformer);

  I single();

  /**
   * Transform an Flux by applying a particular Transformer function to it.
   */
  interface Transformer<T, R> extends Function<Flux<T>, Flux<R>> {
  }

}
