package com.loic.common;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestHelper {
    private TestHelper() {
    }

    public static Set<String> toSet(String... data) {
        //FIXME use immutable list
        return new HashSet<>(asList(data));
    }

    public static <T> T[] toArray(T... list) {
        return list;
    }

    public static <T> List<T> toList(T... list) {
        //FIXME use immutable list
        return asList(list);
    }
}
