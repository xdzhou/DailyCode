package com.sky.recursion;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loic.algo.common.Pair;
import com.sky.problem.Problem;
/**
 * 给出一个矩阵，其中每一行和每一列都是有序的，写一个函数在矩阵中找出指定的数
 * @link http://www.hawstein.com/posts/9.6.html
 *
 */
public class BinarySearch2D<K extends Comparable<K>> implements Problem<Pair<K[][], K>, Pair<Integer, Integer>>
{
	private static final Logger Log = LoggerFactory.getLogger(BinarySearch2D.class);

	public static final Pair<Integer, Integer> UNFOUND = Pair.create(-1, -1);
	
	@Override
	public Pair<Integer, Integer> resolve(Pair<K[][], K> param)
	{
		Objects.requireNonNull(param);
		return binarySearch2D(param.getFirst(), param.getSecond());
	}

    public Pair<Integer, Integer> binarySearch2D(K[][] table, K value)
    {
        Pair<Integer, Integer> result = binarySearch2D(table, value, 0, table[0].length - 1, 0, table.length - 1);
        if(result == UNFOUND)
        {
        	Log.debug("can't find {}", value);
        }
        else
        {
            Log.debug("{} found, index : {}", value, result);
        }
        return result;
    }

    private Pair<Integer, Integer> binarySearch2D(K[][] table, K value, int lineFrom, int lineTo, int rowFrom, int rowTo)
    {
        if(value.compareTo(table[lineFrom][rowFrom]) < 0 || value.compareTo(table[lineTo][rowTo]) > 0)
        {
            return UNFOUND;
        }
        if((lineTo - lineFrom) * (rowTo - rowFrom) < 9)
        {
        	Log.debug("line from {} to {}, row from {} to {}", lineFrom, lineTo, rowFrom, rowTo);
            Pair<Integer, Integer> result = UNFOUND;
            for(int i = lineFrom; i <= lineTo && result == UNFOUND; i++)
            {
                for(int j = rowFrom; j <= rowTo && result == UNFOUND; j++)
                {
                    result = checkValue(table, value, i, j);
                }
            }
            return result;
        }
        else
        {
            int lineMid = (lineFrom + lineTo) >>> 1;
            int rowMid = (rowFrom + rowTo) >>> 1;

            Pair<Integer, Integer> result = checkValue(table, value, lineMid, rowMid);
            if(result == UNFOUND)
            {
            	Log.debug("check mid index : table[{}, {}] = {}", lineMid, rowMid, table[lineMid][rowMid]);
                if(value.compareTo(table[lineMid][rowMid]) < 0)
                {
                    result = binarySearch2D(table, value, lineFrom, lineMid, rowFrom, rowMid);
                    if(result == UNFOUND)
                    {
                        result = binarySearch2D(table, value, lineFrom, lineMid - 1, rowMid + 1, rowTo);
                    }
                    if(result == UNFOUND)
                    {
                        result = binarySearch2D(table, value, lineMid + 1, lineTo, rowFrom, rowMid - 1);
                    }
                }
                else
                {
                    result = binarySearch2D(table, value, lineMid, lineTo, rowMid, rowTo);
                    if(result == UNFOUND)
                    {
                        result = binarySearch2D(table, value, lineFrom, lineMid - 1, rowMid + 1, rowTo);
                    }
                    if(result == UNFOUND)
                    {
                        result = binarySearch2D(table, value, lineMid + 1, lineTo, rowFrom, rowMid - 1);
                    }
                }
            }

            return result;
        }
    }

    private Pair<Integer, Integer> checkValue(K[][] table, K value, int line, int row)
    {
        if(value.equals(table[line][row]))
        {
            return Pair.create(line, row);
        }
        else
        {
            return UNFOUND;
        }
    }
}
