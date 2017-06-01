package com.loic.algo.common;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.stream.IntStream;

public class BitMap {
    private final int[] mBits;

    public BitMap(int maxLen) {
        checkArgument(maxLen > 0);
        mBits = new int[maxLen / 32 + 1];
    }

    /**
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
     * @return whether the number is setted
     */
    public boolean isSet(int number) {
        // number % 32 == number & 0x1F
        return (mBits[number / 32] & (1 << (number & 0x1F))) != 0;
    }

    public String binaryString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mBits.length; i++) {
            if (mBits[i] != 0) {
                String origin = Integer.toBinaryString(mBits[i]);
                sb.insert(0, origin);
                if (i + 1 < mBits.length && mBits[i + 1] != 0) {
                    IntStream.range(0, 32 - origin.length()).forEach(val -> sb.insert(0, '0'));
                }
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
