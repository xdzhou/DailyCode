package com.loic.codejam.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.loic.codejam.Resolver;

public class MinimumScalarProduct implements Resolver<Long> {

    @Override
    public Long solve(Scanner in) {
        int num = in.nextInt();
        List<Long> list1 = new ArrayList<>(num);
        List<Long> list2 = new ArrayList<>(num);
        for (int j = 0; j < num; j++) {
            list1.add(in.nextLong());
        }
        for (int j = 0; j < num; j++) {
            list2.add(in.nextLong());
        }
        Collections.sort(list1);
        Collections.sort(list2);
        long sum = 0;
        for (int j = 0; j < num; j++) {
            sum += (list1.get(j) * list2.get(num - 1 - j));
        }
        return sum;
    }
}
