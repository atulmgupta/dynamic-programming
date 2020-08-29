package com.atul.dp._01knapsack;

/**
 * Given a set of positive numbers, 
 * find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.
 * 
 * Example 1: #
	Input: {1, 2, 3, 4}
	Output: True
	Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
	
	Example 2: #
	Input: {1, 1, 3, 4, 7}
	Output: True
	Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
	
	
	Example 3: #
	Input: {2, 3, 4, 6}
	Output: False
	Explanation: The given set cannot be partitioned into two subsets with equal sum.
 */
public class EqualSubsetSumPartition {
	private boolean canPartition(int[] num) {

		return false;
	}

	public static void main(String[] args) {
		EqualSubsetSumPartition subsetSumPartition = new EqualSubsetSumPartition();
		int[] num = { 1, 2, 3, 4 };
		System.out.println(subsetSumPartition.canPartition(num));
		
		num = new int[] { 1, 1, 3, 4, 7 };
		System.out.println(subsetSumPartition.canPartition(num));
		
		num = new int[] { 2, 3, 4, 6 };
		System.out.println(subsetSumPartition.canPartition(num));
	}
}
