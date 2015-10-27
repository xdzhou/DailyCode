package com.sky.exercise;

import com.sky.problem.OneInputOneOutputProb;
/**
 * 输入一个整数，求该整数的二进制表达中有多少个1。
 * 例如输入10，由于其二进制表示为1010，有两个1，因此输出2。
 */
public class OneBitCount implements OneInputOneOutputProb<Integer, Integer>
{

	@Override
	public Integer resolve(Integer param)
	{
		int count = 0;
		while(param != 0)
		{
			param = param & (param - 1);
			count ++;
		}
		return count;
	}

}
