package com.loic.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

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
