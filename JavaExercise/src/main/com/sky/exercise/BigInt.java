package com.sky.exercise;

public class BigInt
{
	private static final int MAX = 999999999;
    private BigInt mPrefix;
    private int value = 0;

    public void increase(int n)
    {
        if(MAX - value >= n)
        {
            value += n;
        }
        else
        {
            value = n - (MAX - value + 1);
            if(mPrefix == null)
            {
                mPrefix = new BigInt();
            }
            mPrefix.increase(1);
        }
    }

    public String getPrintablValue()
    {
        if(mPrefix == null)
        {
            return Integer.toString(value);
        }
        else
        {
            StringBuilder valueSb = new StringBuilder(Integer.toString(value));
            int delta = 9 - Integer.toString(value).length();
            for(int i=0; i<delta; i++)
            {
                valueSb.insert(0, "0");
            }
            valueSb.insert(0, mPrefix.getPrintablValue());
            return valueSb.toString();
        }
    }
}
