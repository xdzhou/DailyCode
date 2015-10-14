package com.sky.OOP;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sky.OOP.RubikCube.Direction;

public class RubikCubeTest
{
	@Test
	public void testRotate()
	{
		RubikCube rubikCube = RubikCube.getPrefectRubikCube();
		Assert.assertTrue(rubikCube.isRubikCubePrefect());
		
		rubikCube.clockwiseRotate(Direction.XDirection, 1, 1);
		Assert.assertFalse(rubikCube.isRubikCubePrefect());
		
		rubikCube.clockwiseRotate(Direction.XDirection, 2, 1);
		Assert.assertFalse(rubikCube.isRubikCubePrefect());
		
		rubikCube.clockwiseRotate(Direction.XDirection, 3, 1);
		Assert.assertTrue(rubikCube.isRubikCubePrefect());
	}
}
