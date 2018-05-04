package com.loic.flux.impl;

import com.loic.flux.GroupFlux;

import java.util.stream.Stream;

public class Java8GroupFlux<K, I> extends Java8Flux<I> implements GroupFlux<K, I> {
  private final K key;

  Java8GroupFlux(K key, Stream<I> stream) {
    super(stream);
    this.key = key;
  }

  @Override
  public K key() {
    return key;
  }

}
