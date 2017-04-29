package com.loic.algo.common;

public class Triple<T, E, K> {
    private final Pair<T, E> pair;
    private final K third;

    private Triple(T first, E second, K third) {
        this.pair = Pair.of(first, second);
        this.third = third;
    }

    public static <T, E, K> Triple<T, E, K> of(T first, E second, K third) {
        return new Triple<>(first, second, third);
    }

    public T first() {
        return pair.first();
    }

    public E second() {
        return pair.second();
    }

    public K third() {
        return third;
    }

    @Override
    public String toString() {
        return "[" + pair.first() + ", " + pair.second() + ", " + third + "]";
    }
}
