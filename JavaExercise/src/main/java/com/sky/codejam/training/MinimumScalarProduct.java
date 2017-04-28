package com.sky.codejam.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.sky.codejam.Resolver;

public class MinimumScalarProduct implements Resolver {

    @Override
    public String solve(Scanner in) {
        int num = in.nextInt();
        List<Long> list1 = new ArrayList<>(num);
        List<Long> list2 = new ArrayList<>(num);
        for (int j = 0; j < num; j++)
            list1.add(in.nextLong());
        for (int j = 0; j < num; j++)
            list2.add(in.nextLong());
        Collections.sort(list1);
        Collections.sort(list2);
        long somme = 0;
        for (int j = 0; j < num; j++) {
            somme += (list1.get(j) * list2.get(num - 1 - j));
        }
        return Long.toString(somme);
    }
}
