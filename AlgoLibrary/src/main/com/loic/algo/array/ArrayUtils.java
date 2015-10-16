package com.loic.algo.array;

import java.util.List;

public class ArrayUtils
{
	public static <T extends Comparable<T>> int binarySearch(List<T> list, T key)
	{
		return binarySearch(list, 0, list.size(), key);
	}
	
	public static <T extends Comparable<T>> int binarySearch(List<T> list, int from, int to, T key)
	{
		int low = from;
        int high = to - 1;

        while (low <= high)
        {
            int mid = (low + high) >>> 1;
            T midVal = list.get(mid);
            int cmp = midVal.compareTo(key);
            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return ~low ;  // key not found.
	}
}
