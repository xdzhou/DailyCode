package com.loic.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skynet {

    public static void main(String args[]) {
        new Skynet().start();
    }

    private void start() {
        Scanner in = new Scanner(System.in, "UTF-8");
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        Graph graph = new Graph(N);
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            graph.connect(N1, N2);
            graph.connect(N2, N1);
        }
        int[] ends = new int[E];
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            ends[i] = EI;
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            int[] result = graph.getShortestLastLink(SI, ends);

            System.out.println(result[0] + " " + result[1]); // Example: 0 1 are the indices of the nodes you wish to sever the link between
            graph.disconnect(result[0], result[1]);
            graph.disconnect(result[0], result[1]);
        }
    }

    private static class Graph {
        private boolean[][] mMaps;

        private Graph(int size) {
            mMaps = new boolean[size][size];
        }

        private void connect(int index1, int index2) {
            mMaps[index1][index2] = true;
        }

        private void disconnect(int index1, int index2) {
            mMaps[index1][index2] = false;
        }

        private int[] getShortestLastLink(int start, int... ends) {
            int size = mMaps.length;
            List<Integer> toTouchNodes = new ArrayList<>();
            boolean[] removeFlags = new boolean[size];
            int[] prevNodes = new int[size];
            List<Integer> destinations = new ArrayList<>(ends.length);
            for (int i = 0; i < ends.length; i++) {
                destinations.add(ends[i]);
            }
            toTouchNodes.add(start);
            while (!destinations.contains(toTouchNodes.get(0))) {
                int node = toTouchNodes.get(0);
                if (!removeFlags[node]) {
                    for (int i = 0; i < size; i++) {
                        if (mMaps[node][i] && !removeFlags[i] && !toTouchNodes.contains(i)) {
                            prevNodes[i] = node;
                            toTouchNodes.add(i);
                        }
                    }
                }
                toTouchNodes.remove(0);
                removeFlags[node] = true;
            }
            int[] result = new int[2];
            result[1] = toTouchNodes.get(0);
            result[0] = prevNodes[result[1]];
            return result;
        }
    }
}
