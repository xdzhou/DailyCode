package com.sky.codingame.training;

import com.loic.algo.search.MonteCarlo;

import java.util.*;

public class VoxCodei {

    public static void main(String args[]) {
        Maze maze = Maze.getInstance();
        MapNode root = new MapNode();
        MonteCarlo<MapNode, Integer> algo = new MonteCarlo<MapNode, Integer>();

        Scanner in = new Scanner(System.in);
        maze.mWidth = in.nextInt(); // width of the firewall grid
        maze.mHeight = in.nextInt(); // height of the firewall grid
        in.nextLine();
        for (int i = 0; i < maze.mHeight; i++) {
            String mapRow = in.nextLine(); // one line of the firewall grid
            for(int j = 0; j < mapRow.length(); j++) {
                char c = mapRow.charAt(j);
                if (c == '@') {
                    root.mSurveillances.add(i * maze.mWidth + j);
                } else if (c == '#') {
                    maze.mIndestructibles.add(i * maze.mWidth + j);
                }
            }
        }
        Collections.sort(root.mSurveillances);
        maze.mTotalPoint = root.mSurveillances.size();

        // game loop
        while (true) {
            int rounds = in.nextInt(); // number of rounds left before the end of the game
            int bombs = in.nextInt(); // number of bombs left

            root.mBombNb = bombs;
            algo.execute(root, 3000, 10);
            MapNode bestChild = algo.getBestChild(root);
            if (bestChild.getTransition() == -1) {
                System.out.println("WAIT");
            } else {
                int p = bestChild.mPutPosition;
                System.out.println(p/maze.mWidth + " " + p%maze.mWidth);
            }
            root = bestChild;
        }
    }

    private static class Maze {
        private int mHeight, mWidth;
        private List<Integer> mIndestructibles = new ArrayList<Integer>();
        private int mTotalPoint;

        private static Maze mInstance;

        public static Maze getInstance() {
            if (mInstance == null) mInstance = new Maze();
            return mInstance;
        }

        public void influenceZone(int position, IInfluenceListener listener) {
            int x = position / mWidth;
            int y = position % mWidth;
            short delta = 1;
            int index;
            //up
            while(delta <= 3) {
                index = getValidIndex(x - delta, y);
                if (index >= 0) listener.onInfluenced(index);
                else break;
                delta ++;
            }
            delta = 1;
            //down
            while(delta <= 3) {
                index = getValidIndex(x + delta, y);
                if (index >= 0) listener.onInfluenced(index);
                else break;
                delta ++;
            }
            delta = 1;
            //left
            while(delta <= 3) {
                index = getValidIndex(x, y - delta);
                if (index >= 0) listener.onInfluenced(index);
                else break;
                delta ++;
            }
            delta = 1;
            //right
            while(delta <= 3) {
                index = getValidIndex(x, y + delta);
                if (index >= 0) listener.onInfluenced(index);
                else break;
                delta ++;
            }
        }

        private int getValidIndex(int x, int y) {
            if (x >= 0 && x < mHeight && y >= 0 && y < mWidth) {
                int index = x * mWidth + y;
                if (! mIndestructibles.contains(index)) return index;
            }
            return -1;
        }

        @Override
        public String toString() {
            return "Maze{" +
                    "mHeight=" + mHeight +
                    ", mWidth=" + mWidth +
                    ", mIndestructibles=" + mIndestructibles +
                    ", mTotalPoint=" + mTotalPoint +
                    '}';
        }

        public interface IInfluenceListener {
            void onInfluenced(int position);
        }
    }

    private static class BombInfo implements Cloneable, Comparable<BombInfo> {
        private int mPosition;
        private int mTurnLeft = 3;

        public void oneTurn() {
            mTurnLeft --;
        }

        @Override
        public String toString() {
            return "{" +
                    "mPosition=" + mPosition +
                    ", mTurnLeft=" + mTurnLeft +
                    '}';
        }

