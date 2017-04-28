package com.sky.OOP;

import java.util.Arrays;

import com.loic.algo.common.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RubikCube {
    private static final Logger Log = LoggerFactory.getLogger(RubikCube.class);
    private SubCube[] mSubCubes;

    public RubikCube() {
        mSubCubes = new SubCube[26];
    }

    public static RubikCube getPrefectRubikCube() {
        RubikCube rubikCube = new RubikCube();
        // top:yellow bellow:red left:green right:orange front:blue behind:white
        Pair<Color, Side> topPair = Pair.create(Color.Yellow, Side.Top);
        Pair<Color, Side> bellowPair = Pair.create(Color.Red, Side.Bellow);
        Pair<Color, Side> leftPair = Pair.create(Color.Green, Side.Left);
        Pair<Color, Side> rightPair = Pair.create(Color.Orange, Side.Right);
        Pair<Color, Side> frontPair = Pair.create(Color.Blue, Side.Front);
        Pair<Color, Side> behindPair = Pair.create(Color.White, Side.Behind);
        // top level 3
        rubikCube.mSubCubes[0] = new SubCube(topPair.clone(), leftPair.clone(), frontPair.clone());
        rubikCube.mSubCubes[1] = new SubCube(topPair.clone(), leftPair.clone(), behindPair.clone());
        rubikCube.mSubCubes[2] = new SubCube(topPair.clone(), frontPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[3] = new SubCube(topPair.clone(), behindPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[4] = new SubCube(topPair.clone(), frontPair.clone());
        rubikCube.mSubCubes[5] = new SubCube(topPair.clone(), leftPair.clone());
        rubikCube.mSubCubes[6] = new SubCube(topPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[7] = new SubCube(topPair.clone(), behindPair.clone());
        rubikCube.mSubCubes[8] = new SubCube(topPair.clone());
        // top level 2
        rubikCube.mSubCubes[9] = new SubCube(frontPair.clone(), leftPair.clone());
        rubikCube.mSubCubes[10] = new SubCube(frontPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[11] = new SubCube(behindPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[12] = new SubCube(behindPair.clone(), leftPair.clone());
        rubikCube.mSubCubes[13] = new SubCube(frontPair.clone());
        rubikCube.mSubCubes[14] = new SubCube(behindPair.clone());
        rubikCube.mSubCubes[15] = new SubCube(leftPair.clone());
        rubikCube.mSubCubes[16] = new SubCube(rightPair.clone());
        // top level 1
        rubikCube.mSubCubes[17] = new SubCube(bellowPair.clone(), leftPair.clone(), frontPair.clone());
        rubikCube.mSubCubes[18] = new SubCube(bellowPair.clone(), leftPair.clone(), behindPair.clone());
        rubikCube.mSubCubes[19] = new SubCube(bellowPair.clone(), frontPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[20] = new SubCube(bellowPair.clone(), behindPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[21] = new SubCube(bellowPair.clone(), frontPair.clone());
        rubikCube.mSubCubes[22] = new SubCube(bellowPair.clone(), leftPair.clone());
        rubikCube.mSubCubes[23] = new SubCube(bellowPair.clone(), rightPair.clone());
        rubikCube.mSubCubes[24] = new SubCube(bellowPair.clone(), behindPair.clone());
        rubikCube.mSubCubes[25] = new SubCube(bellowPair.clone());
        return rubikCube;
    }

    public boolean isRubikCubePrefect() {
        for (Side side : Side.values()) {
            SubCube[] subCubes = getSubCubesForSide(side);
            Color color = subCubes[0].getColorForSide(side);
            Log.debug("check side : {}, color : {}", side, color);
            for (int i = 1; i < subCubes.length; i++) {
                if (color != subCubes[i].getColorForSide(side)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clockwiseRotate(Direction dir, int level, int turnCount) {
        Log.debug("clockwise Rotate in direction: {}, for level: {}, turn count: {}", dir, level, turnCount);
        SubCube[] cubesToRotateCubes = getSubcubeFrom(dir, level);
        turnCount = turnCount % 4;
        Side[] targetSides = dir.getTargetSides(turnCount);
        Log.debug("change direction from {} to {}", Arrays.toString(dir.transformOrder), Arrays.toString(targetSides));
        for (SubCube cube : cubesToRotateCubes) {
            cube.transform(dir.transformOrder, targetSides);
        }
    }

    private SubCube[] getSubCubesForSide(Side side) {
        SubCube[] retVal = new SubCube[9];
        int curIndex = 0;
        for (SubCube cube : mSubCubes) {
            if (curIndex >= retVal.length) {
                break;
            }
            if (cube.isPresentIn(side)) {
                retVal[curIndex++] = cube;
            }
        }
        return retVal;
    }

    private SubCube[] getSubcubeFrom(Direction dir, int level) {
        SubCube[] retVal = new SubCube[level == 2 ? 8 : 9];
        int curIndex = 0;
        for (SubCube cube : mSubCubes) {
            if (curIndex >= retVal.length) {
                break;
            }
            switch (level) {
                case 1:
                    if (cube.isPresentIn(dir.relativeSide.getOppisiteSide())) {
                        retVal[curIndex++] = cube;
                    }
                    break;

                case 2:
                    if (!cube.isPresentIn(dir.relativeSide) && !cube.isPresentIn(dir.relativeSide.getOppisiteSide())) {
                        retVal[curIndex++] = cube;
                    }
                    break;
                default:
                    if (cube.isPresentIn(dir.relativeSide)) {
                        retVal[curIndex++] = cube;
                    }
                    break;
            }
        }
        return retVal;
    }

    private enum Color {
        Yellow, Red, Green, Orange, Blue, White, UnKnownColor
    }

    private enum Side {
        Front(1), Behind(0), Top(3), Bellow(2), Left(5), Right(4);

        int oppositeSideIndex;

        private Side(int oppositeSideIndex) {
            this.oppositeSideIndex = oppositeSideIndex;
        }

        public Side getOppisiteSide() {
            return Side.values()[oppositeSideIndex];
        }
    }

    public enum Direction {
        XDirection(Side.Right, Side.Top, Side.Behind, Side.Bellow, Side.Front), // right
        // side
        YDirection(Side.Front, Side.Top, Side.Right, Side.Bellow, Side.Left), // front
        // side
        ZDirection(Side.Top, Side.Front, Side.Left, Side.Bellow, Side.Right); // top
        // side

        public final Side relativeSide;
        public final Side[] transformOrder;

        private Direction(Side relativeSide, Side order1, Side order2, Side order3, Side order4) {
            this.relativeSide = relativeSide;
            transformOrder = new Side[]{order1, order2, order3, order4};
        }

        public Side[] getTargetSides(int turnCount) {
            Side[] targets = new Side[4];
            for (int i = 0; i < 4; i++) {
                targets[i] = transformOrder[(i + turnCount) % 4];
            }
            return targets;
        }
    }

    /**
     * SubCube class represent the 9*3-1 = 26 small cube
     */
    private static class SubCube {
        private final Pair<Color, Side>[] colorPairs;

        public SubCube(Pair<Color, Side>... pairs) {
            colorPairs = pairs;
        }

        public void transform(Side[] from, Side[] to) {
            for (Pair<Color, Side> colorPair : colorPairs) {
                for (int i = 0; i < from.length; i++) {
                    if (colorPair.getSecond() == from[i]) {
                        colorPair.setSecond(to[i]);
                        break;
                    }
                }
            }
        }

        public boolean isPresentIn(Side dir) {
            for (Pair<Color, Side> colorPair : colorPairs) {
                if (colorPair.getSecond() == dir) {
                    return true;
                }
            }
            return false;
        }

        public Color getColorForSide(Side dir) {
            for (Pair<Color, Side> colorPair : colorPairs) {
                if (colorPair.getSecond() == dir) {
                    return colorPair.getFirst();
                }
            }
            return Color.UnKnownColor;
        }

        @Override
        public String toString() {
            return "SubCube {" + Arrays.toString(colorPairs) + "}";
        }
    }

}
