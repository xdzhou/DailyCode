package com.loic.algo.common;

public class Triple<T, E, K> {
	private Pair<T, E> pair;
	private K third;

	public Triple(T first, E second, K third) {
		this.pair = Pair.create(first, second);
		this.third = third;
	}

	public T getFirst() {
		return pair.getFirst();
	}

	public void setFirst(T first) {
		this.pair.setFirst(first);
	}

	public E getSecond() {
		return pair.getSecond();
	}

	public void setSecond(E second) {
		this.pair.setSecond(second);
	}

	public K getThird() {
		return third;
	}

	public void setThird(K third) {
		this.third = third;
	}

	@Override
	public String toString() {
		return "[" + pair.getFirst() + ", " + pair.getSecond() + ", " + third + "]";
	}

	public static <T, E, K> Triple<T, E, K> create(T first, E second, K third) {
		return new Triple<>(first, second, third);
	}
}
