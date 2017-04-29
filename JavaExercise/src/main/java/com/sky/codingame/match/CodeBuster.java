package com.sky.codingame.match;

import java.util.*;

import com.loic.algo.array.ArrayUtils;

public class CodeBuster {

    private static final int HEIGHT = 9001;
    private static final int WIDTH = 16001;
    private final int mTeamId;
    private final List<Ghost> mGhosts;
    private final List<Buster> mMyBusters;
    private final List<Buster> mOtherBusters;
    private final GameInfo mGameInfo;
    private final Coord mBasePosition;
    private final Coord mOpponentBasePosition;
    private final Coord[] mZoneCenters = new Coord[18];
    private final boolean[] mZonePassed = new boolean[18];
    private Random mRandom = new Random(new Date().getTime());
    private int mDefenderCount = 0;
    public CodeBuster(int teamId, int busterCount, int ghostCount) {
        mTeamId = teamId;
        mMyBusters = new ArrayList<>(busterCount);
        mOtherBusters = new ArrayList<>(busterCount);
        mGhosts = new ArrayList<>(ghostCount);
        mGameInfo = new GameInfo();
        mGameInfo.mGhostCount = ghostCount;

        if (mTeamId == 0) {
            mBasePosition = new Coord(0, 0);
            mOpponentBasePosition = new Coord(WIDTH - 1, HEIGHT - 1);
        } else {
            mBasePosition = new Coord(WIDTH - 1, HEIGHT - 1);
            mOpponentBasePosition = new Coord(0, 0);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                int x = 1500 + j * 3000;
                if (j == 5) x = 15500;
                mZoneCenters[i * 6 + j] = new Coord(x, 1500 + i * 3000);
            }
        }
        if (mTeamId == 0) mZonePassed[0] = true;
        else mZonePassed[mZonePassed.length - 1] = true;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        int bustersPerPlayer = in.nextInt(); // the amount of busters you control
        int ghostCount = in.nextInt(); // the amount of ghosts on the map
        int myTeamId = in.nextInt(); // if this is 0, your base is on the top left of the map, if it is one, on the bottom right

        CodeBuster player = new CodeBuster(myTeamId, bustersPerPlayer, ghostCount);

