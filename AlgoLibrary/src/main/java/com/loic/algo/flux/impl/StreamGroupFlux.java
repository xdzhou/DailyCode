package com.loic.algo.flux.impl;

import java.util.stream.Stream;

import com.loic.algo.flux.GroupFlux;


public class StreamGroupFlux<K, I> extends StreamFlux<I> implements GroupFlux<K, I> {
  private final K key;

  StreamGroupFlux(K key, Stream<I> stream) {
    super(stream);
    this.key = key;
  }

  @Override
  public K key() {
    return key;
  }

}
