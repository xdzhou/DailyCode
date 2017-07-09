package com.loic.solution;

import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

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
