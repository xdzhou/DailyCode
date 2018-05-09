package com.loic.flux;

public interface GroupFlux<K, I> extends Flux<I> {

  K key();

}
