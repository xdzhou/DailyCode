package com.loic.codinGame.training;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.loic.codinGame.CodinGameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SkynetBridge implements CodinGameResolver<String> {
    private static final Logger LOG = LoggerFactory.getLogger(SkynetBridge.class);

    private int mMin;
    private String[] mLanes;
    private List<Command> commands;

    @Override
    public void before(Scanner in) {
        int M = in.nextInt(); // the amount of motorbikes to control
        mMin = in.nextInt(); // the minimum amount of motorbikes that must survive
        mLanes = new String[4];
        mLanes[0] = in.next();
        mLanes[1] = in.next();
        mLanes[2] = in.next();
        mLanes[3] = in.next();

        int S = in.nextInt(); // the motorbikes' speed
        int curX = 0;
        List<Integer> curY = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            curX = in.nextInt(); // x coordinate of the motorbike
            int Y = in.nextInt(); // y coordinate of the motorbike
            curY.add(Y);
            in.nextInt(); // indicates whether the motorbike is activated "1" or detroyed "0"
        }

        commands = getCommands(S, curX, curY);
    }

    @Override
    public String accept(Scanner in) {
        return commands.remove(0).toString();
    }

    public List<Command> getCommands(int curSpeed, int curX, List<Integer> curY) {
        List<Command> commands = new ArrayList<>();
        return find(curSpeed, curX, curY, commands);
    }

    private List<Command> find(int curSpeed, int curX, List<Integer> curY, List<Command> commands) {
        LOG.debug("speed: " + curSpeed + ", X: " + curX + ", Ys: " + curY + ", commands: " + commands);
        if (curY.size() >= mMin) {
            if (curX > mLanes[0].length()) {
                return commands;
            } else {
                List<Command> retVal = null;
                for (int i = 0; i <= 5 && retVal == null; i++) {
                    Command com = Command.values()[i];
                    if (com == Command.UP && curY.contains(0)) {
                        continue;
                    }
                    if (com == Command.DOWN && curY.contains(3)) {
                        continue;
                    }
                    if (com == Command.SLOW && curSpeed <= 1) {
                        continue;
                    }
                    commands.add(com);
                    int newSpeed;
                    if (com == Command.SPEED) {
                        newSpeed = curSpeed + 1;
                    } else if (com == Command.SLOW) {
                        newSpeed = curSpeed - 1;
                    } else {
                        newSpeed = curSpeed;
                    }

                    List<Integer> newY = applyCommand(curX, newSpeed, new ArrayList<>(curY), com);
                    retVal = find(newSpeed, curX + newSpeed, newY, commands);

                    if (retVal == null) {
                        commands.remove(commands.size() - 1);
                    }
                }
                return retVal;
            }
        }
        return null;
    }

    private List<Integer> applyCommand(int curX, int speed, List<Integer> curY, Command com) {
        Iterator<Integer> iterator = curY.iterator();
        while (iterator.hasNext()) {
            int y = iterator.next();
            if (hasHole(curX, y, speed, com)) {
                iterator.remove();
            }
        }
        if (com == Command.DOWN) {
            addDelta(curY, 1);
        }
        if (com == Command.UP) {
            addDelta(curY, -1);
        }
        return curY;
    }

    private void addDelta(List<Integer> curY, int delta) {
        if (curY != null && !curY.isEmpty()) {
            for (int i = 0; i < curY.size(); i++) {
                curY.set(i, curY.get(i) + delta);
            }
        }
    }

    private boolean hasHole(int x, int y, int speed, Command com) {
        if (com == Command.WAIT || com == Command.SPEED || com == Command.SLOW) {
            return hasHole(y, x + 1, x + speed);
        } else if (com == Command.JUMP) {
            return hasHole(y, x + speed, x + speed);
        } else if (com == Command.UP) {
            boolean retVal = hasHole(y, x + 1, x + speed - 1);
            if (!retVal) {
                retVal = hasHole(y - 1, x + 1, x + speed);
            }
            return retVal;
        } else if (com == Command.DOWN) {
            boolean retVal = hasHole(y, x + 1, x + speed - 1);
            if (!retVal) {
                retVal = hasHole(y + 1, x + 1, x + speed);
            }
            return retVal;
        }
        return false;
    }

    private boolean hasHole(int y, int startX, int endX) {
        if (startX <= endX) {
            int len = mLanes[y].length();
            if (endX >= len) {
                endX = len - 1;
            }
            if (startX >= len) {
                startX = len - 1;
            }
            for (int i = startX; i <= endX; i++) {
                if (mLanes[y].charAt(i) == '0') {
                    LOG.debug("has hole for y:" + y + ", startX:" + startX + ", endX:" + endX);
                    return true;
                }
            }
        }
        return false;
    }

    private enum Command {
        SPEED, SLOW, JUMP, WAIT, UP, DOWN
    }
}
