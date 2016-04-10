package com.loic.algo.common;

import com.google.common.base.Preconditions;

public class BitMap {
	private final int[] mBits;

	public BitMap(int maxLen) {
		Preconditions.checkArgument(maxLen > 0);
		mBits = new int[maxLen / 32];
	}

	/**
	 * 
	 * @return true if set success, false if already setted
	 */
	public boolean set(int number) {
		// number % 32 == number & 0x1F
		int index = number / 32;
		int rest = number & 0x1F;
		if ((mBits[index] & (1 << rest)) != 0) {
			return false;
		}
		mBits[index] |= (1 << rest);
		return true;
	}

	/**
	 * 
	 * @return whether the number is setted
	 */
	public boolean isSet(int number) {
		// number % 32 == number & 0x1F
		return (mBits[number / 32] & (1 << (number & 0x1F))) != 0;
	}
}
