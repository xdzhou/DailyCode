package com.loic.algo.search.core;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class SearchPathTest {

    @Test
    public void testEmptyPath() throws Exception {
        SearchPath path = new SearchPath();
        Assert.assertEquals(0, path.deep());
    }

    @Test
    public void testPathEqual() throws Exception {
        Transition t1 = Mockito.mock(Transition.class);
        Transition t2 = Mockito.mock(Transition.class);

        SearchPath path1 = new SearchPath(t1, t2);
        SearchPath path2 = new SearchPath(Lists.newArrayList(t1, t2));

        Assert.assertEquals(path1.getTransitions(), path2.getTransitions());
        Assert.assertEquals(path1, path2);
    }
}
