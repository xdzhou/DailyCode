package com.loic.solution;

import org.testng.annotations.Test;

import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertEquals;

public class AbstractSolutionProviderTest {

  @Test
  public void testSolutionCount() {
    SingleSolutionProvider solutionProvider = spy(new SingleSolutionProvider() {
      @Override
      protected Object resolve(Object input) {
        return null;
      }
    });

    assertEquals(solutionProvider.solutions().size(), 1);
  }
}
