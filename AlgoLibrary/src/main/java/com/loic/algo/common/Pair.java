package com.loic.algo.common;

public class Pair<T, E> implements Cloneable {
    private final T first;
    private final E second;

    private Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    public static <T, E> Pair<T, E> of(T first, E second) {
        return new Pair<>(first, second);
    }

    public T first() {
        return first;
    }

    public E second() {
        return second;
    }

    @Override
    public Pair<T, E> clone() {
        try {
            return (Pair<T, E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[" + first + ", " + second + "]";
    }
}
