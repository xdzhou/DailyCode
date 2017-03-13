package com.sky.codingame.training;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SuperComputer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        int nb = 0;
        int position = 0;

        int n = in.nextInt();
        Task jobs[] = new Task[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Task();
            jobs[i].debut = in.nextInt();
            jobs[i].fin = in.nextInt() - 1 + jobs[i].debut;
        }

        Arrays.sort(jobs, TASK_COMPARATOR);
        position = jobs[0].debut - 1;
        for (int i = 0; i < n; i++) {
            if (jobs[i].debut > position) {
                position = jobs[i].fin;
                nb++;
            }
        }

        System.out.println(nb);
        in.close();
    }

    private static class Task {
        private int debut = 0;
        private int fin = 0;
    }

    private static final Comparator<Task> TASK_COMPARATOR = (o1, o2) -> o1.fin - o2.fin;
}
