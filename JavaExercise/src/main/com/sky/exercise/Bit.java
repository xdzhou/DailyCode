package com.sky.exercise;

public class Bit
{
	private int bits = 0;

	public static void main(String[] args)
	{
		Bit bit = new Bit(Integer.MIN_VALUE + 1);
		bit.showBit();
	}

	public Bit(int bit)
	{
		bits = bit;
	}

	public void setBit(int position, int bit)
	{
		if (position >= 0 && position <= 31)
		{
			int currentBit = ((bits >>> position) & 1);
			// System.out.println("current bit = "+currentBit);
			if (bit != currentBit)
			{
				bits = bits ^ (1 << position);
			}
		}
	}

	public void showBit()
	{
		System.out.print(bits + " = ");
		for (int i = 31; i >= 0; i--)
		{
			System.out.print(((bits >>> i) & 1));
		}
		System.out.println();
	}

}
