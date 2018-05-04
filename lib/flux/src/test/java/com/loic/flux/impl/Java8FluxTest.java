package com.loic.flux.impl;

import com.loic.flux.Flux;
import com.loic.flux.Flux.Transformer;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class Java8FluxTest {

  @Test
  public void testNewFlow() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of());
    assertNotNull(flux.newFlux(Arrays.asList(1, 2)));
  }

  @Test
  public void testGroupBy() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of(1, 2, 3, 4));

    Set<Integer> keys = new HashSet<>(Arrays.asList(0, 1));

    flux.groupBy(num -> num % 2)
        .peek(gf -> assertTrue(keys.remove(gf.key())))
        .single();
  }

  @Test
  public void testMap() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of(1));
    flux.map(num -> num * 2)
        .peek(num -> assertEquals(2, num, 0))
        .single();
  }

  @Test
  public void testFilter() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of(1, 2, 3, 4));
    flux.filter(num -> num % 2 == 0)
        .peek(num -> assertEquals(0, num % 2))
        .single();
  }

  @Test
  public void testPeek() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of(1, 2, 3, 4));

    Set<Integer> items = new HashSet<>(Arrays.asList(1, 2, 3, 4));

    flux.peek(num -> assertTrue(items.remove(num)))
        .single();
  }

  @Test
  public void testFlatMap() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of(1, 2, 3, 4));

    flux.flatMap(num -> flux.newFlux(Arrays.asList(0, 0)))
        .peek(num -> assertEquals(0, num, 0))
        .single();
  }

  @Test
  public void testToList() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);

    assertEquals(list, new Java8Flux<>(list.stream()).toList().single());
  }

  @Test
  public void testToSet() {
    List<Integer> list = Arrays.asList(1, 2, 3, 1);
    Set<Integer> set = new HashSet<>(list);

    assertEquals(set, new Java8Flux<>(list.stream()).toSet().single());
  }


  @Test
  public void testReduce() {
    Flux<Integer> flux = new Java8Flux<>(Stream.of(1, 2, 3, 4));

    int sum = flux.reduce(0, (res, num) -> res + num).single();

    assertEquals(10, sum, 0);
  }

  @Test
  public void testCompose() {
    Transformer<Integer, Integer> transformer =
        f -> f.reduce(0, (res, num) -> res + num);

    Flux<Integer> flux = new Java8Flux<>(Stream.of(1, 2, 3, 4));
    int sum = flux.compose(transformer).single();
    assertEquals(10, sum, 0);
  }

}
