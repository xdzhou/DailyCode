package com.sky.exercise;

import com.sky.problem.Problem;

/**
 * Additive number is a positive integer whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 
 * @link https://leetcode.com/problems/additive-number/
 */
public class AdditiveNumber implements Problem<String, Boolean>
{

	/**
	 * 诀窍就是只要确定前2个数字，后面的可以自动求出
	 */
	@Override
	public Boolean resolve(String s)
	{
		if(s == null || s.length() < 3)
        {
            return false;
        }
        char[] nums = s.toCharArray();

        for(int firstLen = 1; firstLen <= (nums.length - 1) / 2; firstLen ++)
        {
            for(int secLen = 1; firstLen + secLen + Math.max(firstLen, secLen) <= nums.length; secLen++)
            {
                long firstNum = getLong(nums, 0, firstLen);
                long secondNum = getLong(nums, firstLen, secLen);

                if(firstNum >= 0 && secondNum >= 0)
                {
                    int curIndi = firstLen + secLen;
                    long checkValue;
                    do
                    {
                        checkValue = firstNum + secondNum;
                        curIndi = checkNumExistence(nums, curIndi, checkValue);
                        if(curIndi == nums.length)
                        {
                            return true;
                        }
                        firstNum = secondNum;
                        secondNum = checkValue;
                    }
                    while (curIndi > 0);
                }
            }
        }

        return false;
	}
	
	private long getLong(char[] nums, int from, int len)
    {
        int firstNum = nums[from] - '0';
        if(firstNum < 0 || firstNum > 9 || len <= 0 || from + len > nums.length)
        {
            return -1;
        }
        if(firstNum == 0)
        {
            return len == 1 ? 0 : -1;
        }
        long retVal = 0;
        for(int i = from; i < from + len; i++)
        {
            retVal *= 10;
            retVal += (nums[i] - '0');
        }
        return retVal;
    }

    private int checkNumExistence(char[] nums, int from, long checkValue)
    {
        int len = Long.toString(checkValue).length();
        int firstNum = nums[from] - '0';
        if(firstNum < 0 || firstNum > 9 || nums.length - from < len)
        {
            return -1;
        }
        if(firstNum == 0)
        {
            return checkValue == 0 ? from + len : -1;
        }

        for(int i = from + len - 1; i >= from; i--)
        {
            if(nums[i] - '0' != checkValue % 10)
            {
                return -1;
            }
            checkValue /= 10;
        }
        return from + len;
    }

}
