package com.sky.problem;

public interface ProblemThreeSolutions<T, E> extends ProblemTwoSolutions<T, E> {
    E resolve3(T param);
}
