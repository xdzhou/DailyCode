package com.loic.solution;

import org.testng.annotations.Test;

import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertTrue;

public class AbstractSolutionProviderTest {

  @Test
  public void testSolutionCount() {
    AbstractSolutionProvider solutionProvider = spy(new AbstractSolutionProvider() {
      @Override
      protected Object resolve(Object input) {
        return null;
      }
    });

    assertTrue(solutionProvider.solutions().size() == 1);
  }
}
