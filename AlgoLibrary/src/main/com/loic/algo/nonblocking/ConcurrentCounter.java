package com.loic.algo.nonblocking;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentCounter
{
    private AtomicInteger mCount = new AtomicInteger(0);

    private int get()
    {
        return mCount.get();
    }

    private int increase()
    {
        while(true)
        {
            int oldValue = mCount.get();
            if(mCount.compareAndSet(oldValue, oldValue + 1))
            {
                return oldValue + 1;
            }
        }
    }
}