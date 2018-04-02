package com.loic.algo.flux;

public interface GroupFlux<K, I> extends Flux<I> {

	K key();

}
