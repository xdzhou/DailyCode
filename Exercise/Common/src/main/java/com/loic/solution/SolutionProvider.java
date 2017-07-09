package com.loic.solution;

import java.util.List;
import java.util.function.Function;

/**
 * common interface for solution provider which could have several solutions
 *
 * @param <T> input parameter
 * @param <E> output result
 */
public interface SolutionProvider<T, E> {
    List<Function<T, E>> solutions();
}
