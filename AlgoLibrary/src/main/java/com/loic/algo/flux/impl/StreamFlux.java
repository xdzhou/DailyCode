package com.loic.algo.flux.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.loic.algo.flux.Flux;
import com.loic.algo.flux.GroupFlux;

public class StreamFlux<I> implements Flux<I> {

  private final Stream<? extends I> stream;

  public StreamFlux(Stream<? extends I> items) {
    this.stream = items;
  }

  @Override
  public <R> Flux<R> newFlux(Iterable<? extends R> iterable) {
    return new StreamFlux<>(StreamSupport.stream(iterable.spliterator(), false));
  }

  @Override
  public <K> Flux<GroupFlux<K, I>> groupBy(Function<? super I, ? extends K> keyMapper) {
    Map<K, List<I>> map = stream.collect(Collectors.groupingBy(keyMapper));
    return new StreamFlux<>(map.entrySet().stream().map(entry -> new StreamGroupFlux<>(entry.getKey(), entry.getValue().stream())));
  }

  @Override
  public <R> Flux<R> map(Function<? super I, ? extends R> mapper) {
    return new StreamFlux<>(stream.map(mapper));
  }

  @Override
  public Flux<I> filter(Predicate<? super I> predicate) {
    return new StreamFlux<>(stream.filter(predicate));
  }

	@Override
	public Flux<I> peek(Consumer<I> consumer) {
		return new StreamFlux<>(stream.peek(consumer));
	}

	@Override
	@SuppressWarnings("unchecked")
  public <R> Flux<R> flatMap(Function<? super I, Flux<? extends R>> mapper) {
    Stream<R> flatStream = stream.map(mapper)
                                 .map(flux -> (StreamFlux<R>)flux)
                                 .flatMap(f -> f.stream);
    return new StreamFlux<>(flatStream);
  }

  @Override
  public <R> Flux<R> reduce(R initialValue, BiFunction<R, ? super I, R> accumulator) {
    R result = stream.reduce(initialValue, accumulator, (a, b) -> a);
    return new StreamFlux<>(Stream.of(result));
  }

  @Override
	public Flux<List<I>> toList() {
  	List<I> list = stream.collect(Collectors.toList());
		return new StreamFlux<>(Stream.of(list));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <R> Flux<R> compose(Transformer<? super I, ? extends R>  transformer) {
		return ((Transformer<I, R>) transformer).apply(this);
	}

	@Override
  public I single() {
		return stream.findFirst().orElse(null);
  }
}
