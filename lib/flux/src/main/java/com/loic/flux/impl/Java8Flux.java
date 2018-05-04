package com.loic.flux.impl;

import com.loic.flux.Flux;
import com.loic.flux.GroupFlux;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Java8Flux<I> implements Flux<I> {

  private final Stream<I> stream;

  public Java8Flux(Stream<I> items) {
    this.stream = items;
  }

  @Override
  public <R> Flux<R> newFlux(Iterable<R> iterable) {
    return new Java8Flux<>(StreamSupport.stream(iterable.spliterator(), false));
  }

  @Override
  public <K> Flux<GroupFlux<K, I>> groupBy(Function<? super I, ? extends K> keyMapper) {
    Map<K, List<I>> map = stream.collect(Collectors.groupingBy(keyMapper));
    return new Java8Flux<>(map.entrySet()
        .stream()
        .map(entry -> new Java8GroupFlux<>(entry.getKey(), entry.getValue().stream())));
  }

  @Override
  public <R> Flux<R> map(Function<? super I, ? extends R> mapper) {
    return new Java8Flux<>(stream.map(mapper));
  }

  @Override
  public Flux<I> filter(Predicate<? super I> predicate) {
    return new Java8Flux<>(stream.filter(predicate));
  }

  @Override
  public Flux<I> peek(Consumer<I> consumer) {
    return new Java8Flux<>(stream.peek(consumer));
  }

  @Override
  @SuppressWarnings("unchecked")
  public <R> Flux<R> flatMap(Function<? super I, Flux<? extends R>> mapper) {
    Stream<R> flatStream = stream.map(mapper)
        .map(flux -> (Java8Flux<R>) flux)
        .flatMap(f -> f.stream);
    return new Java8Flux<>(flatStream);
  }

  @Override
  public <R> Flux<R> reduce(R initialValue, BiFunction<R, ? super I, R> accumulator) {
    R result = stream.reduce(initialValue, accumulator, (a, b) -> a);
    return new Java8Flux<>(Stream.of(result));
  }

  @Override
  public Flux<List<I>> toList() {
    List<I> list = stream.collect(Collectors.toList());
    return new Java8Flux<>(Stream.of(list));
  }

  @Override
  public Flux<Set<I>> toSet() {
    Set<I> set = stream.collect(Collectors.toSet());
    return new Java8Flux<>(Stream.of(set));
  }

  @Override
  @SuppressWarnings("unchecked")
  public <R> Flux<R> compose(Transformer<? super I, ? extends R> transformer) {
    return ((Transformer<I, R>) transformer).apply(this);
  }

  @Override
  public I single() {
    return stream.findFirst().orElse(null);
  }
}