        // game loop
        while (true) {
            int entities = in.nextInt(); // the number of busters and ghosts visible to you
            player.beforeRefresh();
            for (int i = 0; i < entities; i++) {
                int entityId = in.nextInt(); // buster id or ghost id
                int x = in.nextInt();
                int y = in.nextInt(); // position of this buster / ghost
                int entityType = in.nextInt(); // the team id if it is a buster, -1 if it is a ghost.
                int state = in.nextInt(); // For busters: 0=idle, 1=carrying a ghost.
                int value = in.nextInt(); // For busters: Ghost id being carried. For ghosts: number of busters attempting to trap this ghost.
                player.refresh(entityId, x, y, entityType, state, value);
            }
            player.afterRefresh();

            player.process();
        }
    }

    private void onPointSearched(Coord other) {
        for (int i = 0; i < mZoneCenters.length; i++) {
            Coord p = mZoneCenters[i];
            if (p.getDis(other) < 111) {
                mZonePassed[i] = true;
                System.err.println("PASS zone " + i + ", " + p);
                break;
            }
        }
    }

    private void checkIndex(int x, int y, int from, List<Integer> list) {
        if (x >= 0 && x < 3 && y >= 0 && y < 6) {
            int value = x * 6 + y;
            if (ArrayUtils.binarySearch(list, from, list.size(), value) < 0) {
                list.add(value);
            }
        }
    }

    private Buster getBusterById(int id, int team) {
        List<Buster> searchList = (team == mTeamId) ? mMyBusters : mOtherBusters;
        for (Buster buster : searchList) {
            if (buster.mId == id) return buster;
        }
        Buster b = new Buster(id);
        searchList.add(b);
        return b;
    }

    private Ghost getGhostById(int id) {
        for (Ghost ghost : mGhosts) {
            if (ghost.mId == id) return ghost;
        }
        Ghost ghost = new Ghost(id);
        mGhosts.add(ghost);
        return ghost;
    }

    private void removeGhostById(int id) {
        Iterator<Ghost> iterator = mGhosts.iterator();
        while (iterator.hasNext()) {
            Ghost ghost = iterator.next();
            if (ghost.mId == id) {
                iterator.remove();
                break;
            }
        }
    }

    private void beforeRefresh() {
        for (Ghost ghost : mGhosts) {
            ghost.mInMemory = true; // last turn's ghost, we remember it
        }
    }

    private void refresh(int entityId, int x, int y, int type, int state, int value) {
        Entity entity;
        if (type == -1) {
            Ghost ghost = getGhostById(entityId);
            ghost.mInMemory = false; //real ghost in this turn
            ghost.mStamina = state;
            ghost.mTrapCount = value;
            entity = ghost;
        } else if (type == 0 || type == 1) {
            Buster buster = getBusterById(entityId, type);
            buster.mChargeTurn++;
            buster.mState = state;
            buster.mValue = value;
            if (type != mTeamId) {
                int trustValue;
                if (buster.mState == 2) trustValue = value;
                else if (buster.mState == 3) trustValue = 9;
                else trustValue = 4;
                if (trustValue < 3) trustValue = 3;
                buster.updateTrust(trustValue);
            }
            entity = buster;
        } else {
            throw new RuntimeException("of entity for type " + type + " ???");
        }
        entity.mPosition.reset(x, y);
        if ((entity instanceof Buster) && mMyBusters.contains(entity)) {
            onPointSearched(entity.mPosition);
        }
    }

    private void afterRefresh() {
        if (mGameInfo.mBaseCaptureInfluence > 0) mGameInfo.mBaseCaptureInfluence--;
        Iterator<Buster> iterator = mOtherBusters.iterator();
        while (iterator.hasNext()) {
            Buster b = iterator.next();
            b.dishonesty();
            if (b.mTrustValue <= 0) iterator.remove();
            if (b.getDis(mBasePosition) < 2500) {
                mGameInfo.mBaseCaptureInfluence = 6;
            }
        }
        //filter the ghost in memory
        Iterator<Ghost> ghostIterator = mGhosts.iterator();
        while (ghostIterator.hasNext()) {
            Ghost ghost = ghostIterator.next();
            if (ghost.mInMemory) {
                boolean closeToMyBuster = false;
                for (int i = 0; i < mMyBusters.size() && !closeToMyBuster; i++) {
                    if (ghost.getDis(mMyBusters.get(i)) < 2200) {
                        closeToMyBuster = true;
                    }
                }
                if (closeToMyBuster) ghostIterator.remove();
            }
        }

        mGameInfo.newTurnStart();
    }

    private String releaseGhost(Buster buster) {
        System.err.println(buster + " need release A ghost " + buster.mValue);
        if (buster.getDis(mBasePosition) > 1600) {
            if (!mOtherBusters.isEmpty()) {
                for (Buster b : mOtherBusters) {
                    if (b.mState != 2 && buster.getDis(b) < 2500) {
                        Coord p = buster.awayFrom(b);
                        System.err.println("Find opponent buster nearby, go away...");
                        return p.moveToMe();
                    }
                }
            }
            if (mGameInfo.mTurnRest <= 50 && mGameInfo.mGhostCaught + mGameInfo.mGhostCarring >= mGameInfo.mGhostCount / 2) {
                boolean hasObstacle = false;
                Coord coord = new Coord(0, 9000);
                for (Buster b : mOtherBusters) {
                    if (b.getDis(coord) < 2500) {
                        hasObstacle = true;
                        break;
                    }
                }
                if (!hasObstacle) {
                    return coord.moveToMe();
                } else {
                    return "MOVE 16000 0";
                }
            }
            return mBasePosition.moveToMe();
        } else {
            throw new RuntimeException("Ghost no released !!!");
        }
    }

    private String trapGhost(Buster buster, Ghost ghost) {
        float dis = buster.getDis(ghost);
        System.err.println(buster + " will trap " + ghost);
        if (dis <= 1760 && dis >= 900) {
            return "BUST " + ghost.mId;
        } else if (dis > 1760) {
            return ghost.moveToMe();
        } else {
            Coord extendPoint = buster.mPosition.extendTo(ghost.mPosition, dis - 1330);
            return extendPoint.moveToMe();
        }
    }

    private int getUnSearchedPoint(Buster buster, List<Integer> toSearchIndex) {
        int line = buster.mPosition.y / 3000;
        if (line > 2) line = 2;
        int col = buster.mPosition.x / 3000;
        int index = line * 6 + col;
        if (!mZonePassed[index] && !toSearchIndex.contains(index)) {
            if (buster.getDis(mZoneCenters[index]) < 111) {
                System.err.println("ERROR getUnSearchedPoint");
            }
            toSearchIndex.add(index);
            return index;
        } else { //search autour
            List<Integer> list = new ArrayList<>();
            list.add(index);
            while (!list.isEmpty()) {
                int size = list.size();
                while (!list.isEmpty() && size > 0) {
                    int value = list.remove(0);
                    size--;
                    if (!mZonePassed[value] && !toSearchIndex.contains(value)) {
                        toSearchIndex.add(value);
                        return value;
                    }
                    int x = value / 6;
                    int y = value % 6;
                    if (x <= line) checkIndex(x - 1, y, size, list);
                    if (y <= col) checkIndex(x, y - 1, size, list);
                    if (y >= col) checkIndex(x, y + 1, size, list);
                    if (x >= line) checkIndex(x + 1, y, size, list);
                }
            }
        }
        System.err.println("ERROR can't find unSearch point");
        return -1;
    }

    private String searchGhost(Buster buster, List<Integer> toSearchIndex) {
        int searchIndex = getUnSearchedPoint(buster, toSearchIndex);
        if (searchIndex < 0) throw new RuntimeException("ERROR : no zone to search...");
        Coord p = mZoneCenters[searchIndex];
        System.err.println(buster + " will search ghost at point " + p);
        return "MOVE " + p.x + " " + p.y;
    }

    private String trapOtherBuster(Buster myBuster, Buster otherBuster) {
        System.err.println(myBuster + " will trapOtherBuster \n: " + otherBuster);
        if (myBuster.mChargeTurn >= 20) {
            myBuster.mChargeTurn = 0;
            return "STUN " + otherBuster.mId;
        } else {
            return otherBuster.moveToMe();
        }
    }

    private boolean hasGhostAtSamePosition(Buster buster) {
        for (Ghost ghost : mGhosts) {
            if (ghost.isSamePosition(buster)) return true;
        }
        return false;
    }

    private int getUnSearchZoneCount() {
        int count = 0;
        for (boolean b : mZonePassed) {
            if (!b) count++;
        }
        return count;
    }

    private void printMap(List<Integer> toSearchIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (toSearchIndex.contains(index)) {
                    sb.append('M');
                    if (mZonePassed[index]) throw new RuntimeException("ERROR is true ????");
                } else if (mZonePassed[index]) sb.append('O');
                else sb.append('X');
            }
            sb.append('\n');
        }
        System.err.println(sb.toString());
    }

    private void analyse() {
        for (Buster b : mMyBusters) {
            if (b.mState == 1) {
                mGameInfo.mGhostCarring++;
                if (mGameInfo.mBaseCaptureInfluence > 0) {
                    mGameInfo.mNeedHelpBuster = b;
                    continue;
                }
                if (b.getDis(mBasePosition) > 1600) {
                    //Collections.sort(mOtherBusters, new EntityComparator(b));
                    mOtherBusters.stream().sorted((b1, b2) -> (Float.compare(b.getDis(b1), b.getDis(b2))));
                    Iterator<Buster> iterator = mOtherBusters.iterator();
                    while (iterator.hasNext()) {
                        Buster otherBuster = iterator.next();
                        if (Math.abs(otherBuster.mState) == 2) continue;
                        float dis = b.getDis(otherBuster);
                        if (dis <= 2200) {
                            mGameInfo.mNeedHelpBuster = b;
                            System.err.println("HELP for " + b);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void process() {
        System.err.println(mGameInfo);
        System.err.println("MyBusters:" + mMyBusters);
        System.err.println("OtherBusters:" + mOtherBusters);
        System.err.println("Ghosts:" + mGhosts);

        analyse();

        int count = getUnSearchZoneCount();
        List<Integer> toSearchIndex = new ArrayList<>();
        mDefenderCount = 0;
        for (int i = 0; i < mMyBusters.size(); i++) {
            System.out.println(treat(mMyBusters.get(i), i, toSearchIndex, count));
        }

        mGameInfo.mCloseOpponent.clear();
        for (int i = 0; i < mMyBusters.size(); i++) {
            Buster myBuster = mMyBusters.get(i);
            if (myBuster.mState == 2) continue;
            Buster single = null;
            for (Buster b : mOtherBusters) {
                if (myBuster.getDis(b) < 1760) {
                    if (single == null) single = b;
                    else {
                        single = null;
                        break;
                    }
                }
            }
            if (single != null) {
                mGameInfo.mCloseOpponent.put(myBuster, single);
            }
        }

        //printMap(toSearchIndex);
    }

    private String treat(Buster buster, int index, List<Integer> toSearchIndex, int unSearchCount) {
        //stunned
        if (buster.mState == 2) {
            if (mGameInfo.mCloseOpponent.containsKey(buster)) {
                mGameInfo.mCloseOpponent.remove(buster).mChargeTurn = 1;
            }
            return buster.moveToMe();
        }
        if (buster.mState == 1 && buster.getDis(mBasePosition) <= 1600) {
            mGameInfo.mGhostCaught++;
            mGameInfo.mLastBustCount++;
            removeGhostById(buster.mValue);
            return "RELEASE";
        }
        //stun opponent buster
        if (!mOtherBusters.isEmpty()) {
            Collections.sort(mOtherBusters, new EntityComparator(buster));
            Iterator<Buster> iterator = mOtherBusters.iterator();
            while (iterator.hasNext()) {
                Buster otherBuster = iterator.next();
                if (Math.abs(otherBuster.mState) == 2) continue;
                float dis = buster.getDis(otherBuster);
                if (dis <= 1760) {
                    if (buster.mChargeTurn >= 20) {
                        otherBuster.mState = -2;
                        buster.mChargeTurn = 0;
                        System.err.println("Find opponent buster, Stun it...");
                        return "STUN " + otherBuster.mId;
                    } else if (buster.mState == 1 && otherBuster.mChargeTurn > 15) {
                        Coord p = buster.awayFrom(otherBuster);
                        System.err.println("Find opponent buster too close, go away...");
                        return p.moveToMe();
                    }
                } else if (dis < 2600 && otherBuster.mChargeTurn > 15) {
                    if (buster.mState == 1) {
                        Coord p = buster.awayFrom(otherBuster);
                        System.err.println("I carry ghost & Find opponent buster nearby, go away...");
                        return p.moveToMe();
                    }
                } else {
                    break;
                }
            }
        }
        //releasing & busting
        if (buster.mState == 1) return releaseGhost(buster);

        //catch opponent buster
        if (!mOtherBusters.isEmpty()) {
            Collections.sort(mOtherBusters, new EntityComparator(buster));
            int curIndex = 0;
            while (curIndex < mOtherBusters.size()) {
                Buster otherBuster = mOtherBusters.get(curIndex);
                if (buster.getDis(otherBuster) <= 1760) {
                    if (hasGhostAtSamePosition(buster)) return trapOtherBuster(buster, otherBuster);
                    else if (otherBuster.mState == 1 && (otherBuster.getDis(mOpponentBasePosition) / 800f - 2 + buster.mChargeTurn >= 20)) {
                        return trapOtherBuster(buster, otherBuster);
                    }
                }
                curIndex++;
            }
        }
        //help our buster
        if (mGameInfo.mNeedHelpBuster != null) {
            System.err.println(buster + " will help " + mGameInfo.mNeedHelpBuster + ", move to it");
            return mGameInfo.mNeedHelpBuster.moveToMe();
        }
        //check trap ghost
        if (!mGhosts.isEmpty()) {
            Collections.sort(mGhosts, new GhostComparator(buster));
            Ghost closeGhost = mGhosts.get(0);
            float cost = buster.getBustCost(closeGhost);
            float dis = buster.getDis(closeGhost);
            /*
            boolean inTime = true;
            if (closeGhost.mTrapCount > 0 && dis > 1760) {
                inTime = closeGhost.mStamina / (float) closeGhost.mTrapCount > (dis - 900) / 800f;
            }
            */
            if (cost <= mGameInfo.mAcceptCost || closeGhost.mStamina == 0) {
                return trapGhost(buster, closeGhost);
            } else {
                System.err.println("Not enough time or Give up trapping ghost, cost " + cost + ", acceptCost " + mGameInfo.mAcceptCost);
            }
        } else if (!mOtherBusters.isEmpty()) {
            for (Buster b : mOtherBusters) {
                if (b.mState == 3 && b.mTrustValue >= 8) {
                    return b.moveToMe();
                }
            }
        }
        //search map
        if (unSearchCount - toSearchIndex.size() > 0) {
            return searchGhost(buster, toSearchIndex);
        }
        //defend last ghost
        if (mGameInfo.mGhostCarring == 1 && mGameInfo.mGhostCaught + 1 >= mGameInfo.mGhostCount / 2) {
            Buster carringBust = null;
            for (Buster b : mMyBusters) {
                if (b.mState == 1) {
                    carringBust = b;
                    break;
                }
            }
            System.err.println("defend last ghost for Buster : " + carringBust);
            if (carringBust != null) return carringBust.moveToMe();
        }
        if (mDefenderCount >= 2) {
            if (buster.mPosition.getDis(0, 9000) < buster.mPosition.getDis(16000, 0)) {
                return "MOVE 0 9000";
            } else {
                return "MOVE 16000 0";
            }
        } else {
            //move to opponent's base
            float dis = buster.getDis(mOpponentBasePosition);
            if (dis <= 2600) {
                mDefenderCount++;
                double curAngle = mRandom.nextFloat() * Math.PI / 2;
                int dx = (int) (2500 * Math.cos(curAngle));
                int dy = (int) (2500 * Math.sin(curAngle));
                if (mTeamId == 0) {
                    dx = 16000 - dx;
                    dy = 9000 - dy;
                }
                return "MOVE " + dx + " " + dy;
            } else {
                return mOpponentBasePosition.moveToMe();
            }
        }
    }

    private static class Ghost extends Entity {
        private int mStamina;
        private int mTrapCount;

        private boolean mInMemory = false;

        public Ghost(int id) {
            mId = id;
        }

        @Override
        public String toString() {
            return "Ghost[" + mId + ", Stamina " + mStamina + ", trapCount" + mTrapCount + "]";
        }
    }

    private abstract static class Entity {
        protected int mId;   // entity id
        protected Coord mPosition = new Coord();  // position

        public float getDis(Entity other) {
            return mPosition.getDis(other.mPosition);
        }

        public float getDis(Coord other) {
            return mPosition.getDis(other);
        }

        public String moveToMe() {
            return mPosition.moveToMe();
        }

        public boolean isSamePosition(Entity e) {
            return mPosition.x == e.mPosition.x && mPosition.y == e.mPosition.y;
        }
    }

    private static class Coord {
        int x, y;

        public Coord() {
            this(0, 0);
        }

        public Coord(int x, int y) {
            reset(x, y);
        }

        public void reset(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public float getDis(Coord other) {
            return getDis(other.x, other.y);
        }

        public float getDis(float dx, float dy) {
            return (float) Math.sqrt((x - dx) * (x - dx) + (y - dy) * (y - dy));
        }

        public String moveToMe() {
            return "MOVE " + x + " " + y;
        }

        public Coord extendTo(Coord direction, float len) {
            return extendTo(direction, len, false);
        }

        public Coord extendTo(Coord direction, float len, boolean nullable) {
            float rate = len / getDis(direction);
            int dx, dy;
            dx = x + (int) ((direction.x - x) * rate);
            dy = y + (int) ((direction.y - y) * rate);
            if (nullable && (dx < 0 || dx >= WIDTH || dy < 0 || dy >= HEIGHT)) {
                return null;
            }
            if (dx < 0) dx = 0;
            if (dx >= WIDTH) dx = WIDTH - 1;
            if (dy < 0) dy = 0;
            if (dy >= HEIGHT) dy = HEIGHT - 1;
            return new Coord(dx, dy);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private static class GameInfo {
        //current turn info
        private int mAcceptCost = 10;
        private int mTurnRest = 401;
        private int mGhostCount;
        private int mGhostCaught;
        private Buster mNeedHelpBuster;
        private int mGhostCarring;
        private Map<Buster, Buster> mCloseOpponent = new HashMap<>();
        private int mBaseCaptureInfluence;

        //last turn info
        private int mLastBustCount = 0;

        public void newTurnStart() {
            mTurnRest--;
            mAcceptCost += (mLastBustCount == 0 ? 1f : 0.4f);
            mLastBustCount = 0;
            mNeedHelpBuster = null;
            mGhostCarring = 0;
        }

        @Override
        public String toString() {
            return "{AcceptCost=" + mAcceptCost +
                    ", TurnRest=" + mTurnRest +
                    ", GhostCount=" + mGhostCount +
                    ", GhostCaught=" + mGhostCaught +
                    ", NeedHelpBuster=" + mNeedHelpBuster +
                    ", LastBustCount=" + mLastBustCount +
                    ", GhostCarring=" + mGhostCarring +
                    '}';
        }
    }

    private static class EntityComparator implements Comparator<Entity> {
        private final Entity mFrom;

        public EntityComparator(Entity e) {
            mFrom = e;
        }

        @Override
        public int compare(Entity o1, Entity o2) {
            return Float.compare(mFrom.getDis(o1), mFrom.getDis(o2));
        }
    }

    private static class GhostComparator implements Comparator<Ghost> {
        private final Buster mFrom;

        public GhostComparator(Buster b) {
            mFrom = b;
        }

        @Override
        public int compare(Ghost o1, Ghost o2) {
            return Float.compare(mFrom.getBustCost(o1), mFrom.getBustCost(o2));
        }
    }

    private class Buster extends Entity {
        private int mState;
        private int mValue;

        private int mChargeTurn = 20;
        private int mTrustValue = 10;

        public Buster(int id) {
            mId = id;
        }

        public void updateTrust(int value) {
            mTrustValue = value;
        }

        public void dishonesty() {
            mTrustValue--;
        }

        public float getBustCost(Ghost ghost) {
            return (getDis(ghost) + ghost.getDis(mBasePosition)) / 800f + ghost.mStamina;
        }

        public Coord awayFrom(Buster b) {
            Coord coord = mPosition.extendTo(b.mPosition, -800);
            if (getDis(coord) <= 799) {
                /*
                if (coord.x == 0 || coord.x == 16000) {
                    float deltaY = (float) Math.sqrt(800 * 800 - (coord.x - mPosition.x) * (coord.x - mPosition.x));
                    if (b.mPosition.getDis(coord.x, mPosition.y + deltaY) > b.mPosition.getDis(coord.x, mPosition.y - deltaY)) {
                        coord.y = (int) (mPosition.y + deltaY);
                    } else {
                        coord.y = (int) (mPosition.y - deltaY);
                    }
                } else {
                    float deltaX = (float) Math.sqrt(800 * 800 - (coord.y - mPosition.y) * (coord.y - mPosition.y));
                    if (b.mPosition.getDis(mPosition.x + deltaX, coord.y) > b.mPosition.getDis(mPosition.x - deltaX, coord.y)) {
                        coord.x = (int) (mPosition.x + deltaX);
                    } else {
                        coord.x = (int) (mPosition.x - deltaX);
                    }
                }
                */
                coord.x = mBasePosition.x;
                coord.y = mBasePosition.y;
            }
            return coord;
        }

        @Override
        public String toString() {
            return "Buster[" + mId + ", state " + mState + ", value " + mValue +
                    ", charge " + mChargeTurn + ", trust " + mTrustValue + ", " + mPosition + "]";
        }
    }
}