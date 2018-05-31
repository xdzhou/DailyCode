package com.loic.solution;

import java.util.List;
import java.util.function.BiFunction;

/**
 * common interface for solution provider which could have several solutions
 *
 * @param <T> input parameter1
 * @param <K> input parameter2
 * @param <E> output result
 */
public interface BiSolutionProvider<T, K, E> {
  List<BiFunction<T, K, E>> solutions();
}
