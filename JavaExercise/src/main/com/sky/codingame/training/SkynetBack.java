package com.sky.codingame.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SkynetBack {

    public static void main(String args[]) {
        new SkynetBack().start();
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
        graph.putEnds(ends);

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            int[] result = graph.passNodeWithGate(SI, ends);

            System.out.println(result[0] + " " + result[1]); // Example: 0 1 are the indices of the nodes you wish to sever the link between
            graph.disconnect(result[0], result[1]);
            graph.disconnect(result[1], result[0]);
        }
    }

    private static class Graph {
        private boolean[][] mMaps;
        Map<Integer, List<Integer>> specialPoints;
        private List<Integer> hasGates;

        private Graph(int size) {
            mMaps = new boolean[size][size];
        }

        private void connect(int index1, int index2) {
            mMaps[index1][index2] = true;
        }

        private void disconnect(int index1, int index2) {
            mMaps[index1][index2] = false;
        }

        private void putEnds(int... ends) {
            specialPoints = new HashMap<>();
            int size = mMaps.length;
            for(int end : ends) {
                for (int i = 0; i < size; i++) {
                    if (mMaps[end][i] ) {
                        List<Integer> gates = specialPoints.get(i);
                        if(gates == null){
                            gates = new ArrayList<>();
                            specialPoints.put(i, gates);
                        }
                        gates.add(end);
                    }
                }
            }
            hasGates = new ArrayList<>(specialPoints.keySet());
            for(Integer key : hasGates) {
                if (specialPoints.get(key).size() <= 1) {
                    specialPoints.remove(key);
                }
            }
            System.err.println("Special Map : "+specialPoints);
        }

        private int[] passNodeWithGate(int start, int... ends) {
            int gate = -1, gatePrev = -1;
            for(int endGate : ends) {
                if (mMaps[endGate][start]) {
                    gate = endGate;
                    gatePrev = start;
                    System.err.println("Direct link : "+gatePrev+", "+gate);
                    break;
                }
            }
            if (gate == -1) {
                int size = mMaps.length;
                List<Integer> toTouchNodes = new ArrayList<>();
                boolean[] removeFlags = new boolean[size];
                int[] prevNodes = new int[size];
                Arrays.fill(prevNodes, -1);
                toTouchNodes.add(start);

                while (! toTouchNodes.isEmpty()) {
                    int node = toTouchNodes.remove(0);
                    if (!removeFlags[node]) {
                        for (int i = 0; i < size; i++) {
                            if (mMaps[node][i] && hasGates.contains(i) && !removeFlags[i] && !toTouchNodes.contains(i)) {
                                prevNodes[i] = node;
                                toTouchNodes.add(i);
                            }
                        }
                    }
                    removeFlags[node] = true;
                }
                for (Map.Entry<Integer, List<Integer>> entry : specialPoints.entrySet()) {
                    if (prevNodes[entry.getKey()] != -1) {
                        gate = entry.getValue().get(0);
                        gatePrev = entry.getKey();
                        System.err.println("soft link : "+gatePrev+", "+gate);
                        break;
                    }
                }
            }
            if (gate == -1 && !specialPoints.isEmpty()) {
                Map.Entry<Integer, List<Integer>> entry = specialPoints.entrySet().iterator().next();
                gatePrev = entry.getKey();
                gate = entry.getValue().get(0);
                System.err.println("choice multi link : "+gatePrev+", "+gate);
            }
            if (gate == -1) {
                gatePrev = hasGates.get(0);
                for(int endGate : ends) {
                    if (mMaps[endGate][gatePrev]) {
                        gate = endGate;
                        System.err.println("choice single link : "+gatePrev+", "+gate);
                        break;
                    }
                }
            }
            if (gate == -1) System.err.println("IMPOSSIBLE");
            List<Integer> gateList = specialPoints.get(gatePrev);
            if (gateList != null) {
                gateList.remove(Integer.valueOf(gate));
                if (gateList.size() <= 1) specialPoints.remove(gatePrev);
            } else {
                hasGates.remove(Integer.valueOf(gatePrev));
            }

            int[] result = new int[2];
            result[0] = gate;
            result[1] = gatePrev;
            return result;
        }
    }
}
