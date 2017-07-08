package com.sky.common;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.sky.solution.SolutionProvider;

public abstract class CommonTest<T, E> {
    private SolutionProvider<T, E> solutionProvider;

    public CommonTest() {
        solutionProvider = getAlgo();
        assertNotEquals(solutionProvider, null);
    }

    protected abstract SolutionProvider<T, E> getAlgo();

    public SolutionProvider<T, E> getProblem() {
        return solutionProvider;
    }

    protected void check(T input, E output) {
        for (Function<T, E> solution : solutionProvider.solutions()) {
            assertEquals(solution.apply(input), output);
        }
    }

    protected void checkInput(T input) {
        List<Function<T, E>> list = solutionProvider.solutions();
        assertTrue(list.size() > 1);
        E output = list.get(0).apply(input);
        for (int i = 1; i < list.size(); i++) {
            assertEquals(list.get(i).apply(input), output);
        }
    }

    protected void check(T input, BiConsumer<T, E> consumer) {
        for (Function<T, E> solution : solutionProvider.solutions()) {
            consumer.accept(input, solution.apply(input));
        }
    }

    // helper method
    protected <E> List<E> generateList(E... data) {
        return Arrays.asList(data);
    }

    protected Set<String> generateSet(String... data) {
        Set<String> result = new HashSet<String>(data.length);
        for (String s : data) {
            result.add(s);
        }
        return result;
    }

    protected Integer[] transform(Integer... list) {
        return list;
    }

    protected String[] transform(String... list) {
        return list;
    }

}
