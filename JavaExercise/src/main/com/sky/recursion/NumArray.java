package com.sky.recursion;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive. The update(i, val) function modifies nums by
 * updating the element at index i to val.
 *
 * @link https://leetcode.com/problems/range-sum-query-mutable/
 */
public class NumArray {
	private static final int BLOCK_SIZE = 500;

	private NumArray superNumArray;
	private int[] nums;

	public NumArray(int[] nums) {
		System.out.println("New NumArray with nums size : " + nums.length);
		this.nums = nums;

		if (nums.length > BLOCK_SIZE) {
			int blockSize = nums.length / BLOCK_SIZE;
			if (nums.length % BLOCK_SIZE > 0) {
				blockSize++;
			}

			int[] blockCounts = new int[blockSize];

			for (int i = 0; i < blockSize; i++) {
				for (int j = i * BLOCK_SIZE; j < (i + 1) * BLOCK_SIZE && j < nums.length; j++) {
					blockCounts[i] += nums[j];
				}
			}
			superNumArray = new NumArray(blockCounts);
		}
	}

	void update(int i, int val) {
		int delta = val - nums[i];
		if (delta != 0) {
			applyDelta(i, delta);
		}
	}

	private void applyDelta(int i, int delta) {
		nums[i] += delta;
		if (superNumArray != null) {
			superNumArray.applyDelta(i / BLOCK_SIZE, delta);
		}
	}

	public int sumRange(int i, int j) {
		System.out.println("sumRange : " + i + " - " + j);

		int blockIndex1 = i / BLOCK_SIZE;
		int blockIndex2 = j / BLOCK_SIZE;

		if (blockIndex2 - blockIndex1 > 1) {
			int sum = 0;
			System.out.println("get sum from blockIndex " + (blockIndex1 + 1) + " to " + (blockIndex2 - 1));
			sum += superNumArray.sumRange(blockIndex1 + 1, blockIndex2 - 1);

			System.out.println("get sum from " + i + " to " + ((blockIndex1 + 1) * BLOCK_SIZE - 1));

			sum += sumRangeSimple(i, (blockIndex1 + 1) * BLOCK_SIZE - 1);

			System.out.println("get sum from " + (blockIndex2 * BLOCK_SIZE) + " to " + j);

			sum += sumRangeSimple(blockIndex2 * BLOCK_SIZE, j);
			return sum;
		} else {
			return sumRangeSimple(i, j);
		}
	}

	private int sumRangeSimple(int i, int j) {
		int sum = 0;
		for (int k = i; k <= j; k++) {
			sum += nums[k];
		}
		return sum;
	}

}