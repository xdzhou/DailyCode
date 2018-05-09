package com.loic.hashcode;

import com.loic.hashcode.pizzaSlice.Main;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class PizzaSliceTest {
  @Test
  public void testSmallCase() throws Exception {
    File file = new File("src/main/resources/hashcode/pizzaSlice/small.in");
    int point = new Main().start(file);
    assertEquals(point, 42);
  }
}
