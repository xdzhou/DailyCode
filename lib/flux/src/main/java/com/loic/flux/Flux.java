package com.loic.flux;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Flux<I> {

  <R> Flux<R> newFlux(Iterable<R> iterable);


  <K> Flux<GroupFlux<K, I>> groupBy(Function<? super I, ? extends K> keyMapper);

  <R> Flux<R> map(Function<? super I, ? extends R> mapper);

  Flux<I> filter(Predicate<? super I> predicate);

  Flux<I> peek(Consumer<I> consumer);

  <R> Flux<R> flatMap(Function<? super I, Flux<? extends R>> mapper);

  <R> Flux<R> reduce(R initialValue, BiFunction<R, ? super I, R> accumulator);

  Flux<List<I>> toList();

  Flux<Set<I>> toSet();

  <R> Flux<R> compose(Transformer<? super I, ? extends R> transformer);

  I single();

  /**
   * Transform an Flux by applying a particular Transformer function to it.
   */
  interface Transformer<T, R> extends Function<Flux<T>, Flux<R>> {
  }

}
