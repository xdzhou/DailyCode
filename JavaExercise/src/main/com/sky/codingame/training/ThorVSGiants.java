package com.sky.codingame.training;

import com.loic.algo.search.AbstractNode;
import com.loic.algo.search.BruteForce;
import com.loic.algo.search.Transition;

import java.util.*;

public class ThorVSGiants {
    private static final int mHeight = 18;
    private static final int mWidth = 40;

    public static void main(String args[]) {
        Algo algo = new Algo();

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

    private static class Algo extends BruteForce<Node, Step> {
        private Node root;
        private int totalGiant = -1;

        public Algo() {
            root = new Node();
        }

        public String getNextAction() {
            System.err.println("Cur ROOT : "+root);
            getNextTransition(root, 5);
            printStep(root);
            String nextStep = (root.getBestChild() != null) ? root.getBestChild().getTransition().toString() : "STRIKE";
            root = (Node) root.getBestChild();
            return nextStep;
        }

        private void printStep(Node n) {
            Node curNode = n;
            while (curNode != null) {
                System.err.println(curNode);
                curNode = (Node) curNode.getBestChild();
            }
        }

        @Override
        protected void onChildNodeCreated(Node parent, Node child, Step transition) {
            child.totalGiant = totalGiant;
        }
    }

    private static class Node extends AbstractNode<Step> implements Cloneable {
        private int totalGiant;
        private int round = 0;
        private int strikeNb;
        private int curX, curY;
        private List<Integer> giants = new ArrayList<Integer>();

        @Override
        public boolean isOver() {
            return isLose() || isWin();
        }

        @Override
        public List<Step> getPossibleTransitions() {
            return Arrays.asList(Step.values());
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
        @Override
        public Node applyTransition(Step step) {
            if (curX + step.deltaX < 0 || curY + step.deltaY < 0) return null;
            if (curX + step.deltaX >= mWidth || curY + step.deltaY >= mHeight) return null;

            List<Integer> arounds = null;
            if (step == Step.STRIKE) {
                if (strikeNb <= 0) return null;
                arounds = giantsAround();
                if (arounds == null) return null;
            }
            Node child = clone();
            child.mTransition = step;
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

        @Override
        public float heuristic() {
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
            return "{step=" + mTransition +
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

    private enum Step implements Transition{
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
