package com.loic.hashcode;

import static org.testng.Assert.assertEquals;

import java.io.File;

import com.loic.hashcode.pizzaSlice.Main;
import org.testng.annotations.Test;

public class PizzaSliceTest {
    @Test
    public void testSmallCase() throws Exception {
        File file = new File("src/main/resources/hashcode/pizzaSlice/small.in");
        int point = new Main().start(file);
        assertEquals(point, 42);
    }
}
