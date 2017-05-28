package com.loic.algo.search;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeSearchUtils {
    private TreeSearchUtils() {
    }

    public static <E> List<E> asStringSort(Set<E> sets) {
        return sets.stream()
            .sorted(Comparator.comparing(Object::toString))
            .collect(Collectors.toList());
    }
}
