package com.sky.solution;

import java.util.List;
import java.util.function.Function;

/**
 * common interface for the problem has one input and one output
 *
 * @param <T> input parameter
 * @param <E> output result
 */
public interface SolutionProvider<T, E> {
    List<Function<T, E>> solutions();
}