        @Override
        public BombInfo clone() {
            try {
                return (BombInfo) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int compareTo(BombInfo o) {
            return mPosition - o.mPosition;
        }
    }

    private static class MapNode extends MonteCarlo.MonteCarloNode<Integer> implements Cloneable {
        private int mBombNb;
        private List<Integer> mSurveillances = new ArrayList<Integer>();
        private List<BombInfo> mBombs = new ArrayList<BombInfo>();
        //TODO
        private int mPutPosition = -1;

        @Override
        public float heuristic() {
            if (isLose()) {
                return 0;
            } else if(isWin()) {
                return 1;
            } else {
                Maze maze = Maze.getInstance();
                float wining = (maze.mTotalPoint - mSurveillances.size() / (float) maze.mTotalPoint);
                int totalSize = mSurveillances.size();
                float rate = 1.0f;

                MapNode self = clone();
                while (!self.mBombs.isEmpty()) {
                    self.applyTransition(-1);
                    rate *= 0.9f;
                    wining += rate * (totalSize - self.mSurveillances.size()) / (float) maze.mTotalPoint;
                    totalSize = self.mSurveillances.size();
                }
                if (self.mBombNb <= 0 && !self.mSurveillances.isEmpty()) {
                    return 0;
                } else {
                    return wining;
                }
            }
        }

        @Override
        protected void applyTransition(Integer transition) {
            Set<BombInfo> expositionBombs = new HashSet<BombInfo>();
            for(BombInfo bi : mBombs) {
                bi.oneTurn();
                if (bi.mTurnLeft == 0) expositionBombs.add(bi);
            }
            //System.err.println("expositionBombs : "+expositionBombs);
            if (expositionBombs.size() == 1) {
                explosionBomb(expositionBombs);
            } else if (expositionBombs.size() > 1) {
                System.err.println("IMPOSSIBLE bomb size : "+expositionBombs.size());
            }
            if (transition >= 0) {
                mPutPosition = transition;
                mBombNb--;
                BombInfo bi = new BombInfo();
                bi.mPosition = mPutPosition;
                addBomb(bi);
            }
        }

        private void putOneBomb() {
            if (mBombNb <= 0) System.err.println("IMPOSSIBLE, bomb count " +mBombNb);
            Maze config = Maze.getInstance();
            if (mSurveillances.isEmpty()) {
                //success, find a random position
                int size = config.mWidth * config.mHeight;
                for(int i = 0; i < size; i++) {
                    if (!config.mIndestructibles.contains(i) && binarySearchBomb(i) < 0) {
                        mPutPosition = i;
                        break;
                    }
                }
                System.err.println("random position " +mPutPosition);
            } else {
                final List<Integer> temp;
                if (!mBombs.isEmpty()) {
                    temp = new ArrayList<Integer>(mSurveillances);
                    for(BombInfo bi : mBombs) {
                        config.influenceZone(bi.mPosition, new Maze.IInfluenceListener() {
                            @Override
                            public void onInfluenced(int position) {
                                int index = Collections.binarySearch(temp, position);
                                if (index >= 0) temp.remove(index);
                            }
                        });
                    }
                } else {
                    temp = mSurveillances;
                }
                if (temp.isEmpty()) {
                    //use bombs as target
                    for(BombInfo bi : mBombs) {
                        temp.add(bi.mPosition);
                    }
                }
                final Map<Integer, Integer> candidature = new HashMap<Integer, Integer>();
                for(int p : temp) {
                    config.influenceZone(p, new Maze.IInfluenceListener() {
                        @Override
                        public void onInfluenced(int position) {
                            if (Collections.binarySearch(mSurveillances, position) < 0 && binarySearchBomb(position) < 0) {
                                Integer count = candidature.get(position);
                                if (count == null) candidature.put(position, 1);
                                else candidature.put(position, count + 1);
                            }
                        }
                    });
                }
                //System.err.println("candidature : " + candidature);
                List<Integer> maxCounts = new ArrayList<Integer>();
                int maxCount = 0;
                for(Map.Entry<Integer, Integer> entry : candidature.entrySet()) {
                    if (entry.getValue() == maxCount) {
                        maxCounts.add(entry.getKey());
                    } else if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        maxCounts.clear();
                        maxCounts.add(entry.getKey());
                    }
                }
                //System.err.println("maxCounts : " + maxCounts);
                if (maxCounts.size() == 0) {
                    System.err.println("IMPOSSIBLE maxCount size : 0");
                } else if (maxCounts.size() == 1) {
                    mPutPosition = maxCounts.get(0);
                } else {
                    //TODO
                    mPutPosition = maxCounts.get(0);
                }
            }
            mBombNb--;
            BombInfo bi = new BombInfo();
            bi.mPosition = mPutPosition;
            addBomb(bi);
        }

        private void addBomb(BombInfo bi) {
            if(mBombs.isEmpty()) {
                mBombs.add(bi);
            } else {
                mBombs.add(~binarySearchBomb(bi.mPosition), bi);
            }
        }

        private void explosionBomb(final Set<BombInfo> expositionBombs) {
            Maze config = Maze.getInstance();
            while (!expositionBombs.isEmpty()) {
                //System.err.println("explosions List : "+expositionBombs);
                Iterator<BombInfo> iterator = expositionBombs.iterator();
                final BombInfo bi = iterator.next();
                iterator.remove();
                mBombs.remove(bi);
                //System.err.println("explosion for "+bi);
                config.influenceZone(bi.mPosition, new Maze.IInfluenceListener() {
                    @Override
                    public void onInfluenced(int position) {
                        //check surveillance
                        int index = Collections.binarySearch(mSurveillances, position);
                        if (index >= 0) mSurveillances.remove(index);
                        //check bomb
                        index = binarySearchBomb(position);
                        if (index >= 0) expositionBombs.add(mBombs.get(index));
                    }
                });
            }
        }

        private int binarySearchBomb(int position) {
            int low = 0;
            int high = mBombs.size() - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                BombInfo midVal = mBombs.get(mid);
                int cmp = midVal.mPosition - position;
                if (cmp < 0)
                    low = mid + 1;
                else if (cmp > 0)
                    high = mid - 1;
                else
                    return mid; // key found
            }
            return ~low; // key not found.
        }

