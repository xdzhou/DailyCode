package com.sky.recursion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigInt implements Cloneable
{
	private static final Logger Log = LoggerFactory.getLogger(BigInt.class);
	private static final int MAX = 999999999;
    private BigInt mPrefix;
    private int value = 0;
    
    public BigInt(String num)
    {
    	if(num.length() <= 9)
    	{
    		try
			{
    			this.value = Integer.parseInt(num);
			} 
    		catch (NumberFormatException e)
			{
    			Log.warn("parse number failed for "+num);
			}
    	}
    	else 
    	{
    		try
			{
    			this.value = Integer.parseInt(num.substring(num.length() - 9, num.length()));
			} 
    		catch (NumberFormatException e)
			{
    			Log.warn("parse number failed for "+num);
			}
    		
    		mPrefix = new BigInt(num.substring(0, num.length() - 9));
		}
    }
    
    public BigInt()
    {
    }

    public void add(int n)
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
            mPrefix.add(1);
        }
    }
    
    public void add(BigInt bigInt)
    {
    	if(bigInt != null)
    	{
    		if(MAX - value >= bigInt.value)
            {
                value += bigInt.value;
            }
            else
            {
                value = bigInt.value - (MAX - value + 1);
            }
        	int addition = (MAX - value >= bigInt.value) ? 0 : 1;
        	if(addition > 0 || bigInt.mPrefix != null)
        	{
        		if(mPrefix == null)
                {
                    mPrefix = new BigInt();
                }
        		mPrefix.add(addition);
        		mPrefix.add(bigInt.mPrefix);
        	}
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
    
    @Override
    public BigInt clone()
    {
    	BigInt newInstance = new BigInt();
    	newInstance.value = this.value;
    	if(mPrefix != null)
    	{
    		newInstance.mPrefix = mPrefix.clone();
    	}
    	return newInstance;
    }
}
