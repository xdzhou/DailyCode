package com.loic.OOP;

import com.loic.OOP.RubikCube.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RubikCubeTest {
  @Test
  public void testRotate() {
    RubikCube rubikCube = RubikCube.getPrefectRubikCube();
    Assertions.assertTrue(rubikCube.isRubikCubePrefect());

    rubikCube.clockwiseRotate(Direction.XDirection, 1, 1);
    Assertions.assertFalse(rubikCube.isRubikCubePrefect());

    rubikCube.clockwiseRotate(Direction.XDirection, 2, 1);
    Assertions.assertFalse(rubikCube.isRubikCubePrefect());

    rubikCube.clockwiseRotate(Direction.XDirection, 3, 1);
    Assertions.assertTrue(rubikCube.isRubikCubePrefect());
  }
}
