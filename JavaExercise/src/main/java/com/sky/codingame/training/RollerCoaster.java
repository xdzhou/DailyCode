package com.sky.codingame.training;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class RollerCoaster {

    public static void main(String args[]) {
        int L, C, N;
        Queue<Integer> queue = new LinkedList<>();

        Scanner in = new Scanner(System.in, "UTF-8");
        L = in.nextInt();
        C = in.nextInt();
        N = in.nextInt();
        for (int i = 0; i < N; i++) {
            queue.offer(in.nextInt());
        }
        int cap;
        int nbGroup = queue.size();
        int numFlag = 1;
        Set<Integer> noteSet = new HashSet<>();
        long resulta = 0;
        for (int i = 1; i <= C; i++) {
            if (noteSet.contains(numFlag)) {

            } else {
                noteSet.add(numFlag);
            }

            int size = nbGroup;
            cap = 0;
            while (true) {
                cap += queue.peek();
                if (cap <= L) {
                    queue.offer(queue.poll());
                    size--;
                    if (size == 0)
                        break;
                } else
                    break;
            }
            if (cap > L)
                cap -= queue.element();
            System.out.println("cap = " + cap);
            resulta += cap;
        }
        System.out.println(resulta);
        in.close();
    }
}
