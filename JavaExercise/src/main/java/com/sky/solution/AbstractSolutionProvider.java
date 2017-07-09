package com.sky.solution;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractSolutionProvider<T, E> implements SolutionProvider<T, E> {
    @Override
    public List<Function<T, E>> solutions() {
        return Collections.singletonList(this::resolve);
    }

    protected abstract E resolve(T input);
}
