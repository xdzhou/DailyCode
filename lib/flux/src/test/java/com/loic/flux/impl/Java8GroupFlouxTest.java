package com.loic.flux.impl;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class Java8GroupFlouxTest {

  @Test
  public void key() {
    String key = "key";
    Java8GroupFlux<String, Integer> gf = new Java8GroupFlux<>(key, Stream.of(1));
    assertEquals(key, gf.key());
  }

}
