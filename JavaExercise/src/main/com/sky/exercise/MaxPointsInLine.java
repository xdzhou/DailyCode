package com.sky.exercise;

import java.util.HashMap;
import java.util.Map;

import com.loic.algo.common.Point;
import com.sky.problem.Problem;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * @link https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaxPointsInLine implements Problem<Point[], Integer>
{

	@Override
	public Integer resolve(Point[] param)
	{
		if(param.length < 3)
		{
			return param.length;
		}
		int sameSlopeCountMax = 1;
		Map<Float, Integer> slopeMap = new HashMap<>();
		for(int i = 0; i < param.length - 1; i++)
		{
			int curSameSlopeCountMax = 1;
			int delta = 0;
			slopeMap.clear();
			for(int j = i+1; j < param.length; j++)
			{
				if((param[i].x == param[j].x) && (param[i].y == param[j].y))
				{
					delta++;
				}
				else 
				{
					float k = getSlope(param[i], param[j]);
					Integer count = slopeMap.get(k);
					if(count == null)
					{
						slopeMap.put(k, 1);
					}
					else 
					{
						slopeMap.put(k, count + 1);
						curSameSlopeCountMax = Math.max(curSameSlopeCountMax, count + 1);
					}
				}
			}
			curSameSlopeCountMax += delta;
			sameSlopeCountMax = Math.max(sameSlopeCountMax, curSameSlopeCountMax);
		}
		int result = Math.min(sameSlopeCountMax + 1, param.length);
		return result;
	}
	
	private float getSlope(Point p1, Point p2)
	{
		float k = (p2.x == p1.x) ? Float.MAX_VALUE : (p2.y - p1.y) / (float)(p2.x - p1.x);
		if(k == 0)
		{
			k = 0;
		}
		System.out.println(p1+" : "+p2+" : "+k);
		
		return k;
	}

}
