package com.sky.problem;

/**
 * common interface for the problem has one input and one output
 *
 * @param <T>
 *            input parameter
 * @param <E>
 *            output result
 */
public interface Problem<T, E> {
	public E resolve(T param);
}
