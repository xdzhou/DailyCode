package com.loic;

import com.loic.flux.Flux;
import com.loic.flux.impl.Java8Flux;

import java.util.stream.StreamSupport;

public class Fluxs {
  private Fluxs() {
    //utils
  }

  public static <I> Flux<I> java8Flux(Iterable<I> iterable) {
    return new Java8Flux<>(StreamSupport.stream(iterable.spliterator(), false));
  }
}
