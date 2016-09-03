package com.sky.hackerrank;

import com.sky.problem.ProblemTwoSolutions;

/**
 * URL : https://www.hackerrank.com/challenges/new-year-chaos
 */
public class NewYearChaos implements ProblemTwoSolutions<Integer[], String> {
    static final String NO_ANSWER = "Too chaotic";

    @Override
    public String resolve(Integer[] param) {
        if (param.length == 0) return NO_ANSWER;
        if (param.length == 1) return String.valueOf(0);

        int sum = 0;
        for (int i = 0; i < param.length; i++) {
            int bribes = 0;
            int j = i - 1;
            while (j >= 0) {
                if (param[j] > param[i]) bribes --;
                j--;
            }
            bribes = param[i] - (i + 1) - bribes;

            if (bribes < 0 || bribes > 2) return NO_ANSWER;
            sum += bribes;
        }
        return String.valueOf(sum);
    }

    @Override
    public String resolve2(Integer[] param) {
        if (param.length == 0) return NO_ANSWER;
        if (param.length == 1) return String.valueOf(0);

        Integer[] positions = new Integer[param.length];
        for (int i = 0; i < param.length; i++) {
            param[i] = param[i] - 1;
            positions[param[i]] = i;
        }
        return compute(param, positions);
    }

    private String compute(Integer[] values, Integer[] position) {
        int sum = 0;
        int len = values.length;
        for (int i = 0; i < len; i++) {
            if (i < Math.max(0, values[i] - 2)) {
                return NO_ANSWER;
            }

            int bribes = 0;
            if (i == 0 ) bribes = 0;
            else if (values[i] == 0) bribes = - i;
            else {
                int positionCheckNb = Math.min(i, len - 1 - i);
                int valueCheckNb = Math.min(values[i], len - 1 - values[i]);
                if (positionCheckNb < valueCheckNb) {
                    if (i < len - 1 - i) {
                        for (int j = 0; j < i; j++) {
                            if (values[j] > values[i]) bribes ++;
                        }
                    } else {
                        bribes = len - 1 - values[i]; //max possible count
                        for (int j = i + 1; j < len; j++) {
                            if (values[j] > values[i]) bribes --;
                        }
                    }
                } else {
                    if (values[i] < len - 1 - values[i]) {
                        bribes = i; //max possible count
                        for (int j = 0; j < values[i]; j++) {
                            if (position[j] < i) bribes --;
                        }
                    } else {
                        for (int j = values[i] + 1; j < len; j++) {
                            if (position[j] < i) bribes ++;
                        }
                    }
                }
                bribes = - bribes;
            }

            bribes = values[i] - i  - bribes;

            if (bribes < 0 || bribes > 2) {
                return NO_ANSWER;
            }
            sum += bribes;
        }
        return String.valueOf(sum);
    }

}