        @Override
        public boolean isOver() {
            return isLose() || isWin();
        }

        private boolean isLose() {
            return mBombNb == 0 && mBombs.size() == 0 && mSurveillances.size() > 0;
        }

        private boolean isWin() {
            return mSurveillances.isEmpty();
        }

        @Override
        public List<Integer> getPossibleTransitions() {
            Maze maze = Maze.getInstance();
            if (mBombNb > 0) {
                final Set<Integer> set = new HashSet<Integer>();
                for(int p : mSurveillances) {
                    maze.influenceZone(p, new Maze.IInfluenceListener() {
                        @Override
                        public void onInfluenced(int position) {
                            if (Collections.binarySearch(mSurveillances, position) < 0 && binarySearchBomb(position) < 0) {
                                set.add(position);
                            }
                        }
                    });
                }
                List<Integer> result = new ArrayList<Integer>(set);
                result.remove(mSurveillances);
                if (!mBombs.isEmpty()) result.add(-1);
                return result;
            } else {
                return Arrays.asList(-1);
            }
        }

        @Override
        protected MapNode clone(){
            MapNode n = (MapNode) super.clone();
            n.mSurveillances = new ArrayList<Integer>(mSurveillances);
            n.mBombs = new ArrayList<BombInfo>(mBombs.size());
            for(BombInfo bi : mBombs) {
                n.mBombs.add(bi.clone());
            }
            n.mPutPosition = -1;
            return n;
        }

        @Override
        public String toString() {
            return super.toString() +
                    ",BombNb=" + mBombNb +
                    ", Surveillances=" + mSurveillances +
                    ", Bombs=" + mBombs +
                    ", PutPosition=" + mPutPosition +
                    ", Step=" + getTransition();
        }
    }
}
