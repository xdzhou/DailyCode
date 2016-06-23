package com.sky.codingame.training;

import com.loic.algo.search.BruteForce;

import java.util.*;

public class ThorVSGiants {
    private static final int mHeight = 18;
    private static final int mWidth = 40;

    public static void main(String args[]) {
        Maze maze = Maze.getInstance();
        MapNode root = new MapNode();
        BruteForce<MapNode, Step> algo = new BruteForce<>();

        Scanner in = new Scanner(System.in);
        root.curX = in.nextInt();
        root.curY = in.nextInt();

        // game loop
        while (true) {
            int H = in.nextInt(); // the remaining number of hammer strikes.
            int N = in.nextInt(); // the number of giants which are still present on the map.
            if (maze.mTotalGiant < 0) maze.mTotalGiant = N;
            root.round = 0;
            root.strikeNb = H;
            root.giants.clear();
            for (int i = 0; i < N; i++) {
                int X = in.nextInt();
                int Y = in.nextInt();
                root.giants.add(X * mHeight + Y);
            }
            algo.execute(root, 5);
            String nextStep = (root.getBestChild() != null) ? root.getBestChild().getTransition().toString() : "STRIKE";
            root = (MapNode) root.getBestChild();
            System.out.println(nextStep);
        }
    }

    private static class Maze {
        private int mTotalGiant = -1;

        private static Maze mInstance;

        public static Maze getInstance() {
            if (mInstance == null) mInstance = new Maze();
            return mInstance;
        }
    }

    private static class MapNode extends BruteForce.BruteForceNode<Step> implements Cloneable {
        private int round = 0;
        private int strikeNb;
        private int curX, curY;
        private List<Integer> giants = new ArrayList<>();

        @Override
        public boolean isOver() {
            return isLose() || isWin();
        }

        @Override
        public List<Step> getPossibleTransitions() {
            List<Step> result = Arrays.asList(Step.values());
            Iterator<Step> iterator = result.iterator();
            while (iterator.hasNext()) {
                Step step = iterator.next();
                if (curX + step.deltaX < 0 || curY + step.deltaY < 0) iterator.remove();
                if (curX + step.deltaX >= mWidth || curY + step.deltaY >= mHeight) iterator.remove();

                if (step == Step.STRIKE) {
                    if (strikeNb <= 0) iterator.remove();
                    else if (giantsAround() == null) iterator.remove();
                }
            }
            return result;
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

        //return next child MapNode
        @Override
        public void applyTransition(Step step) {
            List<Integer> arounds = giantsAround();
            mTransition = step;
            round = round + 1;
            curX += step.deltaX;
            curY += step.deltaY;
            if (arounds != null) {
                System.err.println("strike giants : "+arounds.size());
                giants.removeAll(arounds);
                strikeNb --;
            }
            moveGiants();
        }

        private List<Integer> giantsAround() {
            List<Integer> retVal = new ArrayList<>();
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
                fitness = (Maze.getInstance().mTotalGiant - giants.size()) * maxValue;
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

        @Override
        protected MapNode clone() {
            MapNode n = (MapNode) super.clone();
            n.giants = new ArrayList<>(giants);
            return n;
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
