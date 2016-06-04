package com.sky.codingame.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ThorVSGiants {
    private static final int mHeight = 18;
    private static final int mWidth = 40;

    public static void main(String args[]) {
        ThorVSGiants algo = new ThorVSGiants();

        Scanner in = new Scanner(System.in);
        algo.root.curX = in.nextInt();
        algo.root.curY = in.nextInt();

        // game loop
        while (true) {
            int H = in.nextInt(); // the remaining number of hammer strikes.
            int N = in.nextInt(); // the number of giants which are still present on the map.
            if (algo.totalGiant < 0) algo.totalGiant = N;
            algo.root.round = 0;
            algo.root.strikeNb = H;
            algo.root.giants.clear();
            for (int i = 0; i < N; i++) {
                int X = in.nextInt();
                int Y = in.nextInt();
                algo.root.giants.add(X * mHeight + Y);
            }
            System.out.println(algo.getNextAction());
        }
    }

    private Node root;
    private int totalGiant = -1;

    public ThorVSGiants() {
        root = new Node();
    }

    public String getNextAction() {
        System.err.println("Cur ROOT : "+root);
        process(root, 5);
        printStep(root);
        String nextStep = (root.bestChild != null) ? root.bestChild.step.toString() : "STRIKE";
        root = root.bestChild;
        return nextStep;
    }

    private float process(Node node, int depth) {
        if (depth == 0 || node.isOver()) {
            float fitness = node.heuristic(totalGiant);
            //System.err.println("fitness: " + fitness+", Node: " + node);
            return fitness;
        } else {
            float best = Integer.MIN_VALUE;
            Node bestChild = null;
            for(Step step : Step.values()) {
                Node child = node.applyStep(step);
                if (child != null) {
                    float value = process(child, depth - 1);
                    if (value > best) {
                        best = value;
                        bestChild = child;
                        if (best == Integer.MAX_VALUE) break;
                    }
                }
            }
            node.bestChild = bestChild;
            return best;
        }
    }

    private static void printStep(Node n) {
        Node curNode = n;
        while (curNode != null) {
            System.err.println(curNode);
            curNode = curNode.bestChild;
        }
    }

    private static class Node implements Cloneable {
        private Node bestChild;
        private Step step;
        private int round = 0;
        private int strikeNb;
        private int curX, curY;
        private List<Integer> giants = new ArrayList<Integer>();

        public boolean isOver() {
            return isLose() || isWin();
        }

        private boolean isWin() {
            return giants.isEmpty();
        }

        private boolean isLose() {
            boolean isLose = (curX < 0 || curX >= mWidth || curY < 0 || curY >= mHeight); //out of map
            if (!isLose) isLose = giants.contains(curX * mHeight + curY); //caught
            if (!isLose) isLose = strikeNb <= 0 && giants.size() > 0;
            return isLose;
        }

        //return next child Node
        public Node applyStep(Step step) {
            if (curX + step.deltaX < 0 || curY + step.deltaY < 0) return null;
            if (curX + step.deltaX >= mWidth || curY + step.deltaY >= mHeight) return null;

            List<Integer> arounds = null;
            if (step == Step.STRIKE) {
                if (strikeNb <= 0) return null;
                arounds = giantsAround();
                if (arounds == null) return null;
            }
            Node child = clone();
            child.step = step;
            child.round = round + 1;
            child.curX += step.deltaX;
            child.curY += step.deltaY;
            if (arounds != null) {
                System.err.println("strike giants : "+arounds.size());
                child.giants.removeAll(arounds);
                child.strikeNb --;
            }
            child.moveGiants();
            return child;
        }

        private List<Integer> giantsAround() {
            List<Integer> retVal = new ArrayList<Integer>();
            for(int index : giants) {
                int x = index / mHeight;
                int y = index % mHeight;
                if (Math.abs(curX - x) <= 4 && Math.abs(curY - y) <= 4) {
                    retVal.add(index);
                }
            }
            return retVal.isEmpty() ? null : retVal;
        }

        public void moveGiants() {
            //Collections.sort(giants, mComparator);
            for(int i = 0, size = giants.size(); i < size; i++) {
                int index = giants.get(i);
                int x = index / mHeight;
                int y = index % mHeight;
                float angle = (float) Math.toDegrees(Math.atan2(Math.abs(curY - y), Math.abs(curX - x)));
                if (angle <= 30) {
                    x += Integer.compare(curX, x);
                } else if (angle >= 60) {
                    y += Integer.compare(curY, y);
                } else {
                    x += Integer.compare(curX, x);
                    y += Integer.compare(curY, y);
                }

                giants.set(i, x * mHeight + y);
            }
        }

        public float heuristic(int totalGiant) {
            float fitness;
            if (isLose()) fitness = Integer.MIN_VALUE;
            else if(isWin()) fitness = Integer.MAX_VALUE;
            else {
                int maxValue = mWidth + mHeight;
                fitness = (totalGiant - giants.size()) * maxValue;
                for (int i = 0; i <giants.size(); i++) {
                    int level = getLevel(giants.get(i));
                    if (level <= 4) fitness += (maxValue - 1);
                    else fitness += (mWidth + mHeight - getDis(giants.get(i)));
                }
            }
            return fitness;
        }

        private int getLevel(int index) {
            return Math.max(Math.abs(curX - index / mHeight), Math.abs(curY - index % mHeight));
        }

        private float getDis(int index) {
            int deltaX = curX - index / mHeight;
            int deltaY = curY - index % mHeight;
            return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }

        private final Comparator<Integer> mComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return getLevel(o1) - getLevel(o2);
            }
        };

        @Override
        protected Node clone() {
            try {
                Node n = (Node) super.clone();
                n.giants = new ArrayList<Integer>(giants);
                return n;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public String toString() {
            return "{step=" + step +
                    ", round=" + round +
                    ", strikeNb=" + strikeNb +
                    ", curX=" + curX +
                    ", curY=" + curY +
                    ", giants=" + printGiant() +
                    '}';
        }

        private String printGiant() {
            StringBuilder sb = new StringBuilder("[");
            for(int index : giants) {
                sb.append(index / mHeight).append(',').append(index % mHeight).append("  ");
            }
            sb.append(']');
            return sb.toString();
        }
    }

    private enum Step {
        STRIKE(0, 0),
        //WAIT(0,0),

        N(0, -1),
        NE(1, -1),
        E(1, 0),
        SE(1, 1),
        S(0, 1),
        SW(-1, 1),
        W(-1, 0),
        NW(-1, -1);

        private int deltaX, deltaY;

        Step(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
}
