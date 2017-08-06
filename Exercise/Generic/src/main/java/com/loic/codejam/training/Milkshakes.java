package com.loic.codejam.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.loic.codejam.Resolver;

public class Milkshakes implements Resolver<String> {

    @Override
    public String solve(Scanner in) {
        int numType = in.nextInt();
        int numClient = in.nextInt();
        int[] typeMikeShark = new int[numType];
        for (int j = 0; j < numType; j++) {
            typeMikeShark[j] = -1;
        }
        List<ArrayList<Integer>> untreatedList = new ArrayList<>();

        boolean isPossible = true;
        for (int j = 0; j < numClient; j++) {
            int numFlavor = in.nextInt();
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int m = 0; m < numFlavor; m++) {
                int type = in.nextInt() - 1;
                if (typeMikeShark[type] == -1) {
                    tempList.add(type);
                    tempList.add(in.nextInt());
                }
            }
            if (tempList.size() == 0) {
                isPossible = false;
                break;
            } else if (tempList.size() == 2) {
                typeMikeShark[tempList.get(0)] = tempList.get(1);
            } else {
                untreatedList.add(tempList);
            }
        }
        if (!isPossible) {
            return "IMPOSSIBLE";
        } else {
            return untreatedList.toString();
        }
    }
}
